package com.myGallary;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GallaryApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,"
            + "classpath:level.properties";

    public static void main(String[] args){

        new SpringApplicationBuilder(GallaryApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);

    }
}
