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
        name = "element",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"lt_title", "element_grp_id"})}
)
public class ElementEntity  extends TitleLPEntity {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_grp_id")
    private ElementGrpEntity elementGrp;

    @OneToMany(mappedBy = "element")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "elementType")
    private List<PlansEntity> planTypes;

    @OneToMany(mappedBy = "elementStatus")
    private List<PlansEntity> planStatus;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        headers.add(MessageUtil.getMessage("main.field.grp.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.grp.prTitle"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(getLtTitle() != null ? getLtTitle() : null);
        objects.add(getPrTitle() != null ? getPrTitle() : null);
        objects.add(elementGrp != null ? elementGrp.getLtTitle() : null);
        objects.add(elementGrp != null ? elementGrp.getPrTitle() : null);
        return objects;
    }

    public ElementGrpEntity getElementGrp() {
        return (ElementGrpEntity)ifEntityIsDeleted(elementGrp);
    }

    public List<AttendanceEntity> getAttendances() {
        return ifEntityListHasDeletedElement(attendances);
    }

    public List<PlansEntity> getPlanTypes() {
        return ifEntityListHasDeletedElement(planTypes);
    }

    public List<PlansEntity> getPlanStatus() {
        return ifEntityListHasDeletedElement(planStatus);
    }
}
