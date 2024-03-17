package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.LocationDto;
import edu.educate.model.LocationEntity;
import edu.educate.service.LocationService;
import edu.educate.service.OrgUnitService;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationController extends BaseController<LocationEntity, LocationDto> {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("orgUnit.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("descr", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnoreNullValues()
            .withIgnorePaths("id", "deletedAt", "insertedAt");

    private final OrgUnitService orgUnitService;

    public LocationController(LocationService LocationService,
                              OrgUnitService orgUnitService) {
        super(LocationEntity.class,
                LocationDto.class,
                LocationService,
                "location",
                "locationDir/location",
                null,
                30,
                SEARCH_CONDITIONS_MATCH_ALL);

        this.orgUnitService = orgUnitService;
    }

}