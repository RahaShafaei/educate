package edu.educate.service;

import edu.educate.model.OrgPostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class OrgPostServiceTest {
    @Autowired
    private OrgPostService orgPostService;
    @Test
    public void testSaveDeleteOrgPost() {

        OrgPostEntity orgPost = new OrgPostEntity();

        orgPost.setTitle("Sample orgPost : Title");

        OrgPostEntity savedOrgPost = orgPostService.createEntity(orgPost);

        assertNotNull(savedOrgPost.getId());

        OrgPostEntity retrievedOrgPost = orgPostService.getEntity(savedOrgPost.getId());

        assertNotNull(retrievedOrgPost);
        assertEquals(savedOrgPost.getTitle(), retrievedOrgPost.getTitle());

        Boolean orgPostIsDeleted = orgPostService.deleteEntity(savedOrgPost.getId());

        assertEquals(true, orgPostIsDeleted);
    }
}
