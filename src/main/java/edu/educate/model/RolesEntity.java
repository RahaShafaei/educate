package edu.educate.model;

import edu.educate.model.baseModel.TitleLPEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "roles", schema = "dbo", catalog = "educate")
public class RolesEntity extends TitleLPEntity {

    @ManyToMany(mappedBy = "personRoles")
    private List<PersonEntity> personRoles;

    public List<PersonEntity> getPersonRoles() {
        return ifEntityListHasDeletedElement(personRoles);
    }
}
