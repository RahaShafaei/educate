package edu.educate.service;

import edu.educate.dto.ElementGrpMapper;
import edu.educate.dto.ElementGrpDto;
import edu.educate.dto.ElementGrpMapper;
import edu.educate.dto.PersonDto;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.ElementGrpEntity;
import edu.educate.model.ElementGrpEntity;
import edu.educate.repository.ElementGrpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("elementGroService")
public class ElementGrpServiceImp implements ElementGrpService{

    private static final String ELEMENTGRP_ID = "ElementGrp id: ";
    private final ElementGrpRepository elementGrpRepository;
    private final ElementGrpMapper elementGrpMapper;

    @Override
    public List<ElementGrpDto> getElementGrps() {
        return elementGrpRepository.findAll()
                .stream()
                .map(elementGrpMapper::toDto)
                .toList();
    }

    @Override
    public ElementGrpDto getElementGrp(Integer id) {
        Optional<ElementGrpEntity> elementGrp = elementGrpRepository.findById(id);

        if (elementGrp.isEmpty())
            throw new ItemNotFoundException(ELEMENTGRP_ID + id);

        return elementGrpMapper.toDto(elementGrp.get());
    }

    @Override
    public Boolean deleteElementGrp(Integer id) {
        Optional<ElementGrpEntity> elementGrp = elementGrpRepository.findById(id);

        if (!elementGrp.isEmpty()) {
            elementGrpRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ElementGrpDto createElementGrp(ElementGrpEntity elementGrp) {
        if (elementGrp.getTitle() == null || elementGrp.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of ElementGrp should not be empty.");

        ElementGrpEntity savedElementGrp = elementGrpRepository.save(elementGrp);

        return elementGrpMapper.toDto(savedElementGrp);
    }
}
