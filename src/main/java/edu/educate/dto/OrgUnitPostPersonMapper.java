package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Map;
@AllArgsConstructor
@Component
public class OrgUnitPostPersonMapper {

    private final AttendanceMapper attendanceMapper;

    private final PlansMapper planMapper;

    private final OrgPostMapper orgPostMapper;

    private final OrgUnitMapper orgUnitMapper;

    private final PersonMapper personMapper;

    public OrgUnitPostPersonDto toDto(OrgUnitPostPersonEntity orgUnitPostPerson) {

        OrgUnitPostPersonDto orgUnitPostPersonDto = new OrgUnitPostPersonDto();

        orgUnitPostPersonDto.setId(orgUnitPostPerson.getId());
        orgUnitPostPersonDto.setDeleted(orgUnitPostPerson.getDeleted());
        orgUnitPostPersonDto.setDeletedAt(orgUnitPostPerson.getDeletedAt());
        orgUnitPostPersonDto.setInsertedAt(orgUnitPostPerson.getInsertedAt());

        orgUnitPostPersonDto.setFromDate(orgUnitPostPerson.getFromDate());
        orgUnitPostPersonDto.setToDate(orgUnitPostPerson.getToDate());

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
        if (orgUnitPostPerson.getAttendances() != null) {
            Map<Integer, AttendanceDto> attendanceDtl = ConvertListToMap.apply(
                    orgUnitPostPerson.getAttendances(),
                    AttendanceEntity::getId,
                    attendanceMapper::toDto
            );
            orgUnitPostPersonDto.setAttendances(attendanceDtl);
        }
        if (orgUnitPostPerson.getPlans() != null) {
            Map<Integer, PlansDto> planStatusDtl = ConvertListToMap.apply(
                    orgUnitPostPerson.getPlans(),
                    PlansEntity::getId,
                    planMapper::toDto
            );
            orgUnitPostPersonDto.setPlans(planStatusDtl);
        }

        return orgUnitPostPersonDto;
    }
}
