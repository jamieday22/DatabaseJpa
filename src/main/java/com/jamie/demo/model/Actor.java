package com.jamie.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actor_id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name")
    private String last_name;


}