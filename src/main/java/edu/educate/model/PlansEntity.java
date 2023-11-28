package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
                                "from_date",
                                "to_date"
                        })
        }
)
public class PlansEntity extends TitleEntity {
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

    @OneToMany(mappedBy = "plan")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "plan")
    private List<MeetingEntity> meetings;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "from_date" , columnDefinition = "datetime")
    private LocalDateTime fromDate;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "to_date" , columnDefinition = "datetime")
    private LocalDateTime toDate;

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

    public List<AttendanceEntity> getAttendances() {
        return ifEntityListHasDeletedElement(attendances);
    }

    public List<MeetingEntity> getMeetings() {
        return ifEntityListHasDeletedElement(meetings);
    }

    @AssertTrue(message = "general.dates.range")
    public boolean isValidDateRange() {
        if (fromDate == null)
            return true;
        return fromDate.isBefore(toDate);
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
