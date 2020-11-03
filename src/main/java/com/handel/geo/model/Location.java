package com.handel.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handel.geo.repository.LocationsRepository;
import lombok.*;
import org.hibernate.Session;
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
public class Location {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Float fi;
    @NotNull
    private Float lambda;
    @Nullable
    private float h;

    @Nullable
    private float accuracy;

    private LocalDateTime date_time;

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


