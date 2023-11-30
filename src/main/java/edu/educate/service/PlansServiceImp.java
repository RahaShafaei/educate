package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.MeetingEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.PlansRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Page<PlansEntity> getAllEntities(Example<PlansEntity> example, Pageable pageable) {
        Specification<PlansEntity> dateSpec = getCriterias(example);

        return ((PlansRepository) repository).findAll(dateSpec, pageable);
    }

    @Override
    public List<PlansEntity> findEntities(Example<PlansEntity> example) {
        Specification<PlansEntity> dateSpec = getCriterias(example);

        return ((PlansRepository) repository).findAll(dateSpec);
    }

    private void updateRelatedFiles(PlansEntity plansEntity, MultipartFile[] files) {
        List<MultipartFile> validFiles = Arrays.stream(files)
                .filter(Objects::nonNull)
                .filter(file -> !file.getContentType().equals("application/octet-stream"))
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

    private  Specification<PlansEntity> getCriterias(Example<PlansEntity> example){
        Specification<PlansEntity> dateSpec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (example.getProbe().getOrgUnit().getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("orgUnit").get("title"), "%" +example.getProbe().getOrgUnit().getTitle()+ "%"));
            }
            if (example.getProbe().getPrCourse().getPrCourseGrp().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("prCourse").get("prCourseGrp").get("prTitle"), "%" +example.getProbe().getPrCourse().getPrTitle()+ "%"));
            }
            if (example.getProbe().getPrCourse().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("prCourse").get("prTitle"), "%" +example.getProbe().getPrCourse().getPrTitle()+ "%"));
            }
            if (example.getProbe().getPerson().getFname() != null) {
                predicates.add(criteriaBuilder.like(root.get("person").get("fname"), "%" +example.getProbe().getPerson().getFname()+ "%"));
            }
            if (example.getProbe().getPerson().getLname() != null) {
                predicates.add(criteriaBuilder.like(root.get("person").get("lname"), "%" +example.getProbe().getPerson().getLname()+ "%"));
            }
            if (example.getProbe().getElementType().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementType").get("prTitle"), "%" +example.getProbe().getElementType().getPrTitle()+ "%"));
            }
            if (example.getProbe().getElementStatus().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementStatus").get("prTitle"), "%" +example.getProbe().getElementStatus().getPrTitle()+ "%"));
            }
            if (example.getProbe().getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" +example.getProbe().getTitle()+ "%"));
            }
            if (example.getProbe().getFromDate() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fromDate"), example.getProbe().getFromDate()));

            if (example.getProbe().getToDate() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("toDate"), example.getProbe().getToDate()));

            predicates.add(criteriaBuilder.equal(root.get("deleted"), example.getProbe().isDeleted()));

            if (predicates.isEmpty()) {
                predicates.add(criteriaBuilder.greaterThan(root.get("id"), 1));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return dateSpec;
    }
}