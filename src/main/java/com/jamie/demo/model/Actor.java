package com.jamie.demo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actor_id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @JsonBackReference
    @ManyToMany(mappedBy = "actor")

    private Set<Film> film = new HashSet<>();

    public Actor(String first_name,String last_name ) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Actor (){
    }
}



