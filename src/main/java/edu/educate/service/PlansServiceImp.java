package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.PlansDto;
import edu.educate.model.PlansEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("plansService")
public class PlansServiceImp implements PlansService {
    @Override
    public List<PlansDto> getPlans() {
        return null;
    }

    @Override
    public PlansDto getPlan(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getPlanPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deletePlan(Integer id) {
        return null;
    }

    @Override
    public PlansDto createPlan(PlansEntity Plan) {
        return null;
    }
}
