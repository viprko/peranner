package pet.peranner.contributeservice.controller;

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
import pet.peranner.contributeservice.annotation.CurrentUser;
import pet.peranner.contributeservice.dto.request.ContributeRequestDto;
import pet.peranner.contributeservice.dto.request.RecurrenceRequestDto;
import pet.peranner.contributeservice.dto.response.ContributeResponseDto;
import pet.peranner.contributeservice.lib.ValidPeriod;
import pet.peranner.contributeservice.service.ContributeService;
import pet.peranner.contributeservice.service.mapper.ContributeMapper;
import pet.peranner.contributeservice.service.mapper.RecurrenceMapper;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ContributeController {
    private final ContributeService contributeService;
    private final ContributeMapper contributeMapper;
    private final RecurrenceMapper recurrenceMapper;

    @GetMapping
    public List<ContributeResponseDto> getScheduleForPeriod(
            @RequestParam(name = "period", defaultValue = "today")
            @ValidPeriod String period,
            @CurrentUser Long userId) {
        return contributeService.findContributesForPeriod(period, userId).stream()
                .map(contributeMapper::toDto)
                .toList();
    }

    @GetMapping("/range")
    public List<ContributeResponseDto> getScheduleForPeriod(
            @RequestParam(name = "startDate") LocalDate periodStart,
            @RequestParam(name = "endDate") LocalDate periodEnd,
            @CurrentUser Long userId) {
        return contributeService.findContributesForPeriod(periodStart, periodEnd, userId).stream()
                .map(contributeMapper::toDto)
                .toList();
    }

    @PostMapping("/contributes")
    @ResponseStatus(HttpStatus.CREATED)
    public ContributeResponseDto save(
            @RequestBody ContributeRequestDto contributeRequestDto,
            @CurrentUser Long userId) {
        return contributeMapper.toDto(
                contributeService.save(contributeMapper.toEntity(contributeRequestDto),
                        userId));
    }

    @GetMapping("/contributes/{id}")
    public ContributeResponseDto findById(@PathVariable("id") Long id) {
        return contributeMapper.toDto(
                contributeService.findById(id).orElseThrow(() -> new NoSuchElementException(
                        "DevoteTime with id: " + id + " doesn't exist")));
    }

    @PatchMapping("contributes/{id}")
    public ContributeResponseDto update(@PathVariable("id") Long id,
                                        @RequestBody
                                        ContributeRequestDto requestDto) {
        return contributeMapper.toDto(contributeService.update(
                contributeMapper.toEntity(requestDto), id));
    }

    @DeleteMapping("/contributes/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        contributeService.deleteById(id);
    }

    @PostMapping("/contributes/recurrence")
    public ContributeResponseDto saveWithRecurrence(
            @RequestBody RecurrenceRequestDto recurrenceRequestDto,
            @RequestBody ContributeRequestDto contributeRequestDto,
            @CurrentUser Long userId) {
        return contributeMapper.toDto(contributeService.saveWithRecurrence(
                recurrenceMapper.toEntity(recurrenceRequestDto),
                contributeMapper.toEntity(contributeRequestDto),
                userId));
    }
}
