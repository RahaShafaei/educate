package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.model.baseModel.TitleEntity;
import edu.educate.validator.LengthOrEmpty;
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
//@ToString
@Entity
@Table(
        name = "pr_course_grp",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"lt_title", "pr_title"})}
)
public class PrCourseGrpEntity extends BaseEntity {
    @OneToMany(mappedBy = "prCourseGrp")
    private List<PrCourseEntity> prCourses;

    @NotNull
    @LengthOrEmpty(min = 2, max = 255, message = "{general.ltTitle}")
    @Column(name = "lt_title", length = 255)
    private String ltTitle;

    @NotNull
    @LengthOrEmpty(min = 2, max = 255, message = "{general.prTitle}")
    @Column(name = "pr_title", length = 255)
    private String prTitle;

    @Column(name = "descr", length = 255)
    private String descr;

}
