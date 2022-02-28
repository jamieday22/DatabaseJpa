package com.jamie.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Column(name = "review")
    private String review;

    @Column(name = "language_id")
    private String Language_Id;



//    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
//    @JoinTable(name = "film_actor",
//            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
//            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
//    private Set<Actor> actor;
//
//
//
//
    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
            joinColumns = { @JoinColumn(name = "film_id")},
            inverseJoinColumns = { @JoinColumn (name = "actor_id")})
    private Set<Actor> actor = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "film_category",
            joinColumns = { @JoinColumn(name = "film_id")},
            inverseJoinColumns = { @JoinColumn (name = "category_id")})
    private Set<Category> category = new HashSet<>();

//    @ManyToMany(targetEntity = Review.class, mappedBy = "films", cascade = CascadeType.ALL)
//    private List<Review> reviews;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn( name = "film_language", referencedColumnName = "film_id")
//    List<Language> languages = new ArrayList<>();

//      @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
//      private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "film_id", referencedColumnName = "film_id")
    List<Review> reviews = new ArrayList<>();

}
