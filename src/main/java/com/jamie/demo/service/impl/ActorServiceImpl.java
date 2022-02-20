package com.jamie.demo.service.impl;

import com.jamie.demo.model.Actor;
import com.jamie.demo.repository.ActorRepository;
import com.jamie.demo.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<Actor> getActorById(int actor_id) {
        return actorRepository.findById(actor_id);
    }

    @Override
    public Actor updateActor(Actor updatedActor) {
        return actorRepository.save(updatedActor);
    }

    @Override
    public void deleteActor(int actor_id) {
        actorRepository.deleteById(actor_id);
    }
}
