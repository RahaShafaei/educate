package edu.educate.service;

import edu.educate.model.ElementGrpEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class ElementGrpServiceTest {
    @Autowired
    private ElementGrpService elementGrpService;

    @Test
    public void testSaveDeleteElementGrp() {

        ElementGrpEntity elementGrp = new ElementGrpEntity();

//        elementGrp.setTitle("Sample elementGrp : Title");

        ElementGrpEntity savedElementGrp = elementGrpService.createElementGrp(elementGrp);

        assertNotNull(savedElementGrp.getId());

        ElementGrpEntity retrievedElementGrp = elementGrpService.getElementGrp(savedElementGrp.getId());

        assertNotNull(retrievedElementGrp);
        assertEquals(savedElementGrp.getTitle(), retrievedElementGrp.getTitle());

        Boolean elementGrpIsDeleted = elementGrpService.deleteElementGrp(savedElementGrp.getId());

        assertEquals(true, elementGrpIsDeleted);
    }
}
