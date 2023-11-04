package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.FloatLength;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "attendance",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"org_unit_post_person_id", "plan_id"})}
)
public class AttendanceEntity extends BaseEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_unit_post_person_id")
    private OrgUnitPostPersonEntity orgUnitPostPerson;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementEntity element;

    @FloatLength(minLength = 3)
    @Column(name = "grade")
    private Float grade;

    @AssertTrue(message = "{attendanceEntity.element}")
    private boolean isValidElement() {
        if (element == null) {
            return true;
        }

        return element.getElementGrp().getTitle().equals("attendance_status");
    }

    @AssertTrue(message = "{attendanceEntity.element.present}")
    private boolean isGradeForPresentAttendance() {
        if (grade == null) {
            return true;
        }

        return element.getTitle().equals("Present");
    }

    @AssertTrue(message = "{attendanceEntity.plan}")
    private boolean isValidPlan() {
        if (element == null) {
            return true;
        }

        return !plan.getElementStatus().getTitle().equals("Planning");
    }

}
