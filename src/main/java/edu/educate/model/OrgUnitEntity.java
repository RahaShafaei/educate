package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "org_unit", schema = "dbo", catalog = "educate")
public class OrgUnitEntity extends TitleEntity {
    @OneToMany(mappedBy = "parentOrgUnit")
    private List<OrgUnitEntity> orgUnits;

    @ManyToOne
    @JoinColumn(name = "parent_org_unit_id")
    private OrgUnitEntity parentOrgUnit;

    @OneToMany(mappedBy = "orgUnit")
    private List<PlansEntity> plans;

    @OneToMany(mappedBy = "orgUnit")
    private List<OrgUnitPostPersonEntity> orgUnitPostPersons;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "descr", length = 255)
    private String descr;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("orgUnit.field.parent.title"));
        headers.add(MessageUtil.getMessage("post.field.title"));
        headers.add(MessageUtil.getMessage("main.field.code"));
        headers.add(MessageUtil.getMessage("main.field.descr"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(parentOrgUnit != null ? parentOrgUnit.getTitle() : null);
        objects.add(getTitle() != null ? getTitle() : null);
        objects.add(code != null ? code : null);
        objects.add(descr != null ? descr : null);
        return objects;
    }

    public OrgUnitEntity getParentOrgUnit() {
        return (OrgUnitEntity)ifEntityIsDeleted(parentOrgUnit);
    }

    public List<OrgUnitEntity> getOrgUnits() {
        return ifEntityListHasDeletedElement(orgUnits);
    }

    public List<PlansEntity> getPlans() {
        return ifEntityListHasDeletedElement(plans);
    }

    public List<OrgUnitPostPersonEntity> getOrgUnitPostPersons() {
        return ifEntityListHasDeletedElement(orgUnitPostPersons);
    }

    @AssertTrue(message = "{orgUnitEntity.parentOrgUnit}")
    public boolean isValidParentOrgUnit() {
        if (parentOrgUnit == null) {
            return true;
        }

        return !Objects.equals(parentOrgUnit.getId(), this.getId());
    }

}
