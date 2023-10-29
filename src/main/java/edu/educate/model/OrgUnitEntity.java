package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "org_unit", schema = "dbo", catalog = "educate")
public class OrgUnitEntity extends TitleEntity {
    @OneToMany(mappedBy = "parentOrgUnit")
    private List<OrgUnitEntity> orgUnit;

    @ManyToOne
    @Column(name = "parent_org_unit_id", nullable = true)
    private OrgUnitEntity parentOrgUnit;

    @OneToMany(mappedBy = "orgUnit")
    private List<PlansEntity> plans;

    @OneToMany(mappedBy = "orgUnit")
    private List<OrgUnitPostPersonEntity> orgUnitPostPersons;

    @Column(name = "code", nullable = true, length = 50)
    private String code;

    @Column(name = "descr", nullable = true, length = 255)
    private String descr;

}
