package edu.educate.service;

import edu.educate.model.RolesEntity;

import java.util.List;

public interface RolesService {
    public List<RolesEntity> getRoles();

    public RolesEntity getRole(Integer id);

    public Boolean deleteRole(Integer id);

    public RolesEntity createRole(RolesEntity Role);
}
