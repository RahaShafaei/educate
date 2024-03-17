package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PlansDto;
import edu.educate.dto.PlansMapper;
import edu.educate.dto.baseDto.BaseDto;
import edu.educate.helper.ExcelGenerator;
import edu.educate.model.*;
import edu.educate.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController<PlansEntity, PlansDto> {
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("orgUnit.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("location.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCourse.prCourseGrp.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCourse.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("person.fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("person.lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("personSupervisor.fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("personSupervisor.lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementPhase.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementStatus.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementEdu.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementProject.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementHolding.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("method", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("ltFromDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//            .withMatcher("ltToDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("planLink", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt", "prFromDate", "prToDate");

    private final ProcessService processService;
    private final LocationService locationService;
    private final OrgUnitService orgUnitService;
    private final PrCourseGrpService prCourseGrpService;
    private final PrCourseService prCourseService;
    private final PersonService personService;
    private final ElementService elementService;

    private final PlansMapper plansMapper;

    public ReportController(PlansService plansService,
                            ProcessService processService, OrgUnitService orgUnitService,
                            LocationService LocationService,
                            PrCourseGrpService prCourseGrpService,
                            PrCourseService prCourseService,
                            PersonService personService,
                            ElementService elementService,
                            PlansMapper plansMapper) {
        super(PlansEntity.class,
                PlansDto.class,
                plansService,
                "report",
                "reportDir/report",
                "report/newd",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);
        this.processService = processService;
        this.locationService = LocationService;
        this.orgUnitService = orgUnitService;
        this.prCourseGrpService = prCourseGrpService;
        this.prCourseService = prCourseService;
        this.personService = personService;
        this.elementService = elementService;
        this.plansMapper = plansMapper;
    }

    @Override
    public void getSearchExcelResults(PlansDto searchEntity, Model model, HttpServletResponse response) {
        PlansEntity plansEntity = plansMapper.toEntity(searchEntity);

        Example<PlansEntity> example = Example.of(plansEntity, exampleMatcher);

        List<PlansEntity> plans = service.findEntities(example);
        if (plans != null && !plans.isEmpty()) {
            ExcelGenerator generator = new ExcelGenerator(service.findEntities(example));
            try {
                generator.generateExcelFile(excelSettings(response));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void modelSettingDto(Model model, BaseDto baseDto) {
        model.addAttribute("entityObject", baseDto);

        // Process**************************************
        model.addAttribute("process", processService.getAllEntities());

        // Attendance**************************************
        model.addAttribute("attendancePersons", personService.getAllEntities());

        ElementEntity elementType = new ElementEntity();
        ElementGrpEntity elementGrpType = new ElementGrpEntity();
        elementGrpType.setId(1);
        elementType.setElementGrp(elementGrpType);
        model.addAttribute("attendanceElements", elementService.findEntitiesBySpecificFields(elementType));

        // Plans**************************************
        model.addAttribute("locations", locationService.getAllEntities());
        model.addAttribute("orgUnits", orgUnitService.getAllEntities());
        model.addAttribute("parentOrgUnits", orgUnitService.findByElementTypeId());
        model.addAttribute("courseGrps", prCourseGrpService.getAllEntities());
        model.addAttribute("courses", prCourseService.getAllEntities());
        model.addAttribute("persons", personService.findByPersonRolesLtTitle("teacher"));
        model.addAttribute("personSupervisors", personService.findByPersonRolesLtTitle("supervisor"));
        model.addAttribute("elementStatus", elementService.findByElementGrpLtTitle("plan_status"));
        model.addAttribute("elementEdu", elementService.findByElementGrpLtTitle("edu_type"));
        model.addAttribute("elementProject", elementService.findByElementGrpLtTitle("project_type"));
        model.addAttribute("elementHolding", elementService.findByElementGrpLtTitle("holding_type"));
        model.addAttribute("elementPhase", elementService.findByElementGrpLtTitle("edu_phase"));
    }

}
