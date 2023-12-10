package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PersonDto;
import edu.educate.dto.baseDto.BaseDto;
import edu.educate.model.PersonEntity;
import edu.educate.service.OrgPostService;
import edu.educate.service.OrgUnitService;
import edu.educate.service.PersonService;
import edu.educate.service.RolesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonController extends BaseController<PersonEntity,PersonDto> {
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("fatherName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("nlCode", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCode", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("tel", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final RolesService rolesService;
    private final OrgPostService orgPostService;
    private final OrgUnitService orgUnitService;

    public PersonController(PersonService personService,
                            RolesService rolesService,
                            OrgPostService orgPostService,
                            OrgUnitService orgUnitService) {
        super(PersonEntity.class,
                PersonDto.class,
                personService,
                "person",
                "personDir/person",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.rolesService = rolesService;
        this.orgUnitService = orgUnitService;
        this.orgPostService = orgPostService;
    }

    @PostMapping("/saveovr")
    public String savePerson(@Valid @ModelAttribute("entityObject") PersonDto person,
                             BindingResult result,
                             @RequestParam(value = "roles", required = false) List<Integer> rolesId,
                             Model model) {

        boolean fromDateFlag = super.service.entityValidation(person);

        if (result.hasErrors() || rolesId == null || !fromDateFlag) {
            modelSettingDto(model, person);
            model.addAttribute("rolesId", rolesId);
            model.addAttribute("rolesFlag", rolesId == null ? 1 : 0);
            model.addAttribute("fromDateFlag", !fromDateFlag ? 1 : 0);
            return "personDir/personForm";
        }

        if (person.getOrgUnitPostPersonWrapper().getLtToDate() != null) {
            person.setRolesWrapper(rolesId);
            modelSettingDto(model, (PersonDto) super.service.createEntityByRelatedEntities(person));
            return "personDir/personForm";
        }

        person.setRolesWrapper(rolesId);
        super.service.createEntityByRelatedEntities(person);
        return "redirect:/person";
    }

    @Override
    public void modelSettingDto(Model model, BaseDto baseDto) {
        model.addAttribute("entityObject", (PersonDto)baseDto);
        model.addAttribute("roles", rolesService.getAllEntities());
        model.addAttribute("posts", orgPostService.getAllEntities());
        model.addAttribute("orgUnits", orgUnitService.getAllEntities());
        model.addAttribute("orgUnitsPosts",((PersonDto)baseDto).getPersonWrapper() != null ?  ((PersonDto)baseDto).getPersonWrapper().getOrgUnitPostPersons() : null);
    }
}
