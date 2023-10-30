package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.RolesDto;
import edu.educate.model.RolesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rolesService")
public class RolesServiceImp implements RolesService {
    @Override
    public List<RolesDto> getRoles() {
        return null;
    }

    @Override
    public RolesDto getRole(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getRolePersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteRole(Integer id) {
        return null;
    }

    @Override
    public RolesDto createRole(RolesEntity Role) {
        return null;
    }
}
