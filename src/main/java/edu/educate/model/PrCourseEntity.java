package edu.educate.model;

import edu.educate.model.baseModel.TitleLPEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "pr_course",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames =
                        {
                                "pr_course_grp_id",
                                "lt_title",
                                "pr_title"
                        })
        }
)
public class PrCourseEntity extends TitleLPEntity {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "pr_course_grp_id", nullable = false)
    private PrCourseGrpEntity prCourseGrp;

    @OneToMany(mappedBy = "prCourse")
    private List<PlansEntity> plans;

    @Column(name = "descr", length = 255)
    private String descr;

}
