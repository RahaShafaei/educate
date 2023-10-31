package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.RolesDto;
import edu.educate.model.RolesEntity;

import java.util.List;

public interface RolesService {
    public List<RolesDto> getRoles();

    public RolesDto getRole(Integer id);

    public Boolean deleteRole(Integer id);

    public RolesDto createRole(RolesEntity Role);
}
