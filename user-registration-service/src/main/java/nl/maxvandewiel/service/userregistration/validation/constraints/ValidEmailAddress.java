package nl.maxvandewiel.service.userregistration.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by max on 6/4/16.
 */

/* Linking the EmailAddressValidator. */
@Constraint(validatedBy = {nl.maxvandewiel.service.userregistration.validation.ValidEmailAddressValidator.class})
//This constraint annotation can be used only on fields and method parameters.
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ValidEmailAddress {
    //The message to return when the instance of MyAddress fails the validation.
    String message() default "Invalid email address";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
