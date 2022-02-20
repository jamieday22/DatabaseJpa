package com.jamie.demo.repository;

import com.jamie.demo.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {


}
