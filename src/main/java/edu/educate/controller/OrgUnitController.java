package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.OrgUnitDto;
import edu.educate.model.OrgUnitEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.OrgUnitService;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orgunit")
public class OrgUnitController extends BaseController<OrgUnitEntity, OrgUnitDto> {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("parentOrgUnit.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("descr", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    public OrgUnitController(OrgUnitService orgPostService) {
        super(OrgUnitEntity.class,
                OrgUnitDto.class,
                orgPostService,
                "orgunit",
                "orgUnitDir/orgUnit",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);
    }

    @Override
    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
        model.addAttribute("parents", service.getAllEntities());
    }
}
