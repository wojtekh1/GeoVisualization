package com.handel.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.validation.constraints.*;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
/**
 * Użytkownicy aplikacji
 */
public class Users {
    /** Identyfikator */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Integer userId;
    /** Zakodowane hasło */
    @JsonIgnore
    @Column(name = "Password")
    @Size(min = 5, message = "Hasło musi mieć przynajmniej 5 znaków")
    private String password;

    /** E-mail */
    @Column(name = "Email")
    @Email(message = "Wprowadź poprawny adres email")
    @NotEmpty
    private String email;

    @JsonIgnore
    @Column(name = "Is_Active")
    private Integer isActive;

    @JsonIgnore
    @Column(name = "Image")
    private String imageLink;
    /** Role użytkownika */
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Role",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Role_Id"))
    @NotEmpty(message = "Wybierz uprawnienia")
    private List<Role> roles;
}
