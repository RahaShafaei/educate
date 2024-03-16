package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(
        name = "plans",
        schema = "dbo",
        catalog = "educate"
//        ,uniqueConstraints = {
//                @UniqueConstraint(columnNames =
//                        {
//                                "org_unit_id",
//                                "pr_course_id",
//                                "person_id",
//                                "element_id_status",
//                                "lt_from_date",
//                                "lt_to_date"
//                        })
//        }
)
public class PlansEntity extends BaseEntity {

    @Size(min = 2, message = "{titleEntity.title.min}")
    @Column(name = "title")
    private String title;

    @Column(name = "method")
    private String method;

    @OneToMany(mappedBy = "plan")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "plan")
    private List<PlanProcessEntity> planProcess;

//    @ManyToMany
//    @JoinTable(
//            name = "plan_process",
//            joinColumns = @JoinColumn(name = "plan_id"),
//            inverseJoinColumns = @JoinColumn(name = "process_id"))
//    private List<ProcessEntity> planProcess;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_unit_id")
    private OrgUnitEntity orgUnit;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "pr_course_id")
    private PrCourseEntity prCourse;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
//    @NotNull
    @JoinColumn(name = "person_id_supervisor")
    private PersonEntity personSupervisor;

//    @ManyToOne
//    @NotNull
//    @JoinColumn(name = "element_id_type")
//    private ElementEntity elementType;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_id_status")
    private ElementEntity elementStatus;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_id_edu")
    private ElementEntity elementEdu;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_id_project")
    private ElementEntity elementProject;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_id_holding")
    private ElementEntity elementHolding;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_id_phase")
    private ElementEntity elementPhase;

    @OneToMany(mappedBy = "plan")
    private List<MeetingEntity> meetings;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "lt_from_date" , columnDefinition = "datetime")
    private LocalDateTime ltFromDate;

    //    @NotNull
    @Column(name = "pr_from_date")
    private String prFromDate;

    @Column(name = "plan_link")
    private String planLink;

    @Column(name = "descr")
    private String descr;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("plan.field.project") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.holding") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("orgUnit.field.title"));
        headers.add(MessageUtil.getMessage("location.page.title"));
        headers.add(MessageUtil.getMessage("course.grp.page.title") + "_"+MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("course.grp.page.title") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.status") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.date"));
//        headers.add(MessageUtil.getMessage("plan.field.from.date"));
//        headers.add(MessageUtil.getMessage("plan.field.to.date"));
        headers.add(MessageUtil.getMessage("plan.field.edu") + "_"+MessageUtil.getMessage("main.field.prTitle"));
//        headers.add(MessageUtil.getMessage("plan.field.type") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.method"));
        headers.add(MessageUtil.getMessage("plan.field.phase") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.title"));
        headers.add(MessageUtil.getMessage("plan.field.teacher") + "_"+MessageUtil.getMessage("person.field.master.fname"));
        headers.add(MessageUtil.getMessage("plan.field.teacher") + "_"+MessageUtil.getMessage("person.field.master.lname"));
        headers.add(MessageUtil.getMessage("plan.field.supervisor") + "_"+MessageUtil.getMessage("person.field.master.fname"));
        headers.add(MessageUtil.getMessage("plan.field.supervisor") + "_"+MessageUtil.getMessage("person.field.master.lname"));
        headers.add(MessageUtil.getMessage("plan.field.link"));
        headers.add(MessageUtil.getMessage("main.field.descr"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(elementProject != null ? elementProject.getPrTitle() : null);
        objects.add(elementHolding != null ? elementHolding.getPrTitle() : null);
        objects.add(orgUnit != null ? orgUnit.getTitle() : null);
        objects.add(location != null ? location.getTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrCourseGrp().getLtTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrCourseGrp().getPrTitle() : null);
        objects.add(prCourse != null ? prCourse.getLtTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrTitle() : null);
        objects.add(elementStatus != null ? elementStatus.getPrTitle() : null);
        objects.add(prFromDate != null ? prFromDate : null);
//        objects.add(prToDate != null ? prToDate : null);
        objects.add(elementEdu != null ? elementEdu.getPrTitle() : null);
//        objects.add(elementType != null ? elementType.getPrTitle() : null);
        objects.add(method != null ? method : null);
        objects.add(elementPhase != null ? elementPhase.getPrTitle() : null);
        objects.add(getTitle() != null ? getTitle() : null);
        objects.add(person != null ? person.getFname() : null);
        objects.add(person != null ? person.getLname() : null);
        objects.add(personSupervisor != null ? personSupervisor.getFname() : null);
        objects.add(personSupervisor != null ? personSupervisor.getLname() : null);
        objects.add(planLink != null ? planLink : null);
        objects.add(descr != null ? descr : null);
        return objects;
    }

    public List<PlanProcessEntity> getPlanProcess() {
        if (planProcess == null){
            planProcess = new ArrayList<>();
            return planProcess;
        }

        return ifEntityListHasDeletedElement(planProcess);
    }

    public List<AttendanceEntity> getAttendances() {
        if (attendances == null || attendances.isEmpty()){
            attendances = new ArrayList<>();
            return attendances;
        }

        return ifEntityListHasDeletedElement(attendances);
    }

    public OrgUnitEntity getOrgUnit() {
        return (OrgUnitEntity)ifEntityIsDeleted(orgUnit);
    }
    public LocationEntity getLocation() {
        return (LocationEntity)ifEntityIsDeleted(location);
    }

    public PrCourseEntity getPrCourse() {
        return (PrCourseEntity)ifEntityIsDeleted(prCourse);
    }

    public PersonEntity getPerson() {
        return (PersonEntity)ifEntityIsDeleted(person);
    }

    public PersonEntity getPersonSupervisor() {
        return (PersonEntity)ifEntityIsDeleted(personSupervisor);
    }

//    public ElementEntity getElementType() {
//        return (ElementEntity)ifEntityIsDeleted(elementType);
//    }

    public ElementEntity getElementStatus() {
        return (ElementEntity)ifEntityIsDeleted(elementStatus);
    }

    public ElementEntity getElementEdu() {
        return (ElementEntity)ifEntityIsDeleted(elementEdu);
    }

    public ElementEntity getElementProject() {
        return (ElementEntity)ifEntityIsDeleted(elementProject);
    }

    public ElementEntity getElementHolding() {
        return (ElementEntity)ifEntityIsDeleted(elementHolding);
    }

    public ElementEntity getElementPhase() {
        return (ElementEntity)ifEntityIsDeleted(elementPhase);
    }

    public List<MeetingEntity> getMeetings() {
        return ifEntityListHasDeletedElement(meetings);
    }

    @AssertTrue(message = "{plansEntity.element.status}")
    public boolean isValidElementStatus() {
        if (elementStatus == null)
            return true;
        return elementStatus.getElementGrp().getLtTitle().equals("plan_status");
    }

//    @AssertTrue(message = "{plansEntity.element.type}")
//    public boolean isValidElementType() {
//        if (elementType == null)
//            return true;
//        return elementType.getElementGrp().getLtTitle().equals("plan_type");
//    }

}
