package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.AttendanceDto;
import edu.educate.model.AttendanceEntity;

import java.util.List;

public interface AttendanceService {
    public List<AttendanceDto> getAttendances();

    public AttendanceDto getAttendance(Integer id);

    public List<PersonDto> getAttendancePersons(Integer id);

    public Boolean deleteAttendance(Integer id);

    public AttendanceDto createAttendance(AttendanceEntity Attendance);
}
