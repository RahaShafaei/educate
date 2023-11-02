package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.model.ElementEntity;
import edu.educate.repository.ElementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("elementService")
public class ElementServiceImp implements ElementService{

    private static final String ELEMENT_ID = "Element id: ";
    private final ElementRepository elementRepository;
    @Override
    public List<ElementEntity> getElements() {
        return elementRepository.findAll();
    }

    @Override
    public ElementEntity getElement(Integer id) {
        Optional<ElementEntity> element = elementRepository.findById(id);

        if (element.isEmpty())
            throw new ItemNotFoundException(ELEMENT_ID + id);

        return element.get();
    }

    @Override
    public Boolean deleteElement(Integer id) {
        Optional<ElementEntity> element = elementRepository.findById(id);

        if (!element.isEmpty()) {
            elementRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ElementEntity createElement(ElementEntity element) {

        ElementEntity savedElement = elementRepository.save(element);

        return savedElement;
    }
}
