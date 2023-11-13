package edu.educate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PersonController {
    @GetMapping("/person")
    public String home() {
        return "person";
    }
}
