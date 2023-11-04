package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.OrgUnitEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class OrgUnitMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public OrgUnitDto toDto(OrgUnitEntity orgUnit) {

        OrgUnitDto orgUnitDto = new OrgUnitDto();

        dtoMapperUtils.populateCommonFields(orgUnit, orgUnitDto);

        orgUnitDto.setCode(orgUnit.getCode());
        orgUnitDto.setDescr(orgUnit.getDescr());
        orgUnitDto.setParentOrgUnit(orgUnit.getParentOrgUnit().getId());

        if (orgUnit.getOrgUnits() != null) {
            List<Integer> orgUnitsDtl = orgUnit.getOrgUnits().stream()
                    .map(i->i.getId())
                    .toList();
            orgUnitDto.setOrgUnits(orgUnitsDtl);
        }

        return orgUnitDto;
    }
}
