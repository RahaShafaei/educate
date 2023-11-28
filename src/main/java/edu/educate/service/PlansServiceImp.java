package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.MeetingEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.PlansRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service("plansService")
public class PlansServiceImp extends GenericServiceImpl<PlansEntity, PlansDto> implements PlansService {
    @Autowired
    private ElementService elementService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    public PlansServiceImp(PlansRepository repository) {
        super(repository, "PlansEntity");
    }

    @Override
    public List<PlansEntity> findEntitiesBySpecificFields(PlansEntity plansEntity) {
        List<ElementEntity> elementEntities = new ArrayList<>();
        elementEntities.add(elementService.getEntityById(plansEntity.getElementStatus().getId()).get());

        return ((PlansRepository) this.repository).findByElementStatusNotIn(elementEntities);
    }

    @Override
    public PlansEntity createEntityByRelatedFiles(PlansEntity plansEntity, MultipartFile[] files) {
        PlansEntity savedPlan = super.createEntity(plansEntity);
        updateRelatedFiles(savedPlan, files);
        return savedPlan;
    }

    private void updateRelatedFiles(PlansEntity plansEntity, MultipartFile[] files) {
        List<MultipartFile> validFiles = Arrays.stream(files)
                .filter(Objects::nonNull)
                .filter(file-> !file.getContentType().equals("application/octet-stream"))
                .toList();

        if (validFiles != null && validFiles.size() > 0) {
            if (plansEntity.getMeetings() == null)
                plansEntity.setMeetings(new ArrayList<>());

            List<String> titles = Arrays.stream(files).map(file -> file.getOriginalFilename()).toList();

            Iterator<MeetingEntity> iterator = plansEntity.getMeetings().iterator();
            while (iterator.hasNext()) {
                MeetingEntity meeting = iterator.next();
                if (titles.contains(meeting.getTitle())) {
                    iterator.remove();
                    meetingService.deleteEntityEntirely(meeting.getId());
                }
            }

            Arrays.stream(files)
                    .toList()
                    .forEach(file -> {
                        MeetingEntity meetingEntity = new MeetingEntity();
                        meetingEntity.setPlan(plansEntity);
                        meetingEntity.setTitle(file.getOriginalFilename());
                        meetingEntity.setFileType(file.getContentType());
                        try {
                            meetingEntity.setFileValue(file.getBytes());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        meetingService.createEntity(meetingEntity);
//                        plansEntity.getMeetings().add(meetingEntity);
                    });
        }
    }
}