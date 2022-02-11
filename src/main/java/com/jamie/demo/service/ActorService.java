package com.jamie.demo.service;

import com.jamie.demo.model.Actor;

import java.util.List;



public interface ActorService {
    Actor saveActor(Actor actor);
    List<Actor> getAllActor();
    Actor getActorByActor_Id(int actor_id);

    Actor updateActor(Actor actor, int actor_id);
    void deleteActor(int actor_id);
}