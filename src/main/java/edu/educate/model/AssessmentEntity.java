package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.validator.FloatLength;
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
        name = "assessment",
        schema = "dbo",
        catalog = "educate"
)
public class AssessmentEntity extends BaseEntity {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @NotNull
    @Column(name = "question", columnDefinition = "nvarchar(max)")
    private String question;

    @FloatLength(minLength = 3)
    @Column(name = "score")
    private Float score;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("plan.page.title"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("course.page.title") + "_"+MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.from.date"));
        headers.add(MessageUtil.getMessage("person.field.master.fname"));
        headers.add(MessageUtil.getMessage("person.field.master.lname"));
        headers.add(MessageUtil.getMessage("assessment.field.question"));
        headers.add(MessageUtil.getMessage("assessment.field.score"));
        return headers;
    }

    @Override
    public List<List<Object>> getCellValues() {
        List<List<Object>> objectsContainer = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        objects.add(plan != null ? plan.getTitle() : null);
        objects.add(plan != null ? plan.getPrCourse().getPrCourseGrp().getLtTitle() : null);
        objects.add(plan != null ? plan.getPrCourse().getPrCourseGrp().getPrTitle() : null);
        objects.add(plan != null ? plan.getPrFromDate() : null);
        objects.add(plan != null ? plan.getPerson().getFname() : null);
        objects.add(plan != null ? plan.getPerson().getLname() : null);
        objects.add(question != null ? question : null);
        objects.add(score != null ? score : null);
        objectsContainer.add(objects);
        return objectsContainer;
    }

    public PlansEntity getPlan() {
        return (PlansEntity) ifEntityIsDeleted(plan);
    }
}
