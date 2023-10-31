package edu.educate.service;

import edu.educate.model.ElementGrpEntity;

import java.util.List;

public interface ElementGrpService {
    public List<ElementGrpEntity> getElementGrps();

    public ElementGrpEntity getElementGrp(Integer id);

    public Boolean deleteElementGrp(Integer id);

    public ElementGrpEntity createElementGrp(ElementGrpEntity elementGrp);
}
