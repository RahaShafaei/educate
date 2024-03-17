package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.AttendanceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class AttendanceMapper {
    private final DtoMapperUtils dtoMapperUtils;

    private final PersonMapper personMapper;

    private final PlansMapper plansMapper;

    private final ElementMapper elementMapper;

    public AttendanceDto toDto(AttendanceEntity attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();

        dtoMapperUtils.populateCommonFields(attendance, attendanceDto);
        attendanceDto.setGrade(attendance.getGrade());

        attendanceDto.setPerson(
                this.personMapper.toDto(attendance.getPerson())
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
