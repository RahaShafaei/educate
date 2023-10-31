package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class PlansDto extends TitleDto {
    private OrgUnitDto orgUnit;

    private PrCourseDto prCourse;

    private OrgUnitPostPersonDto orgUnitPostPerson;

    private ElementDto elementType;

    private ElementDto elementStatus;

    private Map<Integer, AttendanceDto> attendances;

    private Map<Integer, MeetingDto> meetings;

    private Date fromDate;

    private Date toDate;
}
