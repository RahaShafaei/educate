package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.model.baseModel.TitleEntity;
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
        catalog = "educate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames =
                        {
                                "org_unit_id",
                                "pr_course_id",
                                "person_id",
                                "element_id_type",
                                "element_id_status",
                                "lt_from_date",
                                "lt_to_date"
                        })
        }
)
public class PlansEntity extends BaseEntity {

    @Size(min = 2, message = "{titleEntity.title.min}")
    @Column(name = "title", unique=true)
    private String title;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_unit_id")
    private OrgUnitEntity orgUnit;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "pr_course_id")
    private PrCourseEntity prCourse;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_id_type")
    private ElementEntity elementType;

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

    @OneToMany(mappedBy = "plan")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "plan")
    private List<MeetingEntity> meetings;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "lt_from_date" , columnDefinition = "datetime")
    private LocalDateTime ltFromDate;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "lt_to_date" , columnDefinition = "datetime")
    private LocalDateTime ltToDate;

//    @NotNull
    @Column(name = "pr_from_date")
    private String prFromDate;

//    @NotNull
    @Column(name = "pr_to_date")
    private String prToDate;

    @Column(name = "plan_link")
    private String planLink;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("main.field.title"));
        headers.add(MessageUtil.getMessage("orgUnit.field.title"));
        headers.add(MessageUtil.getMessage("course.grp.page.title") + "_"+MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("course.grp.page.title") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.type") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.status") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.edu") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.project") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.holding") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("person.field.fname"));
        headers.add(MessageUtil.getMessage("person.field.lname"));
        headers.add(MessageUtil.getMessage("plan.field.from.date"));
        headers.add(MessageUtil.getMessage("plan.field.link"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(getTitle() != null ? getTitle() : null);
        objects.add(orgUnit != null ? orgUnit.getTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrCourseGrp().getLtTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrCourseGrp().getPrTitle() : null);
        objects.add(prCourse != null ? prCourse.getLtTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrTitle() : null);
        objects.add(elementType != null ? elementType.getPrTitle() : null);
        objects.add(elementStatus != null ? elementStatus.getPrTitle() : null);
        objects.add(elementEdu != null ? elementEdu.getPrTitle() : null);
        objects.add(elementProject != null ? elementProject.getPrTitle() : null);
        objects.add(elementHolding != null ? elementHolding.getPrTitle() : null);
        objects.add(person != null ? person.getFname() : null);
        objects.add(person != null ? person.getLname() : null);
        objects.add(prFromDate != null ? prFromDate : null);
        objects.add(prToDate != null ? prToDate : null);
        objects.add(planLink != null ? planLink : null);
        return objects;
    }

    public OrgUnitEntity getOrgUnit() {
        return (OrgUnitEntity)ifEntityIsDeleted(orgUnit);
    }

    public PrCourseEntity getPrCourse() {
        return (PrCourseEntity)ifEntityIsDeleted(prCourse);
    }

    public PersonEntity getPerson() {
        return (PersonEntity)ifEntityIsDeleted(person);
    }

    public ElementEntity getElementType() {
        return (ElementEntity)ifEntityIsDeleted(elementType);
    }

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

    public List<AttendanceEntity> getAttendances() {
        return ifEntityListHasDeletedElement(attendances);
    }

    public List<MeetingEntity> getMeetings() {
        return ifEntityListHasDeletedElement(meetings);
    }

    @AssertTrue(message = "general.dates.range")
    public boolean isValidDateRange() {
        if (ltFromDate == null)
            return true;
        return ltFromDate.isBefore(ltToDate);
    }

    @AssertTrue(message = "{plansEntity.element.status}")
    public boolean isValidElementStatus() {
        if (elementStatus == null)
            return true;
        return elementStatus.getElementGrp().getLtTitle().equals("plan_status");
    }

    @AssertTrue(message = "{plansEntity.element.type}")
    public boolean isValidElementType() {
        if (elementType == null)
            return true;
        return elementType.getElementGrp().getLtTitle().equals("plan_type");
    }

}
