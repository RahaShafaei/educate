package edu.educate.controller;

import edu.educate.dto.PrCourseDto;
import edu.educate.dto.PrCourseMapper;
import edu.educate.model.PrCourseEntity;
import edu.educate.service.PrCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class PrCourseAjaxController {
    private final PrCourseService prCourseService;
    private final PrCourseMapper prCourseMapper;

    @GetMapping("/getSubcategories")
    @ResponseBody
    public ResponseEntity<List<PrCourseDto>> getSubcategories(@RequestParam Integer category) {
        List<PrCourseEntity> subcategories = prCourseService.findByPrCourseGrpId(category);
        return ResponseEntity.ok(subcategories
                .stream()
                .map(prCourseMapper::toDto)
                .toList());
    }
}
