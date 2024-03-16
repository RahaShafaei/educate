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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(
        name = "org_unit_post_person",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames =
                        {
                                "org_unit_id",
                                "org_post_id",
                                "person_id",
                                "lt_from_date",
                                "lt_to_date"
                        })
        }
)
public class OrgUnitPostPersonEntity extends BaseEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_unit_id")
    private OrgUnitEntity orgUnit;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "org_post_id")
    private OrgPostEntity orgPost;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "lt_from_date" , columnDefinition = "datetime")
    private LocalDateTime ltFromDate;

    //    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "lt_to_date" , columnDefinition = "datetime")
    private LocalDateTime ltToDate;

//    @NotNull
    @Column(name = "pr_from_date")
    private String prFromDate;

    @Column(name = "pr_to_date")
    private String prToDate;

    @Column(name = "location")
    private String location;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("orgUnit.field.title"));
        headers.add(MessageUtil.getMessage("post.field.title"));
        headers.add(MessageUtil.getMessage("person.field.fname"));
        headers.add(MessageUtil.getMessage("person.field.lname"));
        headers.add(MessageUtil.getMessage("plan.field.from.date"));
        headers.add(MessageUtil.getMessage("plan.field.to.date"));
        headers.add(MessageUtil.getMessage("person.field.location"));
        return headers;
    }

    @Override
    public List<List<Object>> getCellValues() {
        List<List<Object>> objectsContainer = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        objects.add(orgUnit != null ? orgUnit.getTitle() : null);
        objects.add(orgPost != null ? orgPost.getTitle() : null);
        objects.add(person != null ? person.getFname() : null);
        objects.add(person != null ? person.getLname() : null);
        objects.add(prFromDate != null ? prFromDate : null);
        objects.add(prToDate != null ? prToDate : null);
        objects.add(location != null ? location : null);
        objectsContainer.add(objects);
        return objectsContainer;
    }

    public PersonEntity getPerson() {
        return (PersonEntity)ifEntityIsDeleted(person);
    }

    public OrgUnitEntity getOrgUnit() {
        return (OrgUnitEntity)ifEntityIsDeleted(orgUnit);

    }

    public OrgPostEntity getOrgPost() {
        return (OrgPostEntity)ifEntityIsDeleted(orgPost);
    }

    @AssertTrue(message = "{general.dates.range}")
    public boolean isValidDateRange() {
        if (ltToDate == null) {
            return true;
        }

        return ltFromDate.isBefore(ltToDate);
    }

}
