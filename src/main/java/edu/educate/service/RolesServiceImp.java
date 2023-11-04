package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.model.RolesEntity;
import edu.educate.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("rolesService")
public class RolesServiceImp implements RolesService {

    private static final String ROLES_ID = "Roles id: ";
    private final RolesRepository rolesRepository;
    @Override
    public List<RolesEntity> getRoles() {
        return rolesRepository.findByDeletedFalse();
    }

    @Override
    public RolesEntity getRole(Integer id) {
        Optional<RolesEntity> roles = rolesRepository.findByIdAndDeletedFalse(id);

        if (roles.isEmpty())
            throw new ItemNotFoundException(ROLES_ID + id);

        return roles.get();
    }

    @Override
    public Boolean deleteRole(Integer id) {
        Optional<RolesEntity> roles = rolesRepository.findByIdAndDeletedFalse(id);

        if (!roles.isEmpty()) {
            roles.get().setDeleted(true);
            roles.get().setDeletedAt(LocalDateTime.now());
            rolesRepository.save(roles.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RolesEntity createRole(RolesEntity roles) {

        RolesEntity savedRoles = rolesRepository.save(roles);

        return savedRoles;
    }
}
