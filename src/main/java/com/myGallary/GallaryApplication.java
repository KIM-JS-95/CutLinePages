package com.myGallary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GallaryApplication {
    public static void main(String[] args){
        SpringApplication.run(GallaryApplication.class, args);
    }
}
