package pet.peranner.authenticationservice.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pet.peranner.authenticationservice.dto.request.SecurityUserRegistrationDto;

@Component
public class PasswordValidator implements
        ConstraintValidator<ValidPassword, SecurityUserRegistrationDto> {
    private static final int MIN_PASSWORD_SIZE = 8;

    @Override
    public boolean isValid(SecurityUserRegistrationDto userRequestDto,
                           ConstraintValidatorContext context) {
        return userRequestDto.getPassword() != null
                && userRequestDto.getPassword().length() >= MIN_PASSWORD_SIZE
                && userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
