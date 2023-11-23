package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @AssertTrue(message = "{orgUnitEntity.parentOrgUnit}")
    public boolean isValidParentOrgUnit() {
        if (parentOrgUnit == null) {
            return true;
        }

        return !Objects.equals(parentOrgUnit.getId(), this.getId());
    }

}
