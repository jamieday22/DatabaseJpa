package com.jamie.demo.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int film_id;

    @Column(name = "title")
    private String Title;

    @Column(name = "description")
    private String Description;

    @Column(name = "release_year")
    private int Release_Year;

    @Column(name = "rating")
    private String Rating;

//    @Column(name = "language_id")
//    private String Language_Id;


}
