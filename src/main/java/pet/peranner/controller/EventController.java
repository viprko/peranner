package pet.peranner.controller;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.annotation.CurrentUser;
import pet.peranner.dto.request.EventRequestDto;
import pet.peranner.dto.response.EventResponseDto;
import pet.peranner.model.Event;
import pet.peranner.model.User;
import pet.peranner.service.EventService;
import pet.peranner.service.mapper.EventMapper;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    @CurrentUser
    public ResponseEntity<EventResponseDto> save(@RequestBody EventRequestDto requestDto,
                                                 User currentUser) {
        Event event = eventMapper.toEntity(requestDto);
        event.setUser(currentUser);
        return ResponseEntity.ok(eventMapper.toDto(eventService.save(event)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(eventMapper.toDto(eventService.findById(id).orElseThrow(
                    () -> new NoSuchElementException("There is not event with id: " + id))));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/my")
    public ResponseEntity<List<EventResponseDto>> getAllByUserEmail(Authentication authentication) {
        List<EventResponseDto> events =
                eventService.findAllByUserEmail(authentication.getName()).stream()
                        .map(eventMapper::toDto)
                        .toList();
        return ResponseEntity.ok(events);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventResponseDto> update(@PathVariable("id") Long id,
                                                   @RequestBody EventRequestDto requestDto) {
        return ResponseEntity.ok(
                eventMapper.toDto(eventService.update(id, eventMapper.toEntity(requestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<EventResponseDto> setActualFinishTime(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
