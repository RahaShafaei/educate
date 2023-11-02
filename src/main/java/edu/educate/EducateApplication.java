package edu.educate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;


@SpringBootApplication
public class EducateApplication {

    public static void main(String[] args) {

        SpringApplication.run(EducateApplication.class, args);
        System.out.println("Hello World!");

        Resource resource = new ClassPathResource("eduMessages/eduMessage.properties");
        if (resource.exists()) {
            System.out.println(":::::::::::::::::     : " );
        }
    }

}