package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PlansMapper {
    private final DtoMapperUtils dtoMapperUtils;

//    private final OrgUnitMapper orgUnitMapper;
//
//    private final PrCourseMapper prCourseMapper;
//
//    private final PersonMapper person;
//
//    private final ElementMapper elementMapper;
//
//    private final MeetingMapper meetingMapper;
    public PlansDto toDto(PlansEntity plans) {

        PlansDto plansDto = new PlansDto();

        dtoMapperUtils.populateCommonFields(plans, plansDto);

        plansDto.setLtFromDate(plans.getLtFromDate());
//        plansDto.setLtToDate(plans.getLtToDate());

        plansDto.setPrFromDate(plans.getPrFromDate());
//        plansDto.setPrToDate(plans.getPrToDate());

//        plansDto.setOrgUnit(this.orgUnitMapper.toDto(plans.getOrgUnit()));
//        plansDto.setPrCourse(this.prCourseMapper.toDto(plans.getPrCourse()));
//        plansDto.setPerson(this.person.toDto(plans.getPerson()));
//        plansDto.setElementStatus(this.elementMapper.toDto(plans.getElementStatus()));
//        if (plans.getMeetings() != null) {
//            Map<Integer, MeetingDto> meetingDtoDtl = ConvertListToMap.apply(
//                    plans.getMeetings(),
//                    MeetingEntity::getId,
//                    meetingMapper::toDto
//            );
//            plansDto.setMeetings(meetingDtoDtl);
//        }

        return plansDto;
    }

    public PlansEntity toEntity(PlansDto plansDto){
        PlansEntity plansEntity = new PlansEntity();

        plansEntity.getPlanProcess().add(plansDto.getPlanProcessWrapper());
        plansEntity.getAttendances().add(plansDto.getAttendanceWrapper());

        plansEntity.setTitle(plansDto.getTitle());
        plansEntity.setMethod(plansDto.getMethod());
        plansEntity.setOrgUnit(plansDto.getOrgUnit());
        plansEntity.setLocation(plansDto.getLocation());
        plansEntity.setPrCourse(plansDto.getPrCourse());
        plansEntity.setPerson(plansDto.getPerson());
        plansEntity.setPersonSupervisor(plansDto.getPersonSupervisor());
        plansEntity.setElementStatus(plansDto.getElementStatus());
        plansEntity.setElementEdu(plansDto.getElementEdu());
        plansEntity.setElementProject(plansDto.getElementProject());
        plansEntity.setElementHolding(plansDto.getElementHolding());
        plansEntity.setElementPhase(plansDto.getElementPhase());
        plansEntity.setLtFromDate(plansDto.getLtFromDate());

        return plansEntity;
    }
}
