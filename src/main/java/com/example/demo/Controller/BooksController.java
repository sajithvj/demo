package com.example.demo.Controller;

import com.example.demo.dto.DetailsDto;
import com.example.demo.model.Books;
import com.example.demo.service.impl.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/book")
public class BooksController {
    @Autowired
    BooksService booksService;
   @PostMapping(value="/saveBooks",consumes ={MediaType.MULTIPART_FORM_DATA_VALUE},produces = "application/json")
    public ResponseEntity saveBooks(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
       for(MultipartFile file: files){
           booksService.saveBooks(file);
       }
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(value = "/find",produces = "application/json")
    public CompletableFuture<ResponseEntity> getBooks(){
       return booksService.findAllBooks().thenApply(ResponseEntity::ok);
    }
    @GetMapping(value = "/findAll",produces = "application/json")
    public ResponseEntity getAllBooks(){
       CompletableFuture<List<Books>> books1 = booksService.findAllBooks();
       CompletableFuture<List<Books>> books2 = booksService.findAllBooks();
       CompletableFuture<List<Books>> books3 = booksService.findAllBooks();
       CompletableFuture.allOf(books1,books2,books3).join();
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/companies-data")
    public ResponseEntity<List<DetailsDto>> getCompaniesData() {
        List<DetailsDto> detailsDtos = null;
       detailsDtos= booksService.getCompaniesData();
        return new ResponseEntity<>(detailsDtos, HttpStatus.ACCEPTED);
    }
}
