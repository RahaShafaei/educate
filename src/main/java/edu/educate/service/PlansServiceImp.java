package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PlansEntity;
import edu.educate.repository.PlansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("plansService")
public class PlansServiceImp implements PlansService {

    private static final String PLAN_ID = "Plans id: ";
    private final PlansRepository planRepository;
    @Override
    public List<PlansEntity> getPlans() {
        return planRepository.findAll();
    }

    @Override
    public PlansEntity getPlan(Integer id) {
        Optional<PlansEntity> plan = planRepository.findById(id);

        if (plan.isEmpty())
            throw new ItemNotFoundException(PLAN_ID + id);

        return plan.get();
    }

    @Override
    public Boolean deletePlan(Integer id) {
        Optional<PlansEntity> plan = planRepository.findById(id);

        if (!plan.isEmpty()) {
            planRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PlansEntity createPlan(PlansEntity plan) {
        if (plan.getTitle() == null || plan.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of Plans should not be empty.");

        if (plan.getOrgUnit() == null)
            throw new ParametersNotValidException("OrgUnit of Plans should not be empty.");

        if (plan.getPrCourse() == null)
            throw new ParametersNotValidException("PrCourse of Plans should not be empty.");

        if (plan.getOrgUnitPostPerson() == null )
            throw new ParametersNotValidException("OrgUnitPostPerson of Plans should not be empty.");

        if (plan.getElementStatus() == null)
            throw new ParametersNotValidException("ElementStatus of Plans should not be empty.");

        if (plan.getElementType() == null )
            throw new ParametersNotValidException("ElementType of Plans should not be empty.");

        if (plan.getFromDate() == null )
            throw new ParametersNotValidException("FromDate of Plans should not be empty.");

        if (plan.getToDate() == null )
            throw new ParametersNotValidException("ToDate of Plans should not be empty.");


        PlansEntity savedPlans = planRepository.save(plan);

        return savedPlans;
    }
}
