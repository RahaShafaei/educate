package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.model.PlansEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendanceDto extends BaseDto {

    private PersonDto person;

    private PlansDto plan;

    private ElementDto element;

    private Float grade;
}
