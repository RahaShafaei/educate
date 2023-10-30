package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrgUnitPostPersonDto  extends BaseDto {
    private List<AttendanceDto> attendances;

    private List<PlansDto> plans;

    private OrgUnitDto orgUnit;

    private OrgPostDto orgPost;

    private PersonDto person;

    private Date fromDate;

    private Date toDate;
}
