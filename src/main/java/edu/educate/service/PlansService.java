package edu.educate.service;

import edu.educate.model.PlansEntity;

import java.util.List;

public interface PlansService {
    public List<PlansEntity> getPlans();

    public PlansEntity getPlan(Integer id);

    public Boolean deletePlan(Integer id);

    public PlansEntity createPlan(PlansEntity plan);
}
