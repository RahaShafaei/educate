package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.OrgUnitEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class OrgUnitMapper {

    private final OrgUnitPostPersonMapper orgUnitPostPersonMapper;

    private final PlansMapper planMapper;

    public OrgUnitDto toDto(OrgUnitEntity orgUnit) {

        OrgUnitDto orgUnitDto = new OrgUnitDto();

        orgUnitDto.setId(orgUnit.getId());
        orgUnitDto.setDeleted(orgUnit.getDeleted());
        orgUnitDto.setDeletedAt(orgUnit.getDeletedAt());
        orgUnitDto.setInsertedAt(orgUnit.getInsertedAt());

        orgUnitDto.setCode(orgUnit.getCode());
        orgUnitDto.setDescr(orgUnit.getDescr());
        orgUnitDto.setParentOrgUnit(orgUnit.getParentOrgUnit().getId());

        if (orgUnit.getOrgUnitPostPersons() != null) {
            Map<Integer, OrgUnitPostPersonDto> orgUnitPostPersonDtoDtl = ConvertListToMap.apply(
                    orgUnit.getOrgUnitPostPersons(),
                    OrgUnitPostPersonEntity::getId,
                    orgUnitPostPersonMapper::toDto
            );
            orgUnitDto.setOrgUnitPostPersons(orgUnitPostPersonDtoDtl);
        }
        if (orgUnit.getPlans() != null) {
            Map<Integer, PlansDto> planDtl = ConvertListToMap.apply(
                    orgUnit.getPlans(),
                    PlansEntity::getId,
                    planMapper::toDto
            );
            orgUnitDto.setPlans(planDtl);
        }
        if (orgUnit.getOrgUnits() != null) {
            List<Integer> orgUnitsDtl = orgUnit.getOrgUnits().stream()
                    .map(i->i.getId())
                    .toList();
            orgUnitDto.setOrgUnits(orgUnitsDtl);
        }

        return orgUnitDto;
    }
}
