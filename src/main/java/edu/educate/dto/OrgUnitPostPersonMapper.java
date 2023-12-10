package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.OrgUnitPostPersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class OrgUnitPostPersonMapper {
    private final DtoMapperUtils dtoMapperUtils;

    private final OrgPostMapper orgPostMapper;

    private final OrgUnitMapper orgUnitMapper;

    private final PersonMapper personMapper;

    public OrgUnitPostPersonDto toDto(OrgUnitPostPersonEntity orgUnitPostPerson) {

        OrgUnitPostPersonDto orgUnitPostPersonDto = new OrgUnitPostPersonDto();

        dtoMapperUtils.populateCommonFields(orgUnitPostPerson, orgUnitPostPersonDto);

        orgUnitPostPersonDto.setLtFromDate(orgUnitPostPerson.getLtFromDate());
        orgUnitPostPersonDto.setLtToDate(orgUnitPostPerson.getLtToDate());

        orgUnitPostPersonDto.setPrFromDate(orgUnitPostPerson.getPrFromDate());
        orgUnitPostPersonDto.setPrToDate(orgUnitPostPerson.getPrToDate());

        orgUnitPostPersonDto.setOrgPost(
                orgUnitPostPerson.getOrgPost() != null ?
                        this.orgPostMapper.toDto(orgUnitPostPerson.getOrgPost()) :
                        null
        );
        orgUnitPostPersonDto.setOrgUnit(
                orgUnitPostPerson.getOrgUnit() != null ?
                        this.orgUnitMapper.toDto(orgUnitPostPerson.getOrgUnit()) :
                        null
        );
        orgUnitPostPersonDto.setPerson(
                orgUnitPostPerson.getPerson() != null ?
                        this.personMapper.toDto(orgUnitPostPerson.getPerson()) :
                        null
        );

        return orgUnitPostPersonDto;
    }
}
