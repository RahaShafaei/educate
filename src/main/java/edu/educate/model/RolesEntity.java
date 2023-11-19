package edu.educate.model;

import edu.educate.model.baseModel.TitleLPEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles", schema = "dbo", catalog = "educate")
public class RolesEntity extends TitleLPEntity {

    @ManyToMany(mappedBy = "personRoles")
    private List<PersonEntity> personRoles;

}
