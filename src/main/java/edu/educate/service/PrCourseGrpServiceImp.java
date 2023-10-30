package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.PrCourseGrpDto;
import edu.educate.model.PrCourseGrpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prCourseGrpService")
public class PrCourseGrpServiceImp implements PrCourseGrpService{
    @Override
    public List<PrCourseGrpDto> getPrCourseGrps() {
        return null;
    }

    @Override
    public PrCourseGrpDto getPrCourseGrp(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getPrCourseGrpPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deletePrCourseGrp(Integer id) {
        return null;
    }

    @Override
    public PrCourseGrpDto createPrCourseGrp(PrCourseGrpEntity PrCourseGrp) {
        return null;
    }
}
