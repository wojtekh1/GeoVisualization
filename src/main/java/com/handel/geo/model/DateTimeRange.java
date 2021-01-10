package com.handel.geo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Klasa służąca do określenia przedziału czasowego
 */
public class DateTimeRange {
    /** Dolny zakres przedziału */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime from;
    /** Górny zakres przedziału */
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
