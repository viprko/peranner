package pet.peranner.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private short age;
    private String email;
}
