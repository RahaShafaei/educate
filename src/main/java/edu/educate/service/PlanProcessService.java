package edu.educate.service;

import edu.educate.dto.PlanProcessDto;
import edu.educate.model.PlanProcessEntity;
import edu.educate.service.baseService.GenericService;

import java.util.List;

public interface PlanProcessService  extends GenericService<PlanProcessEntity, PlanProcessDto> {
    List<PlanProcessEntity> findByPlanId(Integer id);
    public PlanProcessEntity createEntityByRelatedPlan(PlanProcessEntity entity, Integer planId);
}
