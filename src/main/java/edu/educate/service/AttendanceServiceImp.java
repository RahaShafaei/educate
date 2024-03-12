package edu.educate.service;

import edu.educate.dto.AttendanceDto;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.AttendanceRepository;
import edu.educate.repository.OrgUnitRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("attendanceService")
public class AttendanceServiceImp extends GenericServiceImpl<AttendanceEntity, AttendanceDto> implements AttendanceService {

    @Autowired
    private PlansService plansService;
    @Autowired
    public AttendanceServiceImp(AttendanceRepository repository) {
        super(repository, "AttendanceEntity");
    }

    @Override
    public List<AttendanceEntity> findByPlanId(Integer id) {
        return ((AttendanceRepository) repository).findByPlanId(id);
    }

    @Override
    public AttendanceEntity createEntityByRelatedPlan(AttendanceEntity entity, Integer planId) {
        PlansEntity plansEntity = plansService.getEntityById(planId).get();
        entity.setPlan(plansEntity);

        return super.createEntity(entity);
    }
}