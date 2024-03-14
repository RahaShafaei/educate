package edu.educate.model;

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
@Table(
        name = "process",
        schema = "dbo",
        catalog = "educate"
)
public class ProcessEntity extends TitleLPEntity {

    @ManyToMany(mappedBy = "planProcess")
    private List<PlansEntity> planProcess;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "pr_course_id", nullable = false)
    private PrCourseEntity prCourse;

    @Column(name = "descr", length = 255)
    private String descr;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.descr"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(prCourse != null ? prCourse.getLtTitle() : null);
        objects.add(prCourse != null ? prCourse.getPrTitle() : null);
        objects.add(getLtTitle() != null ? getLtTitle() : null);
        objects.add(getPrTitle() != null ? getPrTitle() : null);
        objects.add(descr != null ? descr : null);
        return objects;
    }

    public PrCourseEntity getPrCourse() {
        return (PrCourseEntity)ifEntityIsDeleted(prCourse);
    }
    public List<PlansEntity> getPersonRoles() {
        return ifEntityListHasDeletedElement(planProcess);
    }

}
