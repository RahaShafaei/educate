package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.FloatLength;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(
        name = "attendance",
        schema = "dbo",
        catalog = "educate"
        ,
        uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id", "plan_id"})}
)
public class AttendanceEntity extends BaseEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
//    @NotNull
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementEntity element;

    @FloatLength(minLength = 3)
    @Column(name = "grade")
    private Float grade;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("person.field.fname"));
        headers.add(MessageUtil.getMessage("person.field.lname"));
        headers.add(MessageUtil.getMessage("plan.page.title"));
        headers.add(MessageUtil.getMessage("attendance.field.status"));
        headers.add(MessageUtil.getMessage("attendance.field.grade"));
        return headers;
    }

    @Override
    public List<List<Object>> getCellValues() {
        List<List<Object>> objectsContainer = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        objects.add(person != null ? person.getFname() : null);
        objects.add(person != null ? person.getLname() : null);
        objects.add(plan != null ? plan.getTitle() : null);
        objects.add(element != null ? element.getPrTitle() : null);
        objects.add(grade != null ? grade : null);
        objectsContainer.add(objects);
        return objectsContainer;
    }

    public PersonEntity getPerson() {
        return (PersonEntity) ifEntityIsDeleted(person);
    }

    public PlansEntity getPlan() {
        return (PlansEntity) ifEntityIsDeleted(plan);
    }

    public ElementEntity getElement() {
        return (ElementEntity) ifEntityIsDeleted(element);
    }

//    @AssertTrue(message = "{attendanceEntity.element}")
//    public boolean isValidElement() {
//        if (element == null) {
//            return true;
//        }
//
//        return element.getElementGrp().getLtTitle().equals("attendance_status");
//    }

//    @AssertTrue(message = "{attendanceEntity.element.present}")
//    public boolean isGradeForPresentAttendance() {
//        if (grade == null) {
//            return true;
//        }
//
//        return element.getLtTitle().equals("Present");
//    }

//    @AssertTrue(message = "{attendanceEntity.plan}")
//    public boolean isValidPlan() {
//        if (plan == null) {
//            return true;
//        }
//
//        return !plan.getElementStatus().getLtTitle().equals("Planning");
//    }

}
