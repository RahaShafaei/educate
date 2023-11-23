package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.FloatLength;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(
        name = "attendance",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id", "plan_id"})}
)
public class AttendanceEntity extends BaseEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id")
    private PersonEntity person;

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

        return element.getElementGrp().getLtTitle().equals("attendance_status");
    }

    @AssertTrue(message = "{attendanceEntity.element.present}")
    private boolean isGradeForPresentAttendance() {
        if (grade == null) {
            return true;
        }

        return element.getLtTitle().equals("Present");
    }

    @AssertTrue(message = "{attendanceEntity.plan}")
    private boolean isValidPlan() {
        if (element == null) {
            return true;
        }

        return !plan.getElementStatus().getLtTitle().equals("Planning");
    }

}
