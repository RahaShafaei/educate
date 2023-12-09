package edu.educate.service;

import edu.educate.model.ElementGrpEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class ElementGrpServiceTest {
    @Autowired
    private ElementGrpService elementGrpService;
//    @Value(value = "${titleEntity.title.min}")
//    private String welcomeMessage;

    @Test
    public void testSaveDeleteElementGrp() {

        ElementGrpEntity elementGrp = new ElementGrpEntity();
        elementGrp.setLtTitle("Sample elementGrp : LtTitle");
        elementGrp.setPrTitle("Sample elementGrp : PrTitle");

        ElementGrpEntity savedElementGrp = elementGrpService.createEntity(elementGrp);

        assertNotNull(savedElementGrp.getId());

        ElementGrpEntity retrievedElementGrp = elementGrpService.getEntity(savedElementGrp.getId());

        assertNotNull(retrievedElementGrp);
        assertEquals(savedElementGrp.getLtTitle(), retrievedElementGrp.getLtTitle());

        Boolean elementGrpIsDeleted = elementGrpService.deleteEntity(savedElementGrp.getId());

        assertEquals(true, elementGrpIsDeleted);
    }
}
