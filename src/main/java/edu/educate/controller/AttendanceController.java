package edu.educate.controller;


import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.AttendanceDto;
import edu.educate.dto.PlansDto;
import edu.educate.model.*;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.*;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/attendance")
public class AttendanceController extends BaseController<AttendanceEntity, AttendanceDto> {
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("person.fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("person.lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("element.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("plan.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final PlansService plansService;
    private final PersonService personService;
    private final ElementService elementService;

    public AttendanceController(AttendanceService attendanceService,
                                PlansService plansService,
                                PersonService personService,
                                ElementService elementService
    ) {
        super(AttendanceEntity.class,
                AttendanceDto.class,
                attendanceService,
                "attendance",
                "attendanceDir/attendance",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.plansService = plansService;
        this.personService = personService;
        this.elementService = elementService;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("persons", personService.getAllEntities());

        PlansEntity plansEntity = new PlansEntity();
        ElementEntity elementEntity = new ElementEntity();
        elementEntity.setId(3);
        plansEntity.setElementStatus(elementEntity);
        model.addAttribute("plans", plansService.findEntitiesBySpecificFields(plansEntity));

        ElementEntity elementType = new ElementEntity();
        ElementGrpEntity elementGrpType = new ElementGrpEntity();
        elementGrpType.setId(1);
        elementType.setElementGrp(elementGrpType);
        model.addAttribute("elements", elementService.findEntitiesBySpecificFields(elementType));
    }
}
