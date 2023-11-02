package edu.educate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.CustomDateDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "org_unit_post_person",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames =
                        {
                                "org_unit_id",
                                "org_post_id",
                                "person_id",
                                "from_date",
                                "to_date"
                        })
        }
)
public class OrgUnitPostPersonEntity extends BaseEntity {
    @OneToMany(mappedBy = "orgUnitPostPerson")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "orgUnitPostPerson")
    private List<PlansEntity> plans;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_unit_id")
    private OrgUnitEntity orgUnit;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_post_id")
    private OrgPostEntity orgPost;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "from_date")
    private Date fromDate;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "to_date")
    private Date toDate;

    @AssertTrue(message = "The 'from_date' must be less than 'to_date'")
    private boolean isValidDateRange() {
        if (fromDate == null || toDate == null) {
            return true;
        }

        return fromDate.before(toDate);
    }

}
