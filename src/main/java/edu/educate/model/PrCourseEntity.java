package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "pr_course", schema = "dbo", catalog = "educate")
public class PrCourseEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "pr_course_grp_id", nullable = true)
    private PrCourseGrpEntity prCourseGrp;

    @OneToMany(mappedBy = "prCourse")
    private List<PlansEntity> plans;

    @Size(min = 2, message = "LatinTitle should have at least 2 character.")
    @Column(name = "lt_title", nullable = true, length = 50)
    private String ltTitle;

    @Size(min = 2, message = "PersianTitle should have at least 2 character.")
    @Column(name = "pr_title", nullable = true, length = 50)
    private String prTitle;

    @Column(name = "descr", nullable = true, length = 255)
    private String descr;

}
