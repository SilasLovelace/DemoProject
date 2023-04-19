package backend.Kwote.dtos;

import backend.Kwote.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class RegisterResponseDto {
    private String jwt;
    private UserDto userDto;
}
