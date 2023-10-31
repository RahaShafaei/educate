package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.OrgPostEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class OrgPostMapper {
    private final OrgUnitPostPersonMapper orgUnitPostPersonMapper;

    public OrgPostDto toDto(OrgPostEntity orgPost) {

        OrgPostDto orgPostDto = new OrgPostDto();

        orgPostDto.setId(orgPost.getId());
        orgPostDto.setDeleted(orgPost.getDeleted());
        orgPostDto.setDeletedAt(orgPost.getDeletedAt());
        orgPostDto.setInsertedAt(orgPost.getInsertedAt());

        orgPostDto.setCode(orgPost.getCode());
        orgPostDto.setDescr(orgPost.getDescr());

        if (orgPost.getOrgUnitPostPersons() != null) {
            Map<Integer, OrgUnitPostPersonDto> orgUnitPostPersonDtoDtl = ConvertListToMap.apply(
                    orgPost.getOrgUnitPostPersons(),
                    OrgUnitPostPersonEntity::getId,
                    orgUnitPostPersonMapper::toDto
            );
            orgPostDto.setOrgUnitPostPersons(orgUnitPostPersonDtoDtl);
        }

        return orgPostDto;
    }
}
