package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import edu.educate.dto.baseDto.TitleLPDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class ElementGrpDto  extends TitleLPDto {

    private Map<Integer, ElementDto> elements;

}
