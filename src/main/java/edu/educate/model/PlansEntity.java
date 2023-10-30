package edu.educate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.educate.model.baseModel.TitleEntity;
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
@Table(name = "plans", schema = "dbo", catalog = "educate")
public class PlansEntity extends TitleEntity {
    @ManyToOne
    @JoinColumn(name = "org_unit_id", nullable = true)
    private OrgUnitEntity orgUnit;

    @ManyToOne
    @JoinColumn(name = "pr_course_id", nullable = true)
    private PrCourseEntity prCourse;

    @ManyToOne
    @JoinColumn(name = "org_unit_post_person_id", nullable = true)
    private OrgUnitPostPersonEntity orgUnitPostPerson;

    @ManyToOne
    @JoinColumn(name = "element_id_type", nullable = true)
    private ElementEntity elementType;

    @ManyToOne
    @JoinColumn(name = "element_id_status", nullable = true)
    private ElementEntity elementStatus;

    @OneToMany(mappedBy = "plan")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "plan")
    private List<MeetingEntity> meetings;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "from_date", nullable = true)
    private Date fromDate;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "to_date", nullable = true)
    private Date toDate;

}
