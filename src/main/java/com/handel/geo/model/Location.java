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
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private float fi;
    private float lambda;
    @Nullable
    private float h;

    @Nullable
    private float accuracy;

    private LocalDateTime date_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Locator")
    private Locator locator;
}