package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripsDto implements Serializable {
    private String id;
    private String tripduration;
    private Integer startstationid;
    private Integer endstationid;
    private String startstationname;
    private String endstationname;
    private Integer bikeid;
    private Integer birthyear;
    private String startstationlocation;
    private String endstationlocation;
    private String starttime;
    private String stoptime;
}
