package edu.educate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.CustomDateDeserializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "org_unit_post_person", schema = "dbo", catalog = "educate")
public class OrgUnitPostPersonEntity extends BaseEntity {
    @OneToMany(mappedBy = "orgUnitPostPerson")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "orgUnitPostPerson")
    private List<PlansEntity> plans;

    @ManyToOne
    @Column(name = "org_unit_id", nullable = false)
    private OrgUnitEntity orgUnit;

    @ManyToOne
    @Column(name = "org_post_id", nullable = true)
    private OrgPostEntity orgPost;

    @ManyToOne
    @Column(name = "person_id", nullable = false)
    private PersonEntity person;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "from_date", nullable = true)
    private Date fromDate;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "to_date", nullable = true)
    private Date toDate;

}
