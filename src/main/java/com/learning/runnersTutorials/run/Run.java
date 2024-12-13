package com.learning.runnersTutorials.run;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


//this should serve as a model
public record Run(
    Integer id,
    @NotEmpty
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    @Positive
    Integer miles,
    Location location
) {
    public Run {
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed on must be after started on");
        }
        if(title.isBlank()){
            throw new IllegalArgumentException("enter a value for the title...");
        }
    }
}