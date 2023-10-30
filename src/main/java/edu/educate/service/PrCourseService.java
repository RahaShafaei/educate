package edu.educate.service;

import edu.educate.dto.PrCourseDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.PrCourseEntity;

import java.util.List;

public interface PrCourseService {
    public List<PrCourseDto> getPrCourses();

    public PrCourseDto getPrCourse(Integer id);

    public List<PersonDto> getPrCoursePersons(Integer id);

    public Boolean deletePrCourse(Integer id);

    public PrCourseDto createPrCourse(PrCourseEntity PrCourse);
}
