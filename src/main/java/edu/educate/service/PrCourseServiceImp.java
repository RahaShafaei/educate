package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.PrCourseDto;
import edu.educate.model.PrCourseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prCourseService")
public class PrCourseServiceImp implements PrCourseService{
    @Override
    public List<PrCourseDto> getPrCourses() {
        return null;
    }

    @Override
    public PrCourseDto getPrCourse(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getPrCoursePersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deletePrCourse(Integer id) {
        return null;
    }

    @Override
    public PrCourseDto createPrCourse(PrCourseEntity PrCourse) {
        return null;
    }
}
