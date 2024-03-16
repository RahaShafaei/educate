package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PrCourseDto;
import edu.educate.model.PrCourseEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.PrCourseGrpService;
import edu.educate.service.PrCourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class PrCourseController extends BaseController<PrCourseEntity, PrCourseDto> {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("prCourseGrp.ltTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCourseGrp.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("ltTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final PrCourseGrpService prCourseGrpService;

    public PrCourseController(PrCourseService prCourseService,
                              PrCourseGrpService prCourseGrpService) {
        super(PrCourseEntity.class,
                PrCourseDto.class,
                prCourseService,
                "course",
                "courseDir/course",
                null,
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.prCourseGrpService = prCourseGrpService;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("courseGrps", prCourseGrpService.getAllEntities());
    }
}
