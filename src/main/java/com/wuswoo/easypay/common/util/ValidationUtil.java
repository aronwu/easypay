package com.wuswoo.easypay.common.util;

import javax.validation.*;
import java.util.*;

/**
 * Created by wuxinjun on 16/9/23.
 */
public class ValidationUtil {

    public static <T> Map<String, List<String>> validator(T t,  Set<String> ignoreValidationFields) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(t);
        if (set != null && set.size() > 0){
            Map<String, List<String>> validationResults = new HashMap<String, List<String>>();
            for (ConstraintViolation<T> constraintViolation : set) {
                for(Path.Node node : constraintViolation.getPropertyPath()) {
                    String fieldName = node.getName();
                    if (ignoreValidationFields == null || !ignoreValidationFields.contains(fieldName)) {
                        List<String> fieldResult = validationResults.get(fieldName);
                        if (fieldResult == null ){
                            fieldResult = new ArrayList<String>();
                        }
                        fieldResult.add(constraintViolation.getMessage());
                        validationResults.put(fieldName, fieldResult);
                    }
                }
            }
            return validationResults;
        }
        return null;
    }
}
