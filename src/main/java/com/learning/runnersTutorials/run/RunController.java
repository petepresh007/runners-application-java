package com.learning.runnersTutorials.run;


//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;



@RestController
//we can set a request mapping at the controllers instead of setting it individually
@RequestMapping("/api/runs")
public class RunController {
    //request annotation
    // @GetMapping("/")
    // public String home(){
    //     return "Welcome to the application";
    // }
    

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }


    //get run
    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }


    // //finding by id
    // @GetMapping("/{id}")
    // Run findById(@PathVariable Integer id){
    //     return runRepository.findByid(id);
    // }

    //finding by id with optional
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run =  runRepository.findByid(id);
        if(run.isEmpty()){
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new RunNotFoundException();
        }
        return run.get();
    }


    //create post
    // @ResponseStatus(HttpStatus.CREATED)
    // @PostMapping("/create")
    // void create(@RequestBody Run run){
    //     Optional<Run> existingRun = runRepository.findOne(run.title());
    //     if(existingRun.isPresent()){
    //         throw new ResponseStatusException(HttpStatus.CONFLICT);
    //     }

    //     if(run.title() == null || run.title().isEmpty()){
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    //     }

    //     if(run.startedOn() == null){
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    //     }

    //     if(run.completedOn() == null){
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    //     }
    //     runRepository.create(run);
    // }

    //create two
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody Run run){
        Optional<Run> existinRun = runRepository.findOne(run.title());

        if(existinRun.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A run with the title exist already");
        }
        if(run.title() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please enter a title");
        }

        if(run.startedOn() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter a start date");
        }

        if(run.completedOn() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter an end date");
        }


        //create run from users
        Run createdRun = runRepository.create(run);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse("Run created successfully", createdRun));
    }


    //update
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run, id);
    }


    //delete
    // @ResponseStatus(HttpStatus.OK)
    // @DeleteMapping("/del/{id}")
    // void delete(@PathVariable Integer id){
    //     runRepository.delete(id);
    // }


    //delete
    @DeleteMapping("/del/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id){
        boolean isDel =  runRepository.delete(id);
        if(!isDel){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Run with id: "+ id +" does not exist");
        }
        return ResponseEntity.ok("run deleted successfully...");
    }
}