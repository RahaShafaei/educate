package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class OrgUnitPostPersonDto  extends BaseDto {
    private Map<Integer, AttendanceDto> attendances;

    private Map<Integer, PlansDto> plans;

    private OrgUnitDto orgUnit;

    private OrgPostDto orgPost;

    private PersonDto person;

    private Date fromDate;

    private Date toDate;
}
