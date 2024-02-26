package pet.peranner.contributeservice.lib;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PeriodValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPeriod {
    String message() default "Invalid period. Check the console";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?>[] value() default {};
}
