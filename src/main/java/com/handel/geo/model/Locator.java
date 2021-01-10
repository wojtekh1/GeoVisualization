package com.handel.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Data
@Builder
@AllArgsConstructor
@Entity
/**
 * Klasa służąca do opisu lokalizatora
 */
public class Locator {
    /** Identyfikator */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Klucz API */
    @Generated
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String apiKey;
    /** Krótki opis lokalizatora */
    @JsonIgnore
    @NotEmpty(message = "Wypełnij opis dodatkowy")
    private String description;
    /** Data modyfikacji */
    @JsonIgnore
    private LocalDateTime modyficationDate;
    /** Użykownik do którego należy lokalizator */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User")
    private Users user;
    /** Nazwa nadajnika */
    @NotEmpty(message = "Wypełnij nazwę nadajnika")
    private String name;

    public Locator() {
        this.apiKey=UUID.randomUUID().toString();
    }
}
