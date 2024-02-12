package pet.peranner.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.dto.request.UserLoginDto;
import pet.peranner.dto.request.UserRegistrationDto;
import pet.peranner.dto.response.UserResponseDto;
import pet.peranner.exception.AuthenticationException;
import pet.peranner.model.User;
import pet.peranner.security.AuthenticationService;
import pet.peranner.security.jwt.JwtTokenProvider;
import pet.peranner.service.mapper.UserMapper;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        User registeredUser = authenticationService.register(userRegistrationDto);
        return new ResponseEntity<>(userMapper.toDto(registeredUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto) throws
            AuthenticationException {
        User user = authenticationService.login(userLoginDto.getEmail(),
                userLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(user.getEmail(),
                List.of(user.getRole().name()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}