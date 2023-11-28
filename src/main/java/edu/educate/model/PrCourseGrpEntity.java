package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.LengthOrEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public List<PrCourseEntity> getPrCourses() {
        return ifEntityListHasDeletedElement(prCourses);
    }
}
