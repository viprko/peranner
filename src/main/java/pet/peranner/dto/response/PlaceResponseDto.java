package pet.peranner.dto.response;

import lombok.Data;

@Data
public class PlaceResponseDto {
    private String id;
    private Double latitude;
    private Double longitude;
    private String title;
}
