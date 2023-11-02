package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
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

    @Override
    public List<AttendanceEntity> getAttendances() {
        return attendanceRepository.findAll();
    }

    @Override
    public AttendanceEntity getAttendance(Integer id) {
        Optional<AttendanceEntity> attendance = attendanceRepository.findById(id);

        if (attendance.isEmpty())
            throw new ItemNotFoundException(ATTENDANCE_ID + id);

        return attendance.get();
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
    public AttendanceEntity createAttendance(AttendanceEntity attendance) {

        AttendanceEntity savedAttendance = attendanceRepository.save(attendance);

        return savedAttendance;
    }
}
