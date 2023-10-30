package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PlansDto  extends TitleDto {
    private OrgUnitDto orgUnit;

    private PrCourseDto prCourse;

    private OrgUnitPostPersonDto orgUnitPostPerson;

    private ElementDto elementType;

    private ElementDto elementStatus;

    private List<AttendanceDto> attendances;

    private List<MeetingDto> meetings;

    private Date fromDate;

    private Date toDate;
}
