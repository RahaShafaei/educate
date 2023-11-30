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
                                "from_date",
                                "to_date"
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
    @Column(name = "from_date" , columnDefinition = "datetime")
    private LocalDateTime fromDate;

//    @JsonDeserialize(using = CustomDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "to_date" , columnDefinition = "datetime")
    private LocalDateTime toDate;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("orgUnit.field.title"));
        headers.add(MessageUtil.getMessage("post.field.title"));
        headers.add(MessageUtil.getMessage("person.field.fname"));
        headers.add(MessageUtil.getMessage("person.field.lname"));
        headers.add(MessageUtil.getMessage("plan.field.from.date"));
        headers.add(MessageUtil.getMessage("plan.field.to.date"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(orgUnit != null ? orgUnit.getTitle() : null);
        objects.add(orgPost != null ? orgPost.getTitle() : null);
        objects.add(person != null ? person.getFname() : null);
        objects.add(person != null ? person.getLname() : null);
        objects.add(fromDate != null ? fromDate : null);
        objects.add(toDate != null ? toDate : null);
        return objects;
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
        if (toDate == null) {
            return true;
        }

        return fromDate.isBefore(toDate);
    }

}
