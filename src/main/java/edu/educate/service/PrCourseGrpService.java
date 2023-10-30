package edu.educate.service;

import edu.educate.dto.PrCourseGrpDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.PrCourseGrpEntity;

import java.util.List;

public interface PrCourseGrpService {
    public List<PrCourseGrpDto> getPrCourseGrps();

    public PrCourseGrpDto getPrCourseGrp(Integer id);

    public List<PersonDto> getPrCourseGrpPersons(Integer id);

    public Boolean deletePrCourseGrp(Integer id);

    public PrCourseGrpDto createPrCourseGrp(PrCourseGrpEntity PrCourseGrp);
}
