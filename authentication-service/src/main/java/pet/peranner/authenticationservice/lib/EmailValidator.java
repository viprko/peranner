package pet.peranner.authenticationservice.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_VALIDATION_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        return field != null && field.matches(EMAIL_VALIDATION_REGEX);
    }
}
