package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.DoubleLength;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "attendance", schema = "dbo", catalog = "educate")
public class AttendanceEntity extends BaseEntity {

    @ManyToOne
    @Column(name = "org_unit_post_person_id", nullable = true)
    private OrgUnitPostPersonEntity orgUnitPostPerson;

    @ManyToOne
    @Column(name = "plan_id", nullable = true)
    private PlansEntity plan;

    @ManyToOne
    @Column(name = "element_id", nullable = true)
    private ElementEntity element;

    @DoubleLength(minLength = 3)
    @Column(name = "grade", nullable = true, precision = 0)
    private Double grade;

}
