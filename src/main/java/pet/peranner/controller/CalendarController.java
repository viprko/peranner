package pet.peranner.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.annotation.CurrentUser;
import pet.peranner.dto.request.ContributeRequestDto;
import pet.peranner.dto.request.RecurrenceRequestDto;
import pet.peranner.dto.response.ContributeResponseDto;
import pet.peranner.lib.ValidPeriod;
import pet.peranner.model.User;
import pet.peranner.service.CalendarService;
import pet.peranner.service.ContributeService;
import pet.peranner.service.mapper.ContributeMapper;
import pet.peranner.service.mapper.RecurrenceMapper;

@RestController
@RequestMapping("/calendar")
@AllArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final ContributeMapper contributeMapper;
    private final ContributeService contributeService;
    private final RecurrenceMapper recurrenceMapper;

    @GetMapping
    @CurrentUser
    @Parameter(name = "user", description = "Current authenticated user (automatically injected)",
            hidden = true)
    public List<ContributeResponseDto> showPlansForPeriod(
            @RequestParam(name = "period", defaultValue = "ten-days")
            @ValidPeriod
            String period, User user) {
        return calendarService.findContributesForPeriod(period, user).stream()
                .map(contributeMapper::toDto)
                .toList();
    }

    @GetMapping("/period")
    @CurrentUser
    @Parameter(name = "user", description = "Current authenticated user (automatically injected)",
            hidden = true)
    public List<ContributeResponseDto> showPlansForCustomPeriod(
            @RequestParam LocalDate periodStart,
            @RequestParam LocalDate periodEnd,
            User user) {
        return calendarService.findContributesForCustomPeriod(periodStart, periodEnd, user)
                .stream()
                .map(contributeMapper::toDto)
                .toList();
    }

    @PostMapping("/contributes")
    @CurrentUser
    @ResponseStatus(HttpStatus.CREATED)
    @Parameter(name = "user", description = "Current authenticated user (automatically injected)",
            hidden = true)
    public ContributeResponseDto save(
            @RequestBody ContributeRequestDto contributeRequestDto,
            User user) {
        return contributeMapper.toDto(
                contributeService.save(contributeMapper.toEntity(contributeRequestDto),
                        user));
    }

    @GetMapping("/contributes/{id}")
    public ContributeResponseDto findById(@PathVariable("id") Long id) {
        return contributeMapper.toDto(
                contributeService.findById(id).orElseThrow(() -> new NoSuchElementException(
                        "DevoteTime with id: " + id + " doesn't exist")));
    }

    @GetMapping("/contributes")
    @CurrentUser
    @Parameter(name = "user", description = "Current authenticated user (automatically injected)",
            hidden = true)
    public List<ContributeResponseDto> findAllByUser(User user) {
        return contributeService.findAllByUser(user)
                .stream()
                .map(contributeMapper::toDto)
                .toList();
    }

    @PatchMapping("contributes/{id}")
    public ContributeResponseDto update(@PathVariable("id") Long id,
                                        @RequestBody
                                        ContributeRequestDto requestDto) {
        return contributeMapper.toDto(contributeService.update(id,
                contributeMapper.toEntity(requestDto)));
    }

    @DeleteMapping("/contributes/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        contributeService.deleteById(id);
    }

    @PostMapping("/contributes/recurrence")
    @CurrentUser
    public ContributeResponseDto saveWithRecurrence(
            @RequestBody RecurrenceRequestDto recurrenceRequestDto,
            @RequestBody ContributeRequestDto contributeRequestDto,
            User user) {
        return contributeMapper.toDto(contributeService.saveWithRecurrence(
                recurrenceMapper.toEntity(recurrenceRequestDto),
                        contributeMapper.toEntity(contributeRequestDto),
                        user));
    }
}
