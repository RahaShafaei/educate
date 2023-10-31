package edu.educate.service;

import edu.educate.model.ElementEntity;

import java.util.List;

public interface ElementService {
    public List<ElementEntity> getElements();

    public ElementEntity getElement(Integer id);

    public Boolean deleteElement(Integer id);

    public ElementEntity createElement(ElementEntity element);
}
