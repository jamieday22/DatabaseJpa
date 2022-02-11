package com.jamie.demo.controller;

import java.util.List;

import com.jamie.demo.model.Actor;
import com.jamie.demo.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/actor")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        super();
        this.actorService = actorService;
    }

    // build create Actor REST API
    @PostMapping()
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor){
        return new ResponseEntity<Actor>(actorService.saveActor(actor), HttpStatus.CREATED);
    }

    // build get all Actor REST API
    @GetMapping
    public List<Actor> getAllActor(){
        return actorService.getAllActor();
    }

    // build get actor by id REST API
    // http://localhost:8080/api/langauge/1
    @GetMapping("{actor_id}")
    public ResponseEntity<Actor> getActorByActor_Id(@PathVariable("actor_id") int actor_id){
        return new ResponseEntity<Actor>(actorService.getActorByActor_Id(actor_id), HttpStatus.OK);
    }

    // build update actor REST API
    // http://localhost:8080/api/langauge/1
    @PutMapping("{actor_id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("actor_id") int actor_id
            ,@RequestBody Actor actor){
        return new ResponseEntity<Actor>(actorService.updateActor(actor, actor_id), HttpStatus.OK);
    }

    // build delete actor REST API
    // http://localhost:8080/api/actor/1
    @DeleteMapping("{actor_id}")
    public ResponseEntity<String> deleteActor(@PathVariable("actor_id") int actor_id){

        // delete Actor from DB
        actorService.deleteActor(actor_id);

        return new ResponseEntity<String>("Actor deleted successfully!.", HttpStatus.OK);
    }



}
