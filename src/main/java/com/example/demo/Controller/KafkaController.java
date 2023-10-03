package com.example.demo.Controller;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.TripsDto;
import com.example.demo.model.Trips;
import com.example.demo.service.KafkaConsumerService;
import com.example.demo.service.KafkaService;
import com.example.demo.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    KafkaService kafkaService;
    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @Autowired
    TestServiceImpl testService;

//    @GetMapping("/publish-kafka")
//    public void publishKafka(@RequestParam String key, @RequestParam String value) throws IOException {
//        kafkaService.publishKafka(key,value);
//    }



    @GetMapping("/trips")
    public ResponseEntity<String> tripsKafka(@RequestParam Integer id) throws Exception {
        List<TripsDto> list = new ArrayList<>();
        list=testService.getTripsByBikeid(id).stream().map(
                trips -> TripsDto.builder().id(trips.getId())
                        .bikeid(trips.getBikeid())
                        .startstationid(trips.getStart_station_id())
                        .endstationid(trips.getEnd_station_id())
                        .tripduration(trips.getTripduration())
                        .startstationname(trips.getStart_station_name())
                        .endstationname(trips.getEnd_station_name())
                        .birthyear(trips.getBirth_year())
                        .startstationlocation(trips.getStart_station_location().toString())
                        .endstationlocation(trips.getEnd_station_location().toString())
                        .starttime(trips.getStart_time().toString())
                        .stoptime(trips.getStop_time().toString()).build()).collect(Collectors.toList());

        kafkaService.publishKafka(list.get(0));
        return new ResponseEntity<>("Published Succesfully",HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<String> getAllBooks() throws Exception{
        List<BooksDto> booksDtos = new ArrayList<>();
        booksDtos= testService.getAllBooks().stream().map(
                books -> BooksDto.builder().book_id(books.getBook_id()).name(books.getName()).build()).collect(Collectors.toList());

         kafkaService.publishBooks(booksDtos.get(0));
        return new ResponseEntity<>("Published Succesfully",HttpStatus.OK);
    }
}
