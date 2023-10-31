package edu.educate.service;

import edu.educate.dto.ElementMapper;
import edu.educate.dto.ElementDto;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.ElementEntity;
import edu.educate.model.ElementEntity;
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
    private final ElementMapper elementMapper;
    @Override
    public List<ElementDto> getElements() {
        return elementRepository.findAll()
                .stream()
                .map(elementMapper::toDto)
                .toList();
    }

    @Override
    public ElementDto getElement(Integer id) {
        Optional<ElementEntity> element = elementRepository.findById(id);

        if (element.isEmpty())
            throw new ItemNotFoundException(ELEMENT_ID + id);

        return elementMapper.toDto(element.get());
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
    public ElementDto createElement(ElementEntity element) {
        if (element.getTitle() == null || element.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of Element should not be empty.");

        ElementEntity savedElement = elementRepository.save(element);

        return elementMapper.toDto(savedElement);
    }
}
