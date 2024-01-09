package pet.peranner.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.annotation.CurrentUser;
import pet.peranner.dto.response.DevoteTimeResponseDto;
import pet.peranner.model.User;
import pet.peranner.service.CalendarService;
import pet.peranner.service.mapper.DevoteTimeMapper;

@RestController
@RequestMapping("/calendar")
@AllArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final DevoteTimeMapper devoteTimeMapper;

    @GetMapping("/plans")
    @CurrentUser
    public ResponseEntity<List<DevoteTimeResponseDto>> showPlansForPeriod(
            @RequestParam String predefinedPeriod, User user) {
        return ResponseEntity.ok(
                calendarService.findDevoteTimeForPeriod(predefinedPeriod, user).stream()
                        .map(devoteTimeMapper::toDto)
                        .toList());
    }

    @GetMapping("/plans/period")
    @CurrentUser
    public ResponseEntity<List<DevoteTimeResponseDto>> showPlansForCustomPeriod(
            @RequestParam LocalDate periodStart,
            @RequestParam LocalDate periodEnd,
            User currentUser) {
        return ResponseEntity.ok(
                calendarService.findDevoteTimeForCustomPeriod(periodStart, periodEnd, currentUser)
                        .stream()
                        .map(devoteTimeMapper::toDto)
                        .toList());
    }
}
