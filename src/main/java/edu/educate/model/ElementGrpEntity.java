package edu.educate.model;

import edu.educate.model.baseModel.TitleLPEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "element_grp", schema = "dbo", catalog = "educate")
public class ElementGrpEntity extends TitleLPEntity {

    @OneToMany(mappedBy = "elementGrp")
    private List<ElementEntity> elements;

    public List<ElementEntity> getElements() {
        return ifEntityListHasDeletedElement(elements);
    }
}
