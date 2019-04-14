package com.zdw.zms.annotion;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidClass.class)
@Target(value = {ElementType.PARAMETER,ElementType.FIELD})
public @interface NameValid {

    String values();

    String message() default "名字不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
