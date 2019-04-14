package com.zdw.zms.annotion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidClass implements ConstraintValidator<NameValid,Object>
{
    String  values;

    @Override
    public void initialize(NameValid constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String[] split = values.split(",");
        for (String s : split) {
            return s.equals(o);
        }

        return false;
    }
}
