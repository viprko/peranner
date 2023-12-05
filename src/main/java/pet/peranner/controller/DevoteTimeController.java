package pet.peranner.controller;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.annotation.CurrentUser;
import pet.peranner.dto.request.DevoteTimeRequestDto;
import pet.peranner.dto.response.DevoteTimeResponseDto;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;
import pet.peranner.service.DevoteTimeService;
import pet.peranner.service.mapper.DevoteTimeMapper;

@RestController
@RequestMapping("/devote-time")
@AllArgsConstructor
public class DevoteTimeController {
    private final DevoteTimeService devoteTimeService;
    private final DevoteTimeMapper devoteTimeMapper;

    @PostMapping
    @CurrentUser
    public DevoteTimeResponseDto save(@RequestBody DevoteTimeRequestDto requestDto, User user) {
        DevoteTime devoteTime = devoteTimeMapper.toEntity(requestDto);
        devoteTime.setUser(user);
        return devoteTimeMapper.toDto(devoteTimeService.save(devoteTime));
    }

    @GetMapping("/{id")
    public DevoteTime findById(@PathVariable("id") Long id) {
        return devoteTimeService.findById(id).orElseThrow(() -> new NoSuchElementException(
                "DevoteTime with id: " + id + " doesn't exist"));
    }

    @GetMapping("/my")
    @CurrentUser
    public List<DevoteTimeResponseDto> findAllByCurrentUser(User user) {
        return devoteTimeService.findAllByUserEmail(user.getEmail())
                .stream()
                .map(devoteTimeMapper::toDto)
                .toList();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DevoteTimeResponseDto> update(@PathVariable("id") Long id,
                                                        @RequestBody
                                                        DevoteTimeRequestDto requestDto) {
        return ResponseEntity.ok(devoteTimeMapper.toDto(devoteTimeService.update(id,
                devoteTimeMapper.toEntity(requestDto))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        devoteTimeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
