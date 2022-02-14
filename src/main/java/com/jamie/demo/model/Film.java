package com.jamie.demo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "actor")
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    @JsonManagedReference
    private Set<Actor> actor;

    public Film(String title,String language_id, String description , Integer release_year, String rating) {  // , Actor actor
        this.language_id = language_id;
        this.description = description;
        this.release_year = release_year;
        this.rating = rating;
        this.title = title;
//        this.actor = Stream.of(actor).collect(Collectors.toSet());
//        this.actor.forEach(x -> x.getFilm().add(this));
    }

        public Film (){
    }



}
