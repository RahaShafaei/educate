package edu.educate.service;

import edu.educate.dto.ElementGrpDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.ElementGrpEntity;

import java.util.List;

public interface ElementGrpService {
    public List<ElementGrpDto> getElementGrps();

    public ElementGrpDto getElementGrp(Integer id);

    public List<PersonDto> getElementGrpPersons(Integer id);

    public Boolean deleteElementGrp(Integer id);

    public ElementGrpDto createElementGrp(ElementGrpEntity ElementGrp);
}
