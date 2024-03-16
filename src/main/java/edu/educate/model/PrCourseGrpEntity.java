package edu.educate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.TitleLPEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")

@Entity
@Table(
        name = "pr_course_grp",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"lt_title", "pr_title"})}
)
public class PrCourseGrpEntity extends TitleLPEntity {
    @OneToMany(mappedBy = "prCourseGrp")
    private List<PrCourseEntity> prCourses;

    @Column(name = "descr", length = 255)
    private String descr;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.descr"));
        return headers;
    }

    @Override
    public List<List<Object>> getCellValues() {
        List<List<Object>> objectsContainer = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        objects.add(getLtTitle() != null ? getLtTitle() : null);
        objects.add(getPrTitle() != null ? getPrTitle() : null);
        objects.add(descr != null ? descr : null);
        objectsContainer.add(objects);
        return objectsContainer;
    }

    public List<PrCourseEntity> getPrCourses() {
        return ifEntityListHasDeletedElement(prCourses);
    }
}
