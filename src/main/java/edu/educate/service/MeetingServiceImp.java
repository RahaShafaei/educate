package edu.educate.service;

import edu.educate.model.MeetingEntity;
import edu.educate.repository.MeetingRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("meetingService")
public class MeetingServiceImp extends GenericServiceImpl<MeetingEntity> implements MeetingService {

    @Autowired
    public MeetingServiceImp(MeetingRepository repository) {
        super(repository, "MeetingEntity");
    }
}