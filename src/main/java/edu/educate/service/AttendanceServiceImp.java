package edu.educate.service;

import edu.educate.dto.*;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.AttendanceEntity;
import edu.educate.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("attendanceService")
public class AttendanceServiceImp implements AttendanceService{
    private static final String ATTENDANCE_ID = "Attendance id: ";
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final OrgUnitPostPersonMapper OrgUnitPostPersonMapper;

    @Override
    public List<AttendanceDto> getAttendances() {
        return attendanceRepository.findAll()
                .stream()
                .map(attendanceMapper::toDto)
                .toList();
    }

    @Override
    public AttendanceDto getAttendance(Integer id) {
        Optional<AttendanceEntity> attendance = attendanceRepository.findById(id);

        if (attendance.isEmpty())
            throw new ItemNotFoundException(ATTENDANCE_ID + id);

        return attendanceMapper.toDto(attendance.get());
    }

    @Override
    public OrgUnitPostPersonDto getAttendanceOrgUnitPostPersons(Integer id) {
        Optional<AttendanceEntity> attendance = attendanceRepository.findById(id);

        if (attendance.isEmpty())
            throw new ItemNotFoundException(ATTENDANCE_ID + id);

        return OrgUnitPostPersonMapper.toDto(attendance.get().getOrgUnitPostPerson());
    }

    @Override
    public Boolean deleteAttendance(Integer id) {
        Optional<AttendanceEntity> attendance = attendanceRepository.findById(id);

        if (!attendance.isEmpty()) {
            attendanceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AttendanceDto createAttendance(AttendanceEntity attendance) {
        if (attendance.getOrgUnitPostPerson() == null)
            throw new ParametersNotValidException("OrgUnitPostPerson of Attendance should not be empty.");

        if (attendance.getPlan() == null)
            throw new ParametersNotValidException("Plan of Attendance should not be empty.");

        AttendanceEntity savedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toDto(savedAttendance);
    }
}
