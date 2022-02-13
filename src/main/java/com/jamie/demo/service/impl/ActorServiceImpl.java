package com.jamie.demo.service.impl;

import java.util.List;

import com.jamie.demo.exception.ResourceNotFoundException;
import com.jamie.demo.model.Actor;
import com.jamie.demo.repository.ActorRepository;
import org.springframework.stereotype.Service;
import com.jamie.demo.service.ActorService;

@Service  // this is the get post put delete
public class ActorServiceImpl implements ActorService {


    private ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        super();
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public List<Actor> getAllActor() {
        return actorRepository.findAll();
    }


    @Override
    public Actor getActorByActor_Id(int actor_id) {
//		Optional<Actor> actor = actorRepository.findById(Actor_id);
//		if(actor.isPresent()) {
//			return actor.get();
//		}else {
//			throw new ResourceNotFoundException("Actor", "actor_id", actor_id);
//		}
        return actorRepository.findById(actor_id).orElseThrow(() ->
                new ResourceNotFoundException("Actor", "actor_id", actor_id));

    }

    @Override
    public Actor updateActor(Actor actor, int actor_id) {

        // we need to check whether actor with given id is exists in DB or not
        Actor existingActor = actorRepository.findById(actor_id).orElseThrow(
                () -> new ResourceNotFoundException("Actor", "actor_id", actor_id));

        existingActor.setFirst_name(actor.getFirst_name());
        existingActor.setLast_name(actor.getLast_name());

        // save existing actor to DB
        actorRepository.save(existingActor);
        return existingActor;
    }

    @Override
    public void deleteActor(int actor_id) {

        // check whether an actor exist in a DB or not
        actorRepository.findById(actor_id).orElseThrow(() ->
                new ResourceNotFoundException("Actor", "actor_id", actor_id));
        actorRepository.deleteById(actor_id);
    }




}