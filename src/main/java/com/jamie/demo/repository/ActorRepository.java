package com.jamie.demo.repository;

import com.jamie.demo.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
