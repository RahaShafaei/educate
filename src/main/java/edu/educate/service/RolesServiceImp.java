package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.RolesEntity;
import edu.educate.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("rolesService")
public class RolesServiceImp implements RolesService {

    private static final String ROLES_ID = "Roles id: ";
    private final RolesRepository rolesRepository;
    @Override
    public List<RolesEntity> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public RolesEntity getRole(Integer id) {
        Optional<RolesEntity> roles = rolesRepository.findById(id);

        if (roles.isEmpty())
            throw new ItemNotFoundException(ROLES_ID + id);

        return roles.get();
    }

    @Override
    public Boolean deleteRole(Integer id) {
        Optional<RolesEntity> roles = rolesRepository.findById(id);

        if (!roles.isEmpty()) {
            rolesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RolesEntity createRole(RolesEntity roles) {
        if (roles.getTitle() == null || roles.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of Roles should not be empty.");

        RolesEntity savedRoles = rolesRepository.save(roles);

        return savedRoles;
    }
}
