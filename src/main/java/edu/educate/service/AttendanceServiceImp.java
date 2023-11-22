package edu.educate.service;

import edu.educate.dto.AttendanceDto;
import edu.educate.model.AttendanceEntity;
import edu.educate.repository.AttendanceRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attendanceService")
public class AttendanceServiceImp extends GenericServiceImpl<AttendanceEntity, AttendanceDto> implements AttendanceService {

    @Autowired
    public AttendanceServiceImp(AttendanceRepository repository) {
        super(repository, "AttendanceEntity");
    }
}