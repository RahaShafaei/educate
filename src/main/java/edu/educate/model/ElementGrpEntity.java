package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.TitleLPEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("main.field.ltTitle"));
        headers.add(MessageUtil.getMessage("main.field.prTitle"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(getLtTitle() != null ? getLtTitle() : null);
        objects.add(getPrTitle() != null ? getPrTitle() : null);
        return objects;
    }

    public List<ElementEntity> getElements() {
        return ifEntityListHasDeletedElement(elements);
    }
}
