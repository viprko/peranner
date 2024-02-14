package pet.peranner.userprofileservice.lib;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthdateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthdate {
    String message() default "Invalid birthdate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
