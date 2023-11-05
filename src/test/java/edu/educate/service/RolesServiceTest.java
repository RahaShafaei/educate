package edu.educate.service;

import edu.educate.model.RolesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class RolesServiceTest {
    @Autowired
    private RolesService rolesService;
    @Test
    public void testSaveDeleteRoles() {

        RolesEntity roles = new RolesEntity();

        roles.setTitle("Sample roles : Title");

        RolesEntity savedRole = rolesService.createEntity(roles);

        assertNotNull(savedRole.getId());

        RolesEntity retrievedRole = rolesService.getEntity(savedRole.getId());

        assertNotNull(retrievedRole);
        assertEquals(savedRole.getTitle(), retrievedRole.getTitle());

        Boolean roleIsDeleted = rolesService.deleteEntity(savedRole.getId());

        assertEquals(true, roleIsDeleted);
    }
}
