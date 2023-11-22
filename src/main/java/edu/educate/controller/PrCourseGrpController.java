package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.model.PrCourseGrpEntity;
import edu.educate.service.PrCourseGrpService;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coursegrp")
public class PrCourseGrpController extends BaseController<PrCourseGrpEntity> {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("ltTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    public PrCourseGrpController(PrCourseGrpService prCourseGrpService) {
        super(PrCourseGrpEntity.class,
                prCourseGrpService,
                "coursegrp",
                "courseDir/courseGrp",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);
    }
}
