package com.jamie.demo.service;

import com.jamie.demo.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    Actor saveActor(Actor actor);
    List<Actor> getAllActors();
    Optional<Actor> getActorById(int actor_id);
    Actor updateActor(Actor updatedActor);
    void deleteActor(int actor_id);
}
