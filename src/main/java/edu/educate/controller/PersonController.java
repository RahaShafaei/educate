package edu.educate.controller;

import edu.educate.model.PersonEntity;
import edu.educate.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private static final int PAGE_SIZE = 30;

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
            .matchingAny()
            .withMatcher("fname", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("lname", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("fatherName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("nlCode", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("prCode", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("tel", ExampleMatcher.GenericPropertyMatchers.contains())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deleted", "deletedAt", "insertedAt");

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("fname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("lname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("fatherName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("nlCode", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prCode", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("tel", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deleted", "deletedAt", "insertedAt");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
//    @GetMapping
//    public String person(Model model) {
//        model.addAttribute("persons", personService.getAllEntities());
//        return "personDir/personList";
//    }

    @GetMapping
    public String listPersons(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<PersonEntity> personPage = personService.getAllEntities(PageRequest.of(page, PAGE_SIZE));

        model.addAttribute("persons", personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", personPage.getTotalPages());
        model.addAttribute("searchPerson", new PersonEntity());
        model.addAttribute("searchFlag", 0);
        return "personDir/personList";
    }

    @GetMapping("/new")
    public String newPersonForm(Model model) {
        model.addAttribute("person", new PersonEntity());
        return "personDir/personForm";
    }

    @PostMapping("/save")
    public String savePerson(@Valid @ModelAttribute("person") PersonEntity person,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "personDir/personForm";
        }
        personService.createEntity(person);
        return "redirect:/person";
    }

    @GetMapping("/edit/{id}")
    public String editPersonForm(@PathVariable Integer id, Model model) {
        model.addAttribute("person", personService.getEntity(id));
        return "personDir/personForm";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) {
        personService.deleteEntity(id);
        return "redirect:/person";
    }

    @PostMapping("/search")
    public String searchPersonsPost(@ModelAttribute PersonEntity searchPerson,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {
        System.out.println(":::::::::::::::::::::::::::::");
        System.out.println("page : " + page);
        System.out.println(":::::::::::::::::::::::::::::");
        searchPerson.setDeleted(false);
        Example<PersonEntity> example = Example.of(searchPerson, SEARCH_CONDITIONS_MATCH_ALL);

        Page<PersonEntity> personPage = personService.getAllEntities(example, PageRequest.of(page, PAGE_SIZE));

        model.addAttribute("persons", personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", personPage.getTotalPages());
        model.addAttribute("searchPerson", searchPerson);
        model.addAttribute("searchFlag", 1);
        return "personDir/personList";
    }

//    @GetMapping("/search")
//    public String searchPersonsGet(@ModelAttribute("searchPerson") PersonEntity searchPerson,
//                                    @RequestParam(defaultValue = "0") int page,
//                                    Model model) {
//
//        searchPerson.setDeleted(false);
//
//        System.out.println("::::::::::::::::::::::::::::");
//        System.out.println(searchPerson);
//        System.out.println("::::::::::::::::::::::::::::");
//        Example<PersonEntity> example = Example.of(searchPerson, SEARCH_CONDITIONS_MATCH_ALL);
//
//        Page<PersonEntity> personPage = personService.getAllEntities(example, PageRequest.of(page, PAGE_SIZE));
//
//        model.addAttribute("persons", personPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", personPage.getTotalPages());
//        model.addAttribute("searchPerson", searchPerson);
//        model.addAttribute("searchFlag", 1);
//        return "personDir/personList";
//    }
}
