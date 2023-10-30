package edu.educate.service;

import edu.educate.dto.ElementDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.ElementEntity;

import java.util.List;

public interface ElementService {
    public List<ElementDto> getElements();

    public ElementDto getElement(Integer id);

    public List<PersonDto> getElementPersons(Integer id);

    public Boolean deleteElement(Integer id);

    public ElementDto createElement(ElementEntity Element);
}
