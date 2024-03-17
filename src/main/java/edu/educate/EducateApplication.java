package edu.educate;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class EducateApplication {

    public static void main(String[] args) {

        SpringApplication.run(EducateApplication.class, args);
        System.out.println("Hello World!");

        Resource resource = new ClassPathResource("ValidationMessages.properties");
        if (resource.exists()) {
            System.out.println(":::::::::::::::::  resource.exists()   : " + resource.exists());
        }

    }

}