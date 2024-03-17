package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.*;
import edu.educate.repository.PlansRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import jakarta.persistence.criteria.Join;
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
        String title = titleSetting(plansEntity);
        plansEntity.setTitle(title);

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

    private String titleSetting(PlansEntity plansEntity) {
        return plansEntity.getOrgUnit().getTitle() + " _ " +
                plansEntity.getPrCourse().getPrTitle() + " _ " +
                plansEntity.getPrFromDate().substring(0, Math.min(10, plansEntity.getPrFromDate().length()));
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

    private Specification<PlansEntity> getCriterias(Example<PlansEntity> example) {
        Specification<PlansEntity> dateSpec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (example.getProbe().getLocation() != null && example.getProbe().getLocation().getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("location").get("title"), "%" + example.getProbe().getLocation().getTitle() + "%"));
            }
            if (example.getProbe().getOrgUnit() != null && example.getProbe().getOrgUnit().getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("orgUnit").get("title"), "%" + example.getProbe().getOrgUnit().getTitle() + "%"));
            }
//            if (example.getProbe().getPrCourse() != null && example.getProbe().getPrCourse().getPrCourseGrp().getPrTitle() != null) {
//                predicates.add(criteriaBuilder.like(root.get("prCourse").get("prCourseGrp").get("prTitle"), "%" +example.getProbe().getPrCourse().getPrTitle()+ "%"));
//            }
            if (example.getProbe().getPrCourse() != null && example.getProbe().getPrCourse().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("prCourse").get("prTitle"), "%" + example.getProbe().getPrCourse().getPrTitle() + "%"));
            }
            if (example.getProbe().getPerson() != null && example.getProbe().getPerson().getFname() != null) {
                predicates.add(criteriaBuilder.like(root.get("person").get("fname"), "%" + example.getProbe().getPerson().getFname() + "%"));
            }
            if (example.getProbe().getPerson() != null && example.getProbe().getPerson().getLname() != null) {
                predicates.add(criteriaBuilder.like(root.get("person").get("lname"), "%" + example.getProbe().getPerson().getLname() + "%"));
            }
            if (example.getProbe().getPersonSupervisor() != null && example.getProbe().getPersonSupervisor().getFname() != null) {
                predicates.add(criteriaBuilder.like(root.get("personSupervisor").get("fname"), "%" + example.getProbe().getPersonSupervisor().getFname() + "%"));
            }
            if (example.getProbe().getPersonSupervisor() != null && example.getProbe().getPersonSupervisor().getLname() != null) {
                predicates.add(criteriaBuilder.like(root.get("personSupervisor").get("lname"), "%" + example.getProbe().getPersonSupervisor().getLname() + "%"));
            }
            if (example.getProbe().getElementPhase() != null && example.getProbe().getElementPhase().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementPhase").get("prTitle"), "%" + example.getProbe().getElementPhase().getPrTitle() + "%"));
            }
            if (example.getProbe().getElementStatus() != null && example.getProbe().getElementStatus().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementStatus").get("prTitle"), "%" + example.getProbe().getElementStatus().getPrTitle() + "%"));
            }
            if (example.getProbe().getElementEdu() != null && example.getProbe().getElementEdu().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementEdu").get("prTitle"), "%" + example.getProbe().getElementEdu().getPrTitle() + "%"));
            }
            if (example.getProbe().getElementProject() != null && example.getProbe().getElementProject().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementProject").get("prTitle"), "%" + example.getProbe().getElementProject().getPrTitle() + "%"));
            }
            if (example.getProbe().getElementHolding() != null && example.getProbe().getElementHolding().getPrTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("elementHolding").get("prTitle"), "%" + example.getProbe().getElementHolding().getPrTitle() + "%"));
            }
            if (example.getProbe().getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + example.getProbe().getTitle() + "%"));
            }
            if (example.getProbe().getMethod() != null) {
                predicates.add(criteriaBuilder.like(root.get("method"), "%" + example.getProbe().getMethod() + "%"));
            }
            if (example.getProbe().getLtFromDate() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ltFromDate"), example.getProbe().getLtFromDate()));
//            if (example.getProbe().getLtToDate() != null)
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ltToDate"), example.getProbe().getLtToDate()));
            if (example.getProbe().getPlanLink() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("planLink"), example.getProbe().getPlanLink()));

//            ****PlanProcess******************************************
            if (example.getProbe().getPlanProcess() != null && !example.getProbe().getPlanProcess().isEmpty()) {

                Join<PlansEntity, PlanProcessEntity> planProcessJoin = root.join("planProcess");

                if (example.getProbe().getPlanProcess().get(0).getProcess() != null){
                    Join<PlanProcessEntity, ProcessEntity> processJoin = planProcessJoin.join("process");

                    int processId = example.getProbe().getPlanProcess().get(0).getProcess().getId();
                    predicates.add(criteriaBuilder.equal(processJoin.get("id"), processId));
                }

                if (example.getProbe().getPlanProcess().get(0).getLtFromDate() != null)
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(planProcessJoin.get("ltFromDate"),
                            example.getProbe().getPlanProcess().get(0).getLtFromDate()));

                if (example.getProbe().getPlanProcess().get(0).getLtToDate() != null)
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(planProcessJoin.get("ltToDate"),
                            example.getProbe().getPlanProcess().get(0).getLtToDate()));
            }

//            ****Attendance******************************************
            if (example.getProbe().getAttendances() != null && !example.getProbe().getAttendances().isEmpty()) {

                Join<PlansEntity, AttendanceEntity> planAttendanceJoin = root.join("attendances");

                if (example.getProbe().getAttendances().get(0).getPerson() != null) {
                    String attendanceFname = example.getProbe().getAttendances().get(0).getPerson().getFname();
                    predicates.add(criteriaBuilder.like(planAttendanceJoin.get("person").get("fname"), "%" + attendanceFname + "%"));

                    String attendanceLname = example.getProbe().getAttendances().get(0).getPerson().getLname();
                    predicates.add(criteriaBuilder.like(planAttendanceJoin.get("person").get("lname"), "%" + attendanceLname + "%"));
                }

                if (example.getProbe().getAttendances().get(0).getElement() != null) {
                    Join<AttendanceEntity, ElementEntity> elementJoin = planAttendanceJoin.join("element");
                    int elementId = example.getProbe().getAttendances().get(0).getElement().getId();
                    predicates.add(criteriaBuilder.equal(elementJoin.get("id"), elementId));
                }

                if (example.getProbe().getAttendances().get(0).getGrade() != null) {
                    float grade = example.getProbe().getAttendances().get(0).getGrade();
                    predicates.add(criteriaBuilder.equal(planAttendanceJoin.get("grade"), grade));
                }
            }
//            **********************************************

            predicates.add(criteriaBuilder.equal(root.get("deleted"), example.getProbe().isDeleted()));

            if (predicates.isEmpty()) {
                predicates.add(criteriaBuilder.greaterThan(root.get("id"), 1));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return dateSpec;
    }
}