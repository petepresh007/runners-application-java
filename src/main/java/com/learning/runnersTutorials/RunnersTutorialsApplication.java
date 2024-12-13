package com.learning.runnersTutorials;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learning.runnersTutorials.run.Location;
import com.learning.runnersTutorials.run.Run;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;



@SpringBootApplication
public class RunnersTutorialsApplication {

	//create a logger
	private static final Logger  log = LoggerFactory.getLogger(RunnersTutorialsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnersTutorialsApplication.class, args);
		
		//logger
		log.info("Application started successfully...");
	}


	//creating a commandline runnner (runs after the application & after the application context have been created)
	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1, 
			"First run", 
			LocalDateTime.now(), 
			LocalDateTime.now().plus(1, ChronoUnit.HOURS), 
			5, 
			Location.OUTDOOR);
			log.info("Run: " + run);
		};
	}
}