package pet.peranner.authenticationservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.authenticationservice.dto.request.PasswordUpdateDto;
import pet.peranner.authenticationservice.dto.request.SecurityUserLoginDto;
import pet.peranner.authenticationservice.dto.request.SecurityUserRegistrationDto;
import pet.peranner.authenticationservice.dto.request.TelegramUserBindingDto;
import pet.peranner.authenticationservice.dto.response.SecurityUserResponseDto;
import pet.peranner.authenticationservice.exception.AuthenticationException;
import pet.peranner.authenticationservice.exception.InvalidJwtAuthenticationException;
import pet.peranner.authenticationservice.exception.UserNotFoundException;
import pet.peranner.authenticationservice.model.SecurityUser;
import pet.peranner.authenticationservice.security.AuthenticationService;
import pet.peranner.authenticationservice.security.jwt.JwtTokenProvider;
import pet.peranner.authenticationservice.service.SecurityUserService;
import pet.peranner.authenticationservice.service.mapper.SecurityUserMapper;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final SecurityUserMapper securityUserMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUserService securityUserService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityUserResponseDto register(
            @RequestBody @Valid SecurityUserRegistrationDto userRegistrationDto) {
        SecurityUser registeredUser = authenticationService.register(userRegistrationDto);
        return securityUserMapper.toDto(registeredUser);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> login(
            @RequestBody @Valid SecurityUserLoginDto securityUserLoginDto) throws
            AuthenticationException {
        SecurityUser securityUser = authenticationService.login(securityUserLoginDto.getEmail(),
                securityUserLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(securityUser.getEmail(), securityUser.getId());
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }

    @PostMapping("/password/change")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean updatePassword(
            HttpServletRequest request,
            @RequestBody PasswordUpdateDto passwordUpdateDto) {
        String token = jwtTokenProvider.resolveToken(request);
        Long userId = jwtTokenProvider.getUserId(token);
        return authenticationService.updatePassword(userId, passwordUpdateDto.getOldPassword(),
                passwordUpdateDto.getPassword());
    }

    @GetMapping("/token/verify")
    public ResponseEntity<Long> verifyToken(@RequestHeader("Authorization") String token) {
        try {
            if (jwtTokenProvider.validateToken(token)) {
                return ResponseEntity.ok(jwtTokenProvider.getUserId(token));
            }
        } catch (InvalidJwtAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/telegram/verify")
    public ResponseEntity<Long> verifyTelegramUser(
            @RequestHeader("X-Telegram-UserId") String telegramUserId) {
        log.debug("Received request for verify telegram user with header: {}", telegramUserId);
        try {
            Long userIdByTelegramId =
                    securityUserService.findUserIdByTelegramId(telegramUserId);
            log.debug("Try to found user by telegram id. The result user id = {}",
                    userIdByTelegramId);
            return ResponseEntity.ok(userIdByTelegramId);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("telegram/bind")
    public ResponseEntity<SecurityUserResponseDto> bindTelegramIdToUser(
            @RequestBody TelegramUserBindingDto telegramUserBindingDto) {
        SecurityUser securityUser = authenticationService.login(telegramUserBindingDto.getEmail(),
                telegramUserBindingDto.getPassword());
        securityUserService.updateTelegramId(telegramUserBindingDto.getTelegramId(), securityUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "authentication-service response to your request";
    }
}
