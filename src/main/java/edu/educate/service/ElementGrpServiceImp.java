package edu.educate.service;

import edu.educate.dto.ElementGrpDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.ElementGrpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("elementGroService")
public class ElementGrpServiceImp implements ElementGrpService{
    @Override
    public List<ElementGrpDto> getElementGrps() {
        return null;
    }

    @Override
    public ElementGrpDto getElementGrp(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getElementGrpPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteElementGrp(Integer id) {
        return null;
    }

    @Override
    public ElementGrpDto createElementGrp(ElementGrpEntity ElementGrp) {
        return null;
    }
}
