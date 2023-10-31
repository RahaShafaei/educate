package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.PlansEntity;

import java.util.List;

public interface PlansService {
    public List<PlansDto> getPlans();

    public PlansDto getPlan(Integer id);

    public Boolean deletePlan(Integer id);

    public PlansDto createPlan(PlansEntity plan);
}
