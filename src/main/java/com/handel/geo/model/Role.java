package com.handel.geo.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "Role")
/**
 * Role użytkowników aplikacji
 */
public class Role {
    /** Identyfikator */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private Integer roleId;
    /** Nazwa roli */
    @Column(name = "Type")
    private String role;
}
