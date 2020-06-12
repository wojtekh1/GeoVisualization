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
//@Table(name = "locator")
public class Locator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Wypełnij opis dodatkowy")
    private String description;

    private LocalDateTime modyficationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User")
    private Users user;

    @NotEmpty(message = "Wypełnij nazwę nadajnika")
    private String name;

    @Nullable
    private String imageLink;


}
