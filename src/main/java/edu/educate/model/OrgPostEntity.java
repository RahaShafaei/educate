package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "org_post", schema = "dbo", catalog = "educate")
public class OrgPostEntity extends TitleEntity {
    @OneToMany(mappedBy = "orgPost")
    private List<OrgUnitPostPersonEntity> orgUnitPostPersons;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "descr", length = 255)
    private String descr;

}
