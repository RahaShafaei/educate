package edu.educate.service;

import edu.educate.model.PrCourseGrpEntity;

import java.util.List;

public interface PrCourseGrpService {
    public List<PrCourseGrpEntity> getPrCourseGrps();

    public PrCourseGrpEntity getPrCourseGrp(Integer id);

    public Boolean deletePrCourseGrp(Integer id);

    public PrCourseGrpEntity createPrCourseGrp(PrCourseGrpEntity prCourseGrp);
}
