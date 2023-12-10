package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import jakarta.persistence.Column;
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
    private OrgUnitDto orgUnit;

    private PrCourseDto prCourse;

    private PersonDto person;

    private ElementDto elementType;

    private ElementDto elementStatus;

    private Map<Integer, AttendanceDto> attendances;

    private Map<Integer, MeetingDto> meetings;

    private LocalDateTime ltFromDate;

    private LocalDateTime ltToDate;

    private String prFromDate;

    private String prToDate;
}
