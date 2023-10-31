package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.MeetingEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@AllArgsConstructor
@Component
public class PlansMapper {

    private final OrgUnitMapper orgUnitMapper;

    private final PrCourseMapper prCourseMapper;

    private final OrgUnitPostPersonMapper orgUnitPostPersonMapper;

    private final ElementMapper elementMapper;

    private final AttendanceMapper attendanceMapper;

    private final MeetingMapper meetingMapper;
    public PlansDto toDto(PlansEntity plans) {

        PlansDto plansDto = new PlansDto();

        plansDto.setId(plans.getId());
        plansDto.setDeleted(plans.getDeleted());
        plansDto.setDeletedAt(plans.getDeletedAt());
        plansDto.setInsertedAt(plans.getInsertedAt());

        plansDto.setFromDate(plans.getFromDate());
        plansDto.setToDate(plans.getToDate());

        plansDto.setOrgUnit(
                plans.getOrgUnit() != null ?
                        this.orgUnitMapper.toDto(plans.getOrgUnit()) :
                        null
        );
        plansDto.setPrCourse(
                plans.getPrCourse() != null ?
                        this.prCourseMapper.toDto(plans.getPrCourse()) :
                        null
        );
        plansDto.setOrgUnitPostPerson(
                plans.getOrgUnitPostPerson() != null ?
                        this.orgUnitPostPersonMapper.toDto(plans.getOrgUnitPostPerson()) :
                        null
        );
        plansDto.setElementStatus(
                plans.getElementStatus() != null ?
                        this.elementMapper.toDto(plans.getElementStatus()) :
                        null
        );
        plansDto.setElementType(
                plans.getElementType() != null ?
                        this.elementMapper.toDto(plans.getElementType()) :
                        null
        );
        if (plans.getAttendances() != null) {
            Map<Integer, AttendanceDto> attendanceDtl = ConvertListToMap.apply(
                    plans.getAttendances(),
                    AttendanceEntity::getId,
                    attendanceMapper::toDto
            );
            plansDto.setAttendances(attendanceDtl);
        }
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
