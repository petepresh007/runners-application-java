package com.learning.runnersTutorials;
import org.springframework.stereotype.Component;


@Component
public class WelcomeMessage {
    public String sendWelcomeMessage(){
        return "Welcome my friend";
    }
}