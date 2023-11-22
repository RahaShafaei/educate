package edu.educate.controller;

import edu.educate.model.PrCourseGrpEntity;
import edu.educate.service.PrCourseGrpService;
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
@RequestMapping("/coursegrp")
public class PrCourseGrpController {

    private final PrCourseGrpService prCourseGrpService;

    private static final int PAGE_SIZE = 30;

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
            .matchingAny()
            .withMatcher("ltTitle", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("prTitle", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("ltTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("prTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String listCourseGrp(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<PrCourseGrpEntity> prCourseGrpEntityPage = prCourseGrpService.getAllEntities(PageRequest.of(page, PAGE_SIZE));

        model.addAttribute("courseGrps", prCourseGrpEntityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prCourseGrpEntityPage.getTotalPages());
        model.addAttribute("searchCourseGrp", new PrCourseGrpEntity());
        model.addAttribute("searchFlag", 0);
        return "courseDir/courseGrpList";
    }

    @GetMapping("/new")
    public String newCourseGrpForm(Model model) {
        model.addAttribute("courseGrp", new PrCourseGrpEntity());
        return "courseDir/courseGrpForm";
    }

    @PostMapping("/save")
    public String saveCourseGrp(@Valid @ModelAttribute("courseGrp") PrCourseGrpEntity PrCourseGrpEntity,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "courseDir/courseGrpForm";
        }
        prCourseGrpService.createEntity(PrCourseGrpEntity);
        return "redirect:/coursegrp";
    }

    @GetMapping("/edit/{id}")
    public String editCourseGrpForm(@PathVariable Integer id, Model model) {
        model.addAttribute("courseGrp", prCourseGrpService.getEntity(id));
        return "courseDir/courseGrpForm";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) {
        prCourseGrpService.deleteEntity(id);
        return "redirect:/coursegrp";
    }

    @PostMapping("/search")
    public String searchPersonsPost(@ModelAttribute PrCourseGrpEntity searchCourseGrp,
                                    @RequestParam String action,
                                    Model model) {
        int page = Integer.parseInt(action);
        searchCourseGrp.setDeleted(false);
        Example<PrCourseGrpEntity> example = Example.of(searchCourseGrp, SEARCH_CONDITIONS_MATCH_ALL);

        Page<PrCourseGrpEntity> courseGrpPage = prCourseGrpService.getAllEntities(example, PageRequest.of(page, PAGE_SIZE));

        model.addAttribute("courseGrps", courseGrpPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", courseGrpPage.getTotalPages());
        model.addAttribute("searchCourseGrp", searchCourseGrp);
        model.addAttribute("searchFlag", 1);
        return "courseDir/courseGrpList";
    }
}
