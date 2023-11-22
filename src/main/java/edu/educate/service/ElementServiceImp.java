package edu.educate.service;

import edu.educate.dto.ElementDto;
import edu.educate.model.ElementEntity;
import edu.educate.repository.ElementRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("elementService")
public class ElementServiceImp extends GenericServiceImpl<ElementEntity, ElementDto> implements ElementService {

    @Autowired
    public ElementServiceImp(ElementRepository repository) {
        super(repository, "ElementEntity");
    }
}