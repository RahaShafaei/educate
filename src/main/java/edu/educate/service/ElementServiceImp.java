package edu.educate.service;

import edu.educate.dto.ElementDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.ElementEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("elementService")
public class ElementServiceImp implements ElementService{
    @Override
    public List<ElementDto> getElements() {
        return null;
    }

    @Override
    public ElementDto getElement(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getElementPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteElement(Integer id) {
        return null;
    }

    @Override
    public ElementDto createElement(ElementEntity Element) {
        return null;
    }
}
