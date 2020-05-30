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
//@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @NotEmpty(message = "Wypełnij treść postu")
    private String postContent;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User")
    private Users user;

    @NotEmpty(message = "Wypełnij tytuł postu")
    private String topic;

    @Nullable
    private String imageLink;


}
