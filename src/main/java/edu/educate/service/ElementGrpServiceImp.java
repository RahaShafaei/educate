package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
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

    @Override
    public List<ElementGrpEntity> getElementGrps() {
        return elementGrpRepository.findAll();
    }

    @Override
    public ElementGrpEntity getElementGrp(Integer id) {
        Optional<ElementGrpEntity> elementGrp = elementGrpRepository.findById(id);

        if (elementGrp.isEmpty())
            throw new ItemNotFoundException(ELEMENTGRP_ID + id);

        return elementGrp.get();
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
    public ElementGrpEntity createElementGrp(ElementGrpEntity elementGrp) {

        ElementGrpEntity savedElementGrp = elementGrpRepository.save(elementGrp);

        return savedElementGrp;
    }
}
