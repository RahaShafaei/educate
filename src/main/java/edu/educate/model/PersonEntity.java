package edu.educate.model;

import edu.educate.model.baseModel.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "person", schema = "dbo", catalog = "educate")
public class PersonEntity extends BaseEntity {
    @OneToMany(mappedBy = "person")
    private List<OrgUnitPostPersonEntity> orgUnitPostPersons;

    @OneToMany(mappedBy = "person")
    private List<PersonRoleEntity> personRoles;

    @NotNull
    @Size(min = 2, message = "FirstName should have at least 2 character.")
    @Column(name = "fname", length = 50)
    private String fname;

    @NotNull
    @Size(min = 2, message = "LastName should have at least 2 character.")
    @Column(name = "lname", length = 50)
    private String lname;

    @Size(min = 2, message = "FatherName should have at least 2 character.")
    @Column(name = "father_name", length = 50)
    private String fatherName;

    @Size(min = 10,max = 10, message = "NationalCode should have 10 character.")
    @Column(name = "nl_code", length = 50)
    private String nlCode;

    @Size(min = 5, message = "PersonalCode should have at least 5 character.")
    @Column(name = "pr_code", length = 50)
    private String prCode;

    @Size(min = 10,max = 10, message = "PhoneNumber should have 11 character.")
    @Column(name = "tel", length = 50)
    private String tel;

}
