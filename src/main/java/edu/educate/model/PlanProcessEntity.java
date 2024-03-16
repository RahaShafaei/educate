package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
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

    //    @NotNull
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "process_id")
    private ProcessEntity process;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "lt_from_date", columnDefinition = "datetime")
    private LocalDateTime ltFromDate;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "lt_to_date", columnDefinition = "datetime")
    private LocalDateTime ltToDate;

    //    @NotNull
    @Column(name = "pr_from_date")
    private String prFromDate;

    //    @NotNull
    @Column(name = "pr_to_date")
    private String prToDate;

//    private String dateDiff;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("plan.page.title"));
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("plan.field.from.date"));
        headers.add(MessageUtil.getMessage("plan.field.to.date"));
        headers.add(MessageUtil.getMessage("process.field.duration"));
        return headers;
    }

    @Override
    public List<List<Object>> getCellValues() {
        List<List<Object>> objectsContainer = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        objects.add(plan != null ? plan.getTitle() : null);
        objects.add(process != null ? process.getLtTitle() : null);
        objects.add(process != null ? process.getPrTitle() : null);
        objects.add(prFromDate != null ? prFromDate : null);
        objects.add(prToDate != null ? prToDate : null);
        objects.add(ltFromDate != null && ltToDate != null ? getDateDiff(): null);
        objectsContainer.add(objects);
        return objectsContainer;
    }

    public PlansEntity getPerson() {
        return (PlansEntity) ifEntityIsDeleted(plan);
    }

    public ProcessEntity getRole() {
        return (ProcessEntity) ifEntityIsDeleted(process);
    }

    public String getDateDiff() {
        Duration duration = Duration.between(ltFromDate, ltToDate);
        long seconds = duration.getSeconds();
        return  seconds / 3600 + ":"
                + (seconds % 3600) / 60+ ":"
                + seconds % 60;
    }

    @AssertTrue(message = "general.dates.range")
    public boolean isValidDateRange() {
        if (ltFromDate == null)
            return true;
        return ltFromDate.isBefore(ltToDate);
    }

}
