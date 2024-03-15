package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PlansDto;
import edu.educate.model.*;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            .withMatcher("elementPhase.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementStatus.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementEdu.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementProject.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("elementHolding.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("ltFromDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//            .withMatcher("ltToDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("planLink", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt", "prFromDate", "prToDate");

    private final PlanProcessService planProcessService;
    private final LocationService locationService;
    private final OrgUnitService orgUnitService;
    private final PrCourseGrpService prCourseGrpService;
    private final PrCourseService prCourseService;
    private final PersonService personService;
    private final ElementService elementService;

    public PlansController(PlansService plansService,
                           OrgUnitService orgUnitService,
                           LocationService LocationService,
                           PlanProcessService planProcessService,
                           PrCourseGrpService prCourseGrpService,
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

        this.locationService = LocationService;
        this.planProcessService = planProcessService;
        this.orgUnitService = orgUnitService;
        this.prCourseGrpService = prCourseGrpService;
        this.prCourseService = prCourseService;
        this.personService = personService;
        this.elementService = elementService;
    }

    @PostMapping("/saveovr")
    public String savePerson(@Valid @ModelAttribute("entityObject") PlansEntity plan,
                             BindingResult result,
                             @RequestParam("file") MultipartFile[] files,
                             Model model) {

        if (result.hasErrors()) {
            modelSetting(model, plan);
            return "plansDir/plansForm";
        }

        ((PlansService) service).createEntityByRelatedFiles(plan, files);
        return "redirect:/plans";
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);

        if (((PlansEntity) baseEntity).getId() != null )
            model.addAttribute("planProcess", planProcessService.findByPlanId(((PlansEntity) baseEntity).getId()));

        model.addAttribute("attendances", ((PlansEntity) baseEntity).getAttendances());
        model.addAttribute("meetings", ((PlansEntity) baseEntity).getMeetings());
        model.addAttribute("locations", locationService.getAllEntities());
        model.addAttribute("orgUnits", orgUnitService.getAllEntities());
        model.addAttribute("parentOrgUnits", orgUnitService.findByElementTypeId());
//        model.addAttribute("parentOrgUnits", orgUnitService.findByParentOrgUnitIsNull());
        model.addAttribute("courseGrps", prCourseGrpService.getAllEntities());
        model.addAttribute("courses", prCourseService.getAllEntities());

        PersonEntity personEntity = new PersonEntity();
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setId(3);
        personEntity.setPersonRoles(new ArrayList<>());
        personEntity.getPersonRoles().add(rolesEntity);
        model.addAttribute("persons", personService.findEntitiesBySpecificFields(personEntity));

        PersonEntity personSupervisor = new PersonEntity();
        RolesEntity personSupervisorRoles = new RolesEntity();
        personSupervisorRoles.setId(9);
        personSupervisor.setPersonRoles(new ArrayList<>());
        personSupervisor.getPersonRoles().add(personSupervisorRoles);
        model.addAttribute("personSupervisors", personService.findEntitiesBySpecificFields(personSupervisor));

        model.addAttribute("elementTypes", elementService.findEntitiesBySpecificFields(elementEntityConfiguration(3)));
        model.addAttribute("elementStatus", elementService.findEntitiesBySpecificFields(elementEntityConfiguration(2)));
        model.addAttribute("elementEdu", elementService.findEntitiesBySpecificFields(elementEntityConfiguration(4)));
        model.addAttribute("elementProject", elementService.findEntitiesBySpecificFields(elementEntityConfiguration(5)));
        model.addAttribute("elementHolding", elementService.findEntitiesBySpecificFields(elementEntityConfiguration(6)));
        model.addAttribute("elementPhase", elementService.findEntitiesBySpecificFields(elementEntityConfiguration(8)));
    }

    private ElementEntity elementEntityConfiguration(int i){
        ElementEntity elementProject = new ElementEntity();
        ElementGrpEntity elementGrpProject = new ElementGrpEntity();
        elementGrpProject.setId(i);
        elementProject.setElementGrp(elementGrpProject);
        return elementProject;
    }
}
