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
public class PlansEntity extends BaseEntity {
    @NotNull
    @Size(min = 2, message = "Title should have at least 2 character.")
    @Column(name = "title")
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

    @AssertTrue(message = "The 'from_date' must be less than 'to_date'")
    private boolean isValidDateRange() {
        return fromDate.before(toDate);
    }

    @AssertTrue(message = "For 'elementStatus' the ElementGrp must be 'plan_status'.")
    private boolean isValidElementStatus() {
        return elementStatus.getElementGrp().getTitle().equals("plan_status");
    }

    @AssertTrue(message = "For 'elementType' the ElementGrp must be 'plan_type'.")
    private boolean isValidElementType() {
        return elementType.getElementGrp().getTitle().equals("plan_type");
    }

}
