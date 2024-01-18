package com.ecouto.batchdeclara;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeExample {

    public static void main(String[] args) {

        //  LocalDateTime to Timestamp
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        System.out.println(now);            // 2019-06-14T15:50:36.068076300
        System.out.println(timestamp);      // 2019-06-14 15:50:36.0680763

        //  Timestamp to LocalDateTime
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        System.out.println(localDateTime);  // 2019-06-14T15:50:36.068076300

    }
}