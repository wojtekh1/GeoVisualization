package com.handel.geo.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private float Fi;
    private float Lambda;
    @Nullable
    private float H;

    @Nullable
    private float dFi;
    @Nullable
    private float dLambda;
    @Nullable
    private float dH;

    private LocalDateTime date_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Locator")
    private Locator locator;
}