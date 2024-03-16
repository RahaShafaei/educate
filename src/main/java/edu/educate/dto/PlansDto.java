package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import edu.educate.model.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
public class PlansDto extends TitleDto {

    private String method;

    private LocationEntity location;

    private OrgUnitEntity orgUnit;

    private PrCourseEntity prCourse;

    private PersonEntity person;

    private PersonEntity personSupervisor;

    private ElementEntity elementStatus;

    private ElementEntity elementEdu;

    private ElementEntity elementProject;

    private ElementEntity elementHolding;

    private ElementEntity elementPhase;

    private Map<Integer, AttendanceDto> attendances;

    private Map<Integer, MeetingDto> meetings;

    private LocalDateTime ltFromDate;

    private LocalDateTime ltToDate;

    private String prFromDate;

    private String prToDate;

    private String planLink;
    /*
     * Fields for controller wrapper
     * */
    private AttendanceEntity attendanceWrapper;

    private PlanProcessEntity planProcessWrapper;
}
