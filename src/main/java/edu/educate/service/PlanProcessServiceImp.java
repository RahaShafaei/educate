package edu.educate.service;

import edu.educate.dto.PlanProcessDto;
import edu.educate.model.PlanProcessEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.AttendanceRepository;
import edu.educate.repository.PlanProcessRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("planProcessService")
public class PlanProcessServiceImp extends GenericServiceImpl<PlanProcessEntity, PlanProcessDto> implements PlanProcessService {
    @Autowired
    private PlansService plansService;
    @Autowired
    public PlanProcessServiceImp(PlanProcessRepository repository) {
        super(repository, "PlanProcessEntity");
    }

    @Override
    public List<PlanProcessEntity> findByPlanId(Integer id) {
        return ((PlanProcessRepository) repository).findByDeletedFalseAndPlanId(id);
    }

    @Override
    public PlanProcessEntity createEntityByRelatedPlan(PlanProcessEntity entity, Integer planId) {
        PlansEntity plansEntity = plansService.getEntityById(planId).get();
        entity.setPlan(plansEntity);

        return super.createEntity(entity);
    }
}
