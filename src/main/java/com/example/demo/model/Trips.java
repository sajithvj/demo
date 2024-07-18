package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Document("test_trips")
@Builder
public class Trips {
    @Id
    private String id;
    private String tripduration;
    private Integer start_station_id;
    private Integer end_station_id;
    private String start_station_name;
    private String end_station_name;
    private Integer bikeid;
    private Integer birth_year;
    private Object start_station_location;
    private Object end_station_location;
    private LocalDateTime start_time;
    private LocalDateTime stop_time;

}
