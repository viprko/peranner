package pet.peranner.dto.request;

import lombok.Data;

@Data
public class PlaceRequestDto {
    private Double latitude;
    private Double longitude;
    private String title;
}
