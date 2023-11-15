package edu.educate.controller;

import edu.educate.model.PersonEntity;
import edu.educate.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

//    @GetMapping
//    public String person(Model model) {
//        model.addAttribute("persons", personService.getAllEntities());
//        return "personDir/personList";
//    }

    @GetMapping
    public String listPersons(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 30; // Number of items per page
        Page<PersonEntity> personPage = personService.getAllEntities(PageRequest.of(page, pageSize));

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
        if(result.hasErrors()){
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
    public String searchPersons(@ModelAttribute("searchPerson") PersonEntity searchPerson,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {
        int pageSize = 30; // Number of items per page
        Page<PersonEntity> personPage = personService.searchByAllFields(
                searchPerson,
                PageRequest.of(page, pageSize));

        model.addAttribute("persons", personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", personPage.getTotalPages());
        model.addAttribute("searchPerson", searchPerson);
        model.addAttribute("searchFlag", 1);
        return "personDir/personList";
    }
}
