package com.project.pet.nuketyvkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NuketyvkarApplication {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("12345"));
        SpringApplication.run(NuketyvkarApplication.class, args);
    }

}
