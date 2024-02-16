package edu.educate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.TitleLPEntity;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.grp.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.grp.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.descr"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(getLtTitle() != null ? getLtTitle() : null);
        objects.add(getPrTitle() != null ? getPrTitle() : null);
        objects.add(prCourseGrp != null ? prCourseGrp.getLtTitle() : null);
        objects.add(prCourseGrp != null ? prCourseGrp.getPrTitle() : null);
        objects.add(descr != null ? descr : null);
        return objects;
    }

    public PrCourseGrpEntity getPrCourseGrp() {
        return (PrCourseGrpEntity)ifEntityIsDeleted(prCourseGrp);
    }

    public List<PlansEntity> getPlans() {
        return ifEntityListHasDeletedElement(plans);
    }
}
