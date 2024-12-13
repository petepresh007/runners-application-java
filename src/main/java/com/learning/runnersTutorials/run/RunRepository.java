package com.learning.runnersTutorials.run;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;


@Repository
public class RunRepository {
    //creating a memory for the controllers
    private List<Run> runs = new ArrayList<>();
    
    //get runs
    List<Run> findAll(){
        return runs;
    }

    //get runs by id
    // Run findByid(Integer id){
    //     return runs.stream()
    //         .filter(run -> run.id() == id)
    //         .findFirst().get();
    // }


    //get run by id Optional
    Optional<Run> findByid(Integer id){
        return runs.stream()
            .filter(run -> run.id() == id)
            .findFirst();
    }


    //get runs by name
    Optional<Run> findOne(String title){
        return runs.stream()
            .filter(run -> run.title().equals(title))
            .findFirst();
    }


    //create run
    // void create(Run run){
    //     runs.add(run);
    // }

    Run create(Run run){
        runs.add(run);
        return run;
    }


    //update run
    void update(Run run, Integer id){
        Optional<Run> existingRun = findByid(id);
        if(existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    //update with second methods
    // void update(Run run, Integer id) {
    //     Optional<Run> existingRun = findByid(id);
    //     if (existingRun.isPresent()) {
    //         Run currentRun = existingRun.get();
    
    //         // Update only the non-null fields
    //         if (run.title() != null) {
    //             currentRun.setTitle(run.title());
    //         }
    //         if (run.startedOn() != null) {
    //             currentRun.setStartedOn(run.startedOn());
    //         }
    //         if (run.completedOn() != null) {
    //             currentRun.setCompletedOn(run.completedOn());
    //         }
    
    //         // Update the list
    //         runs.set(runs.indexOf(currentRun), currentRun);
    //     } else {
    //         throw new IllegalArgumentException("Run with ID " + id + " not found.");
    //     }
    // }
    


    //delete run
    boolean delete(Integer id){
        return runs.removeIf(run -> run.id().equals(id));
    }
    

    @PostConstruct
    private void init(){
        runs.add(new Run(
            1, 
            "First Title", 
            LocalDateTime.now(), 
            LocalDateTime.now().plus(1, ChronoUnit.HOURS), 
            5, 
            Location.INDOOR
            ));

            runs.add(new Run(
                2, 
                "Second Title", 
                LocalDateTime.now(), 
                LocalDateTime.now().plus(1, ChronoUnit.HOURS), 
                2, 
                Location.OUTDOOR
            ));
    }
}
