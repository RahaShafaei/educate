package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.AssessmentDto;
import edu.educate.model.AssessmentEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.AssessmentService;
import edu.educate.service.PlansService;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assessment")
public class AssessmentController extends BaseController<AssessmentEntity, AssessmentDto> {
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("plan.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("plan.prCourse.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("plan.prFromDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("plan.person.fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("plan.person.lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("question", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("score", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final PlansService plansService;
    public AssessmentController(AssessmentService assessmentService,
                                PlansService plansService
    ) {
        super(AssessmentEntity.class,
                AssessmentDto.class,
                assessmentService,
                "assessment",
                "assessmentDir/assessment",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.plansService = plansService;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("plans", plansService.getAllEntities());
    }
}
