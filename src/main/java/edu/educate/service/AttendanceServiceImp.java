package edu.educate.service;

import edu.educate.dto.AttendanceDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.AttendanceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("attendanceService")
public class AttendanceServiceImp implements AttendanceService{
    @Override
    public List<AttendanceDto> getAttendances() {
        return null;
    }

    @Override
    public AttendanceDto getAttendance(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getAttendancePersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteAttendance(Integer id) {
        return null;
    }

    @Override
    public AttendanceDto createAttendance(AttendanceEntity Attendance) {
        return null;
    }
}
