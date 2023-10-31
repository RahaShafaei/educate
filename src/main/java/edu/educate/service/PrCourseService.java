package edu.educate.service;

import edu.educate.model.PrCourseEntity;

import java.util.List;

public interface PrCourseService {
    public List<PrCourseEntity> getPrCourses();

    public PrCourseEntity getPrCourse(Integer id);

    public Boolean deletePrCourse(Integer id);

    public PrCourseEntity createPrCourse(PrCourseEntity prCourse);
}
