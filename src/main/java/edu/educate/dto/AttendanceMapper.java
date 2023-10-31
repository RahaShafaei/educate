package edu.educate.dto;

import edu.educate.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class AttendanceMapper {

    private final OrgUnitPostPersonMapper orgUnitPostPersonMapper;

    private final PlansMapper plansMapper;

    private final ElementMapper elementMapper;

    public AttendanceDto toDto(AttendanceEntity attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();

        attendanceDto.setId(attendance.getId());
        attendanceDto.setDeleted(attendance.getDeleted());
        attendanceDto.setDeletedAt(attendance.getDeletedAt());
        attendanceDto.setInsertedAt(attendance.getInsertedAt());
        attendanceDto.setGrade(attendance.getGrade());

        attendanceDto.setOrgUnitPostPerson(
                attendance.getOrgUnitPostPerson() != null ?
                        this.orgUnitPostPersonMapper.toDto(attendance.getOrgUnitPostPerson()) :
                        null
        );
        attendanceDto.setPlan(
                attendance.getPlan() != null ?
                        this.plansMapper.toDto(attendance.getPlan()) :
                        null
        );
        attendanceDto.setElement(
                attendance.getElement() != null ?
                        this.elementMapper.toDto(attendance.getElement()) :
                        null
        );

        return attendanceDto;
    }
}
