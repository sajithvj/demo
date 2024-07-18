package com.example.demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeExample {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT2 = "yyyy-MM-dd HHmm";
    public static void main(String[] args) {

        String dateInString = "2024-02-09 00:48";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("America/New_York");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);

        ZoneId uTCZoneId = ZoneId.of("UTC");
        System.out.println("TimeZone : " + uTCZoneId);

        ZonedDateTime utcDateTime = asiaZonedDateTime.withZoneSameInstant(uTCZoneId);
        System.out.println("Date (New York) : " + utcDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT2);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));
        System.out.println("Date (UTC) : " + format.format(utcDateTime));




    }

}