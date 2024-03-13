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

    @NotNull
    @Column(name = "score")
    private Integer score;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("assessment.field.question"));
        headers.add(MessageUtil.getMessage("assessment.field.score"));
        headers.add(MessageUtil.getMessage("plan.page.title"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(question != null ? question : null);
        objects.add(score != null ? score : null);
        objects.add(plan != null ? plan.getTitle() : null);
        return objects;
    }

    public PlansEntity getPlan() {
        return (PlansEntity) ifEntityIsDeleted(plan);
    }
}
