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
@Table(name = "element_grp", schema = "dbo", catalog = "educate")
public class ElementGrpEntity extends TitleEntity {

    @OneToMany(mappedBy = "elementGrp")
    private List<ElementEntity> elements;
}
