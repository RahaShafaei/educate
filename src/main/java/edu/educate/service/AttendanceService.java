package edu.educate.service;

import edu.educate.model.AttendanceEntity;

import java.util.List;

public interface AttendanceService {
    public List<AttendanceEntity> getAttendances();

    public AttendanceEntity getAttendance(Integer id);

    public Boolean deleteAttendance(Integer id);

    public AttendanceEntity createAttendance(AttendanceEntity Attendance);
}
