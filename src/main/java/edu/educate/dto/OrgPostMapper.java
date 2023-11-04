package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.OrgPostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrgPostMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public OrgPostDto toDto(OrgPostEntity orgPost) {

        OrgPostDto orgPostDto = new OrgPostDto();

        dtoMapperUtils.populateCommonFields(orgPost, orgPostDto);

        orgPostDto.setCode(orgPost.getCode());
        orgPostDto.setDescr(orgPost.getDescr());

        return orgPostDto;
    }
}
