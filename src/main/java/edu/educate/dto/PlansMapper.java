package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.helper.ConvertListToMap;
import edu.educate.model.MeetingEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@AllArgsConstructor
@Component
public class PlansMapper {
    private final DtoMapperUtils dtoMapperUtils;

    private final OrgUnitMapper orgUnitMapper;

    private final PrCourseMapper prCourseMapper;

    private final PersonMapper person;

    private final ElementMapper elementMapper;

    private final MeetingMapper meetingMapper;
    public PlansDto toDto(PlansEntity plans) {

        PlansDto plansDto = new PlansDto();

        dtoMapperUtils.populateCommonFields(plans, plansDto);

        plansDto.setLtFromDate(plans.getLtFromDate());
//        plansDto.setLtToDate(plans.getLtToDate());

        plansDto.setPrFromDate(plans.getPrFromDate());
//        plansDto.setPrToDate(plans.getPrToDate());

        plansDto.setOrgUnit(this.orgUnitMapper.toDto(plans.getOrgUnit()));
        plansDto.setPrCourse(this.prCourseMapper.toDto(plans.getPrCourse()));
        plansDto.setPerson(this.person.toDto(plans.getPerson()));
        plansDto.setElementStatus(this.elementMapper.toDto(plans.getElementStatus()));
        plansDto.setElementType(this.elementMapper.toDto(plans.getElementPhase()));
        if (plans.getMeetings() != null) {
            Map<Integer, MeetingDto> meetingDtoDtl = ConvertListToMap.apply(
                    plans.getMeetings(),
                    MeetingEntity::getId,
                    meetingMapper::toDto
            );
            plansDto.setMeetings(meetingDtoDtl);
        }

        return plansDto;
    }
}
