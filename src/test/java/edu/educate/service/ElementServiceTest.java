package edu.educate.service;

import edu.educate.model.ElementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class ElementServiceTest {
    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementGrpService elementGrpService;

    @Test
    public void testSaveDeleteElement() {

        ElementEntity element = new ElementEntity();

        element.setElementGrp(elementGrpService.getElementGrp(1));
        element.setTitle("Sample element : Title");

        ElementEntity savedElement = elementService.createElement(element);

        assertNotNull(savedElement.getId());

        ElementEntity retrievedElement = elementService.getElement(savedElement.getId());

        assertNotNull(retrievedElement);
        assertEquals(savedElement.getTitle(), retrievedElement.getTitle());

        Boolean elementIsDeleted = elementService.deleteElement(savedElement.getId());

        assertEquals(true, elementIsDeleted);
    }
}
