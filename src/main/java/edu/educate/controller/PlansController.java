package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PersonDto;
import edu.educate.dto.PlansDto;
import edu.educate.dto.baseDto.BaseDto;
import edu.educate.model.*;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/plans")
public class PlansController extends BaseController<PlansEntity, PlansDto> {
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("orgUnit.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCourse.prCourseGrp.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCourse.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("person.fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("person.lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementType.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementStatus.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("fromDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("toDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final OrgUnitService orgUnitService;
    private final PrCourseService prCourseService;
    private final PersonService personService;
    private final ElementService elementService;

    public PlansController(PlansService plansService,
                           OrgUnitService orgUnitService,
                           PrCourseService prCourseService,
                           PersonService personService,
                           ElementService elementService
    ) {
        super(PlansEntity.class,
                PlansDto.class,
                plansService,
                "plans",
                "plansDir/plans",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.orgUnitService = orgUnitService;
        this.prCourseService = prCourseService;
        this.personService = personService;
        this.elementService = elementService;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("attendances", ((PlansEntity)baseEntity).getAttendances());
        model.addAttribute("orgUnits", orgUnitService.getAllEntities());
        model.addAttribute("courses", prCourseService.getAllEntities());

        PersonEntity personEntity = new PersonEntity();
        RolesEntity rolesEntity=new RolesEntity();
        rolesEntity.setId(3);
        personEntity.setPersonRoles(new ArrayList<>());
        personEntity.getPersonRoles().add(rolesEntity);
        model.addAttribute("persons", personService.findEntitiesBySpecificFields(personEntity));

        ElementEntity elementType = new ElementEntity();
        ElementGrpEntity elementGrpType = new ElementGrpEntity();
        elementGrpType.setId(3);
        elementType.setElementGrp(elementGrpType);
        model.addAttribute("elementTypes", elementService.findEntitiesBySpecificFields(elementType));

        ElementEntity elementStatus = new ElementEntity();
        ElementGrpEntity elementGrpStatus = new ElementGrpEntity();
        elementGrpStatus.setId(2);
        elementStatus.setElementGrp(elementGrpStatus);
        model.addAttribute("elementStatus", elementService.findEntitiesBySpecificFields(elementStatus));
    }
}
