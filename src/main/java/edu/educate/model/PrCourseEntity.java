package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Collection;
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
public class PrCourseEntity extends BaseEntity {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "pr_course_grp_id", nullable = false)
    private PrCourseGrpEntity prCourseGrp;

    @OneToMany(mappedBy = "prCourse")
    private List<PlansEntity> plans;

    @NotNull
    @Size(min = 2, message = "{general.ltTitle}")
    @Column(name = "lt_title", length = 50 )
    private String ltTitle;

    @NotNull
    @Size(min = 2, message = "{general.prTitle}")
    @Column(name = "pr_title", length = 50)
    private String prTitle;

    @Column(name = "descr", length = 255)
    private String descr;

}
