package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class AttendanceMapper {
    private final DtoMapperUtils dtoMapperUtils;

    private final OrgUnitPostPersonMapper orgUnitPostPersonMapper;

    private final PlansMapper plansMapper;

    private final ElementMapper elementMapper;

    public AttendanceDto toDto(AttendanceEntity attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();

        dtoMapperUtils.populateCommonFields(attendance, attendanceDto);
        attendanceDto.setGrade(attendance.getGrade());

        attendanceDto.setOrgUnitPostPerson(
                this.orgUnitPostPersonMapper.toDto(attendance.getOrgUnitPostPerson())
        );
        attendanceDto.setPlan(
                this.plansMapper.toDto(attendance.getPlan())
        );
        attendanceDto.setElement(
                attendance.getElement() != null ?
                        this.elementMapper.toDto(attendance.getElement()) :
                        null
        );

        return attendanceDto;
    }
}
