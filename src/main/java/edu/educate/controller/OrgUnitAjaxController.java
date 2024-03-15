package edu.educate.controller;

import edu.educate.dto.LocationDto;
import edu.educate.dto.LocationMapper;
import edu.educate.dto.OrgUnitDto;
import edu.educate.dto.OrgUnitMapper;
import edu.educate.model.LocationEntity;
import edu.educate.model.OrgUnitEntity;
import edu.educate.service.LocationService;
import edu.educate.service.OrgUnitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class OrgUnitAjaxController {
    private final OrgUnitService orgUnitService;
    private final OrgUnitMapper orgUnitMapper;
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    @GetMapping("/orgUnitGetSubcategories")
    @ResponseBody
    public ResponseEntity<List<OrgUnitDto>> getSubcategories(@RequestParam Integer category) {
        List<OrgUnitEntity> subcategories = orgUnitService.findByParentOrgUnitId(category);
        return ResponseEntity.ok(subcategories
                .stream()
                .map(orgUnitMapper::toDto)
                .toList());
    }

    @GetMapping("/orgUnitGetLocations")
    @ResponseBody
    public ResponseEntity<List<LocationDto>> getLocations(@RequestParam Integer category) {
        List<LocationEntity> subcategories = locationService.findByOrgUnitsId(category);
        return ResponseEntity.ok(subcategories
                .stream()
                .map(locationMapper::toDto)
                .toList());
    }
}
