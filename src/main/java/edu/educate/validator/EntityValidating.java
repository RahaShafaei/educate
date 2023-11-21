package edu.educate.validator;

import edu.educate.model.baseModel.BaseEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;
@AllArgsConstructor
@Controller
public class EntityValidating {
    private Validator validator;

    public Set<ConstraintViolation<BaseEntity>> validating(BaseEntity baseEntity) {
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(baseEntity);
        if (!violations.isEmpty()) {
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println(violations);
            System.out.println("::::::::::::::::::::::::::::::::::");
//            throw new ConstraintViolationException(violations);
        }
        return violations;
    }
}
