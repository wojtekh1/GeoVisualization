package com.handel.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handel.geo.repository.LocationsRepository;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Klasa służąca do opisu lokalizacji
 */
public class Location {

    /** Identyfikator */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Szerokość geograficzna */
    @NotNull
    private Float fi;
    /** Długość geograficzna */
    @NotNull
    private Float lambda;
    /** Wysokość nad poziomem morza */
    @Nullable
    private float h;
    /** Dokładność */
    @Nullable
    private float accuracy;
    /** Data i czas */
    private LocalDateTime date_time;
    /** Lokalizator który pomierzył lokalizację*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Locator")
    private Locator locator;

    public Location(float fi, float lambda, float h,float accuracy, LocalDateTime date_time,Locator locator) {
        this.fi=fi;
        this.lambda=lambda;
        this.h=h;
        this.date_time=date_time;
        this.locator=locator;
    }

}


