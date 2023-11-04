package edu.educate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.model.baseModel.TitleEntity;
import edu.educate.validator.CustomDateDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
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
                                "org_unit_post_person_id",
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
    @JoinColumn(name = "org_unit_post_person_id")
    private OrgUnitPostPersonEntity orgUnitPostPerson;

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

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @NotNull
    @Column(name = "from_date")
    private Date fromDate;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @NotNull
    @Column(name = "to_date")
    private Date toDate;

    @AssertTrue(message = "general.dates.range")
    private boolean isValidDateRange() {
        return fromDate.before(toDate);
    }

    @AssertTrue(message = "{plansEntity.element.status}")
    private boolean isValidElementStatus() {
        return elementStatus.getElementGrp().getTitle().equals("plan_status");
    }

    @AssertTrue(message = "{plansEntity.element.type}")
    private boolean isValidElementType() {
        return elementType.getElementGrp().getTitle().equals("plan_type");
    }

}
