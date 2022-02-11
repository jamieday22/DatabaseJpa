package com.jamie.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.time.Year;

@Data
@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int film_id;

    @Column(name = "title")
    private String title;

    @Column(name = "language_id")
    private String language_id;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer release_year;

    @Column(name = "rating")
    private String rating;







}