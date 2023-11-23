package edu.educate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class OrgUnitController {
    @GetMapping("/orgUnit")
    public String orgUnit() {
        return "orgUnitList";
    }
}
