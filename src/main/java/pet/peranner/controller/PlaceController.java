package pet.peranner.controller;

import java.util.List;
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
import pet.peranner.dto.request.PlaceRequestDto;
import pet.peranner.dto.response.PlaceResponseDto;
import pet.peranner.model.Place;
import pet.peranner.model.User;
import pet.peranner.service.PlaceService;
import pet.peranner.service.mapper.PlaceMapper;

@RestController
@RequestMapping("/places")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @PostMapping
    @CurrentUser
    public ResponseEntity<PlaceResponseDto> save(@RequestBody PlaceRequestDto requestDto,
                                                 User currentUser) {
        Place place = placeMapper.toEntity(requestDto);
        place.setUser(currentUser);
        return ResponseEntity.ok(placeMapper.toDto(placeService.save(place)));
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<PlaceResponseDto>> findByTitle(@PathVariable("title") String title) {
        List<PlaceResponseDto> places = placeService.findByTitle(title).stream()
                .map(placeMapper::toDto)
                .toList();
        return ResponseEntity.ok(places);
    }

    @GetMapping
    @CurrentUser
    public ResponseEntity<List<PlaceResponseDto>> findAllByUser(User currentUser) {
        List<PlaceResponseDto> places = placeService.findAllByUser(currentUser).stream()
                .map(placeMapper::toDto)
                .toList();
        return ResponseEntity.ok(places);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlaceResponseDto> update(@PathVariable("id") Long id,
                                                   @RequestBody PlaceRequestDto requestDto) {
        return ResponseEntity.ok(
                placeMapper.toDto(placeService.update(id, placeMapper.toEntity(requestDto))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        placeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
