package edu.educate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PrCourseController {
    @GetMapping("/course")
    public String home() {
        return "course";
    }
}
