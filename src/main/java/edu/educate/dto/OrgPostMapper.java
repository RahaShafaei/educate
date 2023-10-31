package edu.educate.dto;

import edu.educate.model.OrgPostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrgPostMapper {

    public OrgPostDto toDto(OrgPostEntity orgPost) {

        OrgPostDto orgPostDto = new OrgPostDto();

        orgPostDto.setId(orgPost.getId());
        orgPostDto.setDeleted(orgPost.getDeleted());
        orgPostDto.setDeletedAt(orgPost.getDeletedAt());
        orgPostDto.setInsertedAt(orgPost.getInsertedAt());

        orgPostDto.setCode(orgPost.getCode());
        orgPostDto.setDescr(orgPost.getDescr());

        return orgPostDto;
    }
}
