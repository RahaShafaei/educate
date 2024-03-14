package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.ProcessDto;
import edu.educate.model.ProcessEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.PrCourseGrpService;
import edu.educate.service.PrCourseService;
import edu.educate.service.ProcessService;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController<ProcessEntity, ProcessDto> {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("prCourse.ltTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCourse.prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("ltTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final PrCourseService prCourseService;
    private final PrCourseGrpService prCourseGrpService;

    public ProcessController(ProcessService processService,
                             PrCourseService prCourseService,
                             PrCourseGrpService prCourseGrpService) {
        super(ProcessEntity.class,
                ProcessDto.class,
                processService,
                "process",
                "processDir/process",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.prCourseService = prCourseService;
        this.prCourseGrpService = prCourseGrpService;
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("courses", prCourseService.getAllEntities());
        model.addAttribute("courseGrps", prCourseGrpService.getAllEntities());
    }
}
