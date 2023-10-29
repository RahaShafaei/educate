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
@Table(name = "roles", schema = "dbo", catalog = "educate")
public class RolesEntity extends TitleEntity {
    @OneToMany(mappedBy = "role")
    private List<PersonRoleEntity> personRoles;

}
