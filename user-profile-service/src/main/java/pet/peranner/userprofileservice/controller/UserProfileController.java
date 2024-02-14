package pet.peranner.userprofileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.userprofileservice.dto.request.UserProfileRequestDto;
import pet.peranner.userprofileservice.dto.response.UserProfileResponseDto;
import pet.peranner.userprofileservice.service.UserProfileService;
import pet.peranner.userprofileservice.service.mapper.UserMapper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserProfileResponseDto save(
            @Valid @RequestBody UserProfileRequestDto profileRequestDto) {
        return userMapper.toDto(userProfileService.save(userMapper.toEntity(profileRequestDto)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserProfileResponseDto getById(@PathVariable("id") Long id) {
        return userMapper.toDto(userProfileService.findById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserProfileResponseDto updateInfo(@PathVariable("id") Long id,
                                             @Valid @RequestBody
                                             UserProfileRequestDto profileRequestDto) {
        return userMapper.toDto(
                userProfileService.updateInfo(userMapper.toEntity(profileRequestDto)));
    }
}
