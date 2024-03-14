package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.BaseEntity;
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
        name = "plan_process",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"plan_id", "process_id"})
        }
)
public class PlanProcessEntity extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "process_id")
    private ProcessEntity process;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("plan.page.title"));
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(plan != null ? plan.getTitle() : null);
        objects.add(process != null ? process.getLtTitle() : null);
        objects.add(process != null ? process.getPrTitle() : null);
        return objects;
    }

    public PlansEntity getPerson() {
        return (PlansEntity)ifEntityIsDeleted(plan);
    }

    public ProcessEntity getRole() {
        return (ProcessEntity)ifEntityIsDeleted(process);
    }

}
