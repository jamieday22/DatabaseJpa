package com.jamie.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id;

    @Column(name = "description")
    private String Description;

    @Column(name = "film_id")
    private String Film_Id;

//    @JoinColumn(name = "film_id", referencedColumnName = "film_id", updatable = false, insertable = false)
//    private Film film;

}