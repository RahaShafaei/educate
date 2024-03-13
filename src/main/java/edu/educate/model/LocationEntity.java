package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.Column;
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
@Table(name = "org_post", schema = "dbo", catalog = "educate")
public class LocationEntity extends TitleEntity {
    @OneToMany(mappedBy = "location")
    private List<OrgUnitEntity> orgUnits;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "descr", length = 255)
    private String descr;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("post.field.title"));
        headers.add(MessageUtil.getMessage("main.field.code"));
        headers.add(MessageUtil.getMessage("main.field.descr"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(getTitle() != null ? getTitle() : null);
        objects.add(code != null ? code : null);
        objects.add(descr != null ? descr : null);
        return objects;
    }

    public List<OrgUnitEntity> getOrgUnits() {
        return ifEntityListHasDeletedElement(orgUnits);
    }
}
