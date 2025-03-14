package com.jenkins.maven.assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalTime;

@SpringBootApplication
public class PreethamJenkinsMavenProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreethamJenkinsMavenProjectApplication.class, args);

        LocalTime currentTime = LocalTime.now();
        String name = "Teena Abraham"; 

        String greeting;
        if (currentTime.isBefore(LocalTime.NOON)) {
            greeting = "Good morning";
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            greeting = "Good afternoon";
        } else if (currentTime.isBefore(LocalTime.of(20, 0))) {
            greeting = "Good evening";
        } else {
            greeting = "Good night";
        }

        System.out.println(greeting + ", " + name + ", Welcome to COMP367");
    }
}
