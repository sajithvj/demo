package com.example.demo.Controller;


import com.example.demo.dto.BooksDto;
import com.example.demo.dto.TripsDto;
import com.example.demo.model.Books;
import com.example.demo.model.Trips;
import com.example.demo.service.KafkaService;
import com.example.demo.service.impl.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class KafkaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private KafkaService kafkaService;

    @MockBean
    private TestServiceImpl testService;

    @InjectMocks
    private KafkaController kafkaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(kafkaController).build();
    }



    @Test
    public void testTripsKafka() throws Exception {
        Trips tripsDto = Trips.builder()
                .id("1")
                .bikeid(100)
                .start_station_id(101)
                .end_station_id(102)
                .end_station_id(102)
                .tripduration("30")
                .start_station_name("Start Station")
                .end_station_name("End Station")
                .birth_year(1985)
                .start_station_location("Start Location")
                .end_station_location("End Location")
                .start_time(LocalDateTime.now())
                .stop_time(LocalDateTime.now())
                .build();

        List<Trips> tripsDtos = Collections.singletonList(tripsDto);

        when(testService.getTripsByBikeid(any(Integer.class))).thenReturn(Collections.singletonList(tripsDto));
        doNothing().when(kafkaService).publishKafka(any(TripsDto.class));

        ResponseEntity<String> response = kafkaController.tripsKafka(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Published Successfully", response.getBody());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Books booksDto = Books.builder()
                .book_id("1L")
                .name("Book Name")
                .build();

        List<Books> booksDtos = Collections.singletonList(booksDto);

        when(testService.getAllBooks()).thenReturn(Collections.singletonList(booksDto));
        doNothing().when(kafkaService).publishBooks(any(BooksDto.class));

        ResponseEntity<String> response = kafkaController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Published Successfully", response.getBody());
    }

    @Test
    public void testDeleteItem() {
        ResponseEntity<String> response = kafkaController.deleteItem(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("check the contents", response.getBody());
    }
}
