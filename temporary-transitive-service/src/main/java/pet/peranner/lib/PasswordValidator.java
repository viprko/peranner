package pet.peranner.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pet.peranner.dto.request.UserRegistrationDto;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, UserRegistrationDto> {
    private static final int MIN_PASSWORD_SIZE = 8;

    @Override
    public boolean isValid(UserRegistrationDto userRequestDto, ConstraintValidatorContext context) {
        return userRequestDto.getPassword() != null
                && userRequestDto.getPassword().length() >= MIN_PASSWORD_SIZE
                && userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
