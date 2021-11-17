package com.myGallary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GallaryApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,"
            + "classpath:aws.yml";

    public static void main(String[] args){
      //  SpringApplication.run(GallaryApplication.class, args);

        new SpringApplicationBuilder(GallaryApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);

    }
}
