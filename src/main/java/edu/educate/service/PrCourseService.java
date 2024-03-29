package edu.educate.service;

import edu.educate.dto.PrCourseDto;
import edu.educate.model.PrCourseEntity;
import edu.educate.service.baseService.GenericService;

import java.util.List;

public interface PrCourseService extends GenericService<PrCourseEntity, PrCourseDto> {
    List<PrCourseEntity> findByPrCourseGrpId(Integer id);
}
