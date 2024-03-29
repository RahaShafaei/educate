package edu.educate.controller.baseContrlller;

import edu.educate.dto.baseDto.BaseDto;
import edu.educate.helper.ExcelGenerator;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.service.baseService.GenericService;
import jakarta.servlet.http.HttpServletResponse;
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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public abstract class BaseController<T, R> {

    protected final Class<T> entity;

    protected final Class<R> dto;

    protected final GenericService<T, R> service;

    protected final String modelAttribute;

    protected final String viewPrefix;

    protected final String mainPage;

    protected final int pageSize;

    protected final ExampleMatcher exampleMatcher;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        if (mainPage != null)
            return "redirect:/" + mainPage;

        Page<T> entityPage = service.getAllEntities(PageRequest.of(page, pageSize));
        model.addAttribute(modelAttribute + "s", entityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", entityPage.getTotalPages());
        model.addAttribute("search" + modelAttribute, service.createEmptyEntity(entity));
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("searchFlag", 0);
        return viewPrefix + "List";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        this.modelSetting(model, (BaseEntity) service.createEmptyEntity(entity));
        return viewPrefix + "Form";
    }

    @GetMapping("/newd")
    public String newFormDto(Model model) {
        this.modelSettingDto(model, (BaseDto) service.createEmptyDto(dto));
        return viewPrefix + "Form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("entityObject") T entity,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            this.modelSetting(model, (BaseEntity) entity);
            return viewPrefix + "Form";
        }
        service.createEntity(entity);
        return "redirect:/" + modelAttribute;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        this.modelSetting(model, (BaseEntity) service.getEntity(id));
        return viewPrefix + "Form";
    }

    @GetMapping("/editd/{id}")
    public String editPersonForm(@PathVariable Integer id, Model model) {
        this.modelSettingDto(model, service.getEntityByRelatedEntities(id));
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
                             HttpServletResponse response,
                             Model model) {
        int page = Integer.parseInt(action);
        if (page >= 0) {
            getSearchResults(searchEntity, model, page);
        } else {
            getSearchExcelResults(searchEntity, model, 0, response);
        }

        if (mainPage != null)
            return "redirect:/" + mainPage;

        return viewPrefix + "List";
    }

    @PostMapping("/searchd")
    public String searchPostDto(@ModelAttribute R searchEntity,
                                HttpServletResponse response,
                                Model model) {
        getSearchExcelResults(searchEntity, model, response);

        if (mainPage != null)
            return "redirect:/" + mainPage;

        return viewPrefix + "List";
    }

    @GetMapping("/export-to-excel")
    public String exportIntoExcelFile(HttpServletResponse response) {
        List<T> baseEntities = service.getAllEntities();
        if (baseEntities != null && !baseEntities.isEmpty()){
            ExcelGenerator generator = new ExcelGenerator(service.getAllEntities());
            try {
                generator.generateExcelFile(excelSettings(response));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }

        if (mainPage != null)
            return "redirect:/" + mainPage;

        return viewPrefix + "List";
    }

    public void getSearchResults(T searchEntity, Model model, int page) {
        ((BaseEntity) searchEntity).setDeleted(false);
        Example<T> example = Example.of(searchEntity, exampleMatcher);

        Page<T> entityPage = service.getAllEntities(example, PageRequest.of(page, pageSize));

        model.addAttribute(modelAttribute + "s", entityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", entityPage.getTotalPages());
        model.addAttribute("search" + modelAttribute, searchEntity);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("searchFlag", 1);
    }

    public void getSearchExcelResults(T searchEntity, Model model, int page, HttpServletResponse response) {
        getSearchResults(searchEntity, model, page);

        Example<T> example = Example.of(searchEntity, exampleMatcher);
        List<T> baseEntities = service.findEntities(example);
        if (baseEntities != null && !baseEntities.isEmpty()){
            ExcelGenerator generator = new ExcelGenerator(service.findEntities(example));
            try {
                generator.generateExcelFile(excelSettings(response));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void getSearchExcelResults(R searchEntity, Model model, HttpServletResponse response) {
    }

    public HttpServletResponse excelSettings(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=exported" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        return response;
    }

    public void modelSetting(Model model, BaseEntity baseEntity) {
        model.addAttribute("entityObject", baseEntity);
    }

    public void modelSettingDto(Model model, BaseDto baseDto) {
        model.addAttribute("entityObject", baseDto);
    }
}

