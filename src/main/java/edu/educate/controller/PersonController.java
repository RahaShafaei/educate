package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.PersonDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonController extends BaseController<PersonEntity> {
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
                personService,
                "person",
                "personDir/person",
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.rolesService = rolesService;
        this.orgUnitService = orgUnitService;
        this.orgPostService = orgPostService;
    }

    @GetMapping("/newovr")
    public String newPersonForm(Model model) {
        modelSetting(model, new PersonDto());
        return "personDir/personForm";
    }

    @PostMapping("/saveovr")
    public String savePerson(@Valid @ModelAttribute("entityObject") PersonDto person,
                             BindingResult result,
                             @RequestParam(value = "roles", required = false) List<Integer> rolesId,
                             Model model) {

        boolean fromDateFlag = super.service.entityValidation(person);

        if (result.hasErrors() || rolesId == null || !fromDateFlag) {
            modelSetting(model, person);
            model.addAttribute("rolesId", rolesId);
            model.addAttribute("rolesFlag", rolesId == null ? 1 : 0);
            model.addAttribute("fromDateFlag", !fromDateFlag ? 1 : 0);

            return "personDir/personForm";
        }

        if (person.getOrgUnitPostPersonWrapper().getToDate() != null) {
            person.setRolesWrapper(rolesId);
            modelSetting(model, (PersonDto) super.service.createEntityByRelatedEntities(person));
            return "personDir/personForm";
        }

        person.setRolesWrapper(rolesId);
        super.service.createEntityByRelatedEntities(person);
        return "redirect:/person";
    }

    @GetMapping("/editovr/{id}")
    public String editPersonForm(@PathVariable Integer id, Model model) {
        modelSetting(model, (PersonDto) super.service.getEntityByRelatedEntities(id));
        return "personDir/personForm";
    }

    private void modelSetting(Model model, PersonDto personDto) {
        model.addAttribute("entityObject", personDto);
        model.addAttribute("roles", rolesService.getAllEntities());
        model.addAttribute("posts", orgPostService.getAllEntities());
        model.addAttribute("orgUnits", orgUnitService.getAllEntities());
        model.addAttribute("orgUnitsPosts",personDto.getPersonWrapper() != null ?  personDto.getPersonWrapper().getOrgUnitPostPersons() : null);
    }
}
