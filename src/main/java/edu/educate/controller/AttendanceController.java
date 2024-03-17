package edu.educate.controller;


import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.AttendanceDto;
import edu.educate.helper.ExcelGenerator;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.ElementEntity;
import edu.educate.model.ElementGrpEntity;
import edu.educate.model.PlansEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.AttendanceService;
import edu.educate.service.ElementService;
import edu.educate.service.PersonService;
import edu.educate.service.PlansService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/attendance")
public class AttendanceController extends BaseController<AttendanceEntity, AttendanceDto> {
    private Integer planId;
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
                null,
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.plansService = plansService;
        this.personService = personService;
        this.elementService = elementService;
    }

    @GetMapping("/newovr/{planId}")
    public String newForm(@PathVariable Integer planId, Model model) {
        this.planId=planId;
        this.modelSetting(model, new AttendanceEntity());
        return viewPrefix + "Form";
    }

    @PostMapping("/saveovr")
    public String save(@Valid @ModelAttribute("entityObject") AttendanceEntity entity,
                       BindingResult result,
                       Model model,
                       @RequestParam Integer planId) {
        if (result.hasErrors()) {
            this.planId=planId;
            this.modelSetting(model, entity);
            return viewPrefix + "Form";
        }
        ((AttendanceService)service).createEntityByRelatedPlan(entity,planId);
        return "redirect:/plans/edit/" + planId;
    }

    @GetMapping("/editovr/{planId}/{id}")
    public String editForm(@PathVariable Integer planId, @PathVariable Integer id, Model model) {
        this.planId=planId;
        this.modelSetting(model, (BaseEntity) service.getEntity(id));
        return viewPrefix + "Form";
    }

    @GetMapping("/deleteovr/{planId}/{id}")
    public String delete(@PathVariable Integer id, @PathVariable Integer planId) {
        service.deleteEntity(id);
        return "redirect:/plans/edit/" + planId;
    }

    @GetMapping("/export-to-excelovr/{planId}")
    public String exportIntoExcelFile(@PathVariable Integer planId, HttpServletResponse response) {
        ExcelGenerator generator = new ExcelGenerator(((AttendanceService) service).findByPlanId(planId));
        try {
            generator.generateExcelFile(excelSettings(response));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return "redirect:/plans/edit/" + planId;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("planId", this.planId);
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("persons", personService.findAllByOrgUnitId(this.planId));
        model.addAttribute("elements", elementService.findByElementGrpLtTitle("attendance_status"));
    }

}
