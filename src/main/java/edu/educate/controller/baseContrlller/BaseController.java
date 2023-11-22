package edu.educate.controller.baseContrlller;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.baseService.GenericService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
public abstract class BaseController<T> {

    protected final Class<T> entity;

    protected final GenericService<T> service;

    protected final String modelAttribute;

    protected final String viewPrefix;

    protected final int pageSize;

    protected final ExampleMatcher exampleMatcher;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<T> entityPage = service.getAllEntities(PageRequest.of(page, pageSize));
        model.addAttribute(modelAttribute + "s", entityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", entityPage.getTotalPages());
        model.addAttribute("search" + modelAttribute, service.createEmptyEntity(entity));
        model.addAttribute("searchFlag", 0);
        return viewPrefix + "List";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("entityObject", service.createEmptyEntity(entity));
        return viewPrefix + "Form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("entityObject") T entity,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            return viewPrefix + "Form";
        }
        service.createEntity(entity);
        return "redirect:/" + modelAttribute;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("entityObject", service.getEntity(id));
        return viewPrefix + "Form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteEntity(id);
        return "redirect:/" + modelAttribute;
    }

    @PostMapping("/search")
    public String searchPost(@ModelAttribute T searchEntity,
                             @RequestParam String action,
                             Model model) {
        int page = Integer.parseInt(action);
        ((BaseEntity) searchEntity).setDeleted(false);
        Example<T> example = Example.of(searchEntity, exampleMatcher);

        Page<T> entityPage = service.getAllEntities(example, PageRequest.of(page, pageSize));

        model.addAttribute(modelAttribute + "s", entityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", entityPage.getTotalPages());
        model.addAttribute("search" + modelAttribute, searchEntity);
        model.addAttribute("searchFlag", 1);
        return viewPrefix + "List";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}

