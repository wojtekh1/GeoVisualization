package com.handel.geo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
public class DateTimeRange {
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime from;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime to;


    public DateTimeRange() {
        this.from = LocalDateTime.now();
        this.to = LocalDateTime.now();
    }
    public DateTimeRange(LocalDateTime from,LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    //    String dateFrom=from.toString().substring(0,10);
//    String dateTo=to.toString().substring(0,10);
//    String timeFrom=from.toString().substring(11,16);
//    String timeTo=to.toString().substring(11,16);

    public LocalDateTime getDateTimeFrom() {
        return from;
    }

    public LocalDateTime getDateTimeTo() {
        return to;
    }

    public void setDateTimeFrom(LocalDateTime dateTime) {
        this.from = dateTime;
    }

    public void setDateTimeTo(LocalDateTime dateTime) {
        this.to = dateTime;
    }
}
