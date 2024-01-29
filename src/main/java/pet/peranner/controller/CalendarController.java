package pet.peranner.controller;

import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.annotation.CurrentUser;
import pet.peranner.dto.request.ContributeRequestDto;
import pet.peranner.dto.response.ContributeResponseDto;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;
import pet.peranner.service.CalendarService;
import pet.peranner.service.ContributeService;
import pet.peranner.service.mapper.ContributeMapper;

@RestController
@RequestMapping("/calendar")
@AllArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final ContributeMapper contributeMapper;
    private final ContributeService contributeService;

    @GetMapping("/plans")
    @CurrentUser
    public ResponseEntity<List<ContributeResponseDto>> showPlansForPeriod(
            @RequestParam(name = "period", defaultValue = "ten-days")
            @Pattern(regexp = "^(ten-days|month|year)$",
                    message = "Invalid period") String predefinedPeriod, User user) {
        return ResponseEntity.ok(
                calendarService.findContributesForPeriod(predefinedPeriod, user).stream()
                        .map(contributeMapper::toDto)
                        .toList());
    }

    @GetMapping("/plans/period")
    @CurrentUser
    public ResponseEntity<List<ContributeResponseDto>> showPlansForCustomPeriod(
            @RequestParam LocalDate periodStart,
            @RequestParam LocalDate periodEnd,
            User currentUser) {
        return ResponseEntity.ok(
                calendarService.findContributesForCustomPeriod(periodStart, periodEnd, currentUser)
                        .stream()
                        .map(contributeMapper::toDto)
                        .toList());
    }

    @PostMapping
    @CurrentUser
    public ContributeResponseDto save(@RequestBody ContributeRequestDto requestDto, User user) {
        Contribute contribute = contributeMapper.toEntity(requestDto);
        contribute.setUser(user);
        return contributeMapper.toDto(contributeService.save(contribute));
    }

    @GetMapping("/{id}")
    public ContributeResponseDto findById(@PathVariable("id") Long id) {
        return contributeMapper.toDto(
                contributeService.findById(id).orElseThrow(() -> new NoSuchElementException(
                        "DevoteTime with id: " + id + " doesn't exist")));
    }

    @GetMapping
    @CurrentUser
    public List<ContributeResponseDto> findAllByUser(User currentUser) {
        return contributeService.findAllByUser(currentUser)
                .stream()
                .map(contributeMapper::toDto)
                .toList();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContributeResponseDto> update(@PathVariable("id") Long id,
                                                        @RequestBody
                                                        ContributeRequestDto requestDto) {
        return ResponseEntity.ok(contributeMapper.toDto(contributeService.update(id,
                contributeMapper.toEntity(requestDto))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        contributeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
