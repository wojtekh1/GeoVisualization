package com.handel.geo.model;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "locator")
public class Locator {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

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
