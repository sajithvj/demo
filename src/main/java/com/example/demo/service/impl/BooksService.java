package com.example.demo.service.impl;

import com.example.demo.RestTemplateConfig;
import com.example.demo.dto.DetailsDto;
import com.example.demo.exception.AppException;
import com.example.demo.model.Books;
import com.example.demo.repository.BooksRepository;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@Service
public class BooksService {
    @Autowired
    BooksRepository booksRepository;

    @Autowired
    RestTemplate restTemplateConfig;
    public static final String ADDRESS_SERVICE_URL="http://localhost:8080/test/companies-data";
    Logger logger = LoggerFactory.getLogger(BooksService.class);
@Async("asyncTaskExecutor")
    public CompletableFuture<List<Books>> saveBooks(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Books> booksList = parseCSVFile(file);
        logger.info("Saving list of books {}",booksList.size()," "+Thread.currentThread().getName());
        booksList= booksRepository.saveAll(booksList);
        long end = System.currentTimeMillis();
        logger.info("Total time {}",end-start);
        return CompletableFuture.completedFuture(booksList);

    }
   @Async("asyncTaskExecutor")
    public CompletableFuture<List<Books>> findAllBooks(){
    logger.info("get list of books by {}",Thread.currentThread().getName());
    List<Books> books = booksRepository.findAll();
    return CompletableFuture.completedFuture(books);
    }

    private List<Books> parseCSVFile(MultipartFile file) throws Exception{
        List<Books> books = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] data = line.split(",");
                final Books book = new Books();
                book.setId(new ObjectId(data[0]));
                book.setBook_id(data[1]);
                book.setName(data[2]);
                books.add(book);
            }
            return books;
        } catch (IOException e) {
            logger.error("Failed to parse Csv File {}", e);
            throw new Exception("Failed to parse Csv File {}", e);
        }
    }
    @CircuitBreaker(maxAttempts = 3, openTimeout = 10000, resetTimeout = 30000)
    @Retryable
    public List<DetailsDto> getCompaniesData(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<DetailsDto>> response= restTemplateConfig.exchange((ADDRESS_SERVICE_URL), HttpMethod.GET,null,  new ParameterizedTypeReference<List<DetailsDto>>() {});
         List<DetailsDto> detailsDtos = response.getBody();
        if (true) {
            throw new RuntimeException("Payment API is down");
        }
        else {
            return detailsDtos;
        }
    }
    @Recover
    private AppException localFallbackMethod(Exception e){
    return new AppException(HttpStatus.SERVICE_UNAVAILABLE.toString(),"Service is not responding properly");
    }
}
