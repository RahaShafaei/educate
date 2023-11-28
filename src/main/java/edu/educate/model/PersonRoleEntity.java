package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(
        name = "person_role",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"person_id", "role_id"})
        }
)
public class PersonRoleEntity extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity role;

    public PersonEntity getPerson() {
        return (PersonEntity)ifEntityIsDeleted(person);
    }

    public RolesEntity getRole() {
        return (RolesEntity)ifEntityIsDeleted(role);
    }
}
