package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.OrgPostDto;
import edu.educate.model.OrgPostEntity;
import edu.educate.service.OrgPostService;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orgpost")
public class OrgPostController extends BaseController<OrgPostEntity, OrgPostDto> {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("descr", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    public OrgPostController(OrgPostService orgPostService) {
        super(OrgPostEntity.class,
                OrgPostDto.class,
                orgPostService,
                "orgpost",
                "orgPostDir/orgPost",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);
    }
}
