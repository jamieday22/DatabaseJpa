package com.jamie.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

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
    private Integer Release_Year;

    @Column(name = "rating")
    private String Rating;



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "film_actor",
            joinColumns = {
                    @JoinColumn(name = "film_id", referencedColumnName = "film_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id",
                            nullable = false, updatable = false)})
    private Set<Actor> actor = new HashSet<>();



}
