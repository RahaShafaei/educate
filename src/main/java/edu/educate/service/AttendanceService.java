package edu.educate.service;

import edu.educate.dto.AttendanceDto;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.PlansEntity;
import edu.educate.service.baseService.GenericService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttendanceService extends GenericService<AttendanceEntity, AttendanceDto> {
    List<AttendanceEntity> findByPlanId(Integer id);
    public AttendanceEntity createEntityByRelatedPlan(AttendanceEntity entity, Integer planId);
}
