package com.jamie.demo.controller;

import com.jamie.demo.model.Actor;
import com.jamie.demo.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(@RequestBody Actor actor){
        return actorService.saveActor(actor);
    }

    @GetMapping
    public List<Actor> getAllActors(){
        return actorService.getAllActors();
    }

    @GetMapping("{actor_id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("actor_id") int actor_id){
        return actorService.getActorById(actor_id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{actor_id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("actor_id") int actor_id,
                                                   @RequestBody Actor actor){
        return actorService.getActorById(actor_id)
                .map(savedActor -> {

                    savedActor.setFirstName(actor.getFirstName());
                    savedActor.setLastName(actor.getLastName());


                    Actor updatedActor = actorService.updateActor(savedActor);
                    return new ResponseEntity<>(updatedActor, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{actor_id}")
    public ResponseEntity<String> deleteActor(@PathVariable("actor_id") int actor_id){

        actorService.deleteActor(actor_id);

        return new ResponseEntity<String>("Actor deleted successfully!.", HttpStatus.OK);

    }

}
