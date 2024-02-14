package pet.peranner.userprofileservice.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class BirthdateValidator implements ConstraintValidator<ValidBirthdate, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        return birthdate.isAfter(LocalDate.now());
    }
}
