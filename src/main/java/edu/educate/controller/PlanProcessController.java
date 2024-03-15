package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PlanProcessDto;
import edu.educate.helper.ExcelGenerator;
import edu.educate.model.PlanProcessEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.PlanProcessService;
import edu.educate.service.PlansService;
import edu.educate.service.ProcessService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/planProcess")
public class PlanProcessController extends BaseController<PlanProcessEntity, PlanProcessDto> {
    private Integer planId;
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("plan.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("process.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("ltFromDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("ltToDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");
    private final ProcessService processService;

    public PlanProcessController(PlanProcessService planProcessService,
                                 ProcessService processService
    ) {
        super(PlanProcessEntity.class,
                PlanProcessDto.class,
                planProcessService,
                "planProcess",
                "planProcessDir/planProcess",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.processService = processService;
    }

    @GetMapping("/newovr/{planId}")
    public String newForm(@PathVariable Integer planId, Model model) {
        this.planId=planId;
        this.modelSetting(model, new PlanProcessEntity());
        return viewPrefix + "Form";
    }

    @PostMapping("/saveovr")
    public String save(@Valid @ModelAttribute("entityObject") PlanProcessEntity entity,
                       BindingResult result,
                       Model model,
                       @RequestParam Integer planId) {
        if (result.hasErrors()) {
            this.planId=planId;
            this.modelSetting(model, entity);
            return viewPrefix + "Form";
        }
        ((PlanProcessService)service).createEntityByRelatedPlan(entity,planId);
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
        ExcelGenerator generator = new ExcelGenerator(((PlanProcessService) service).findByPlanId(planId));
        try {
            generator.generateExcelFile(excelSettings(response));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return "redirect:/plans/edit/" + planId;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("process", processService.getAllEntities());
    }

}
