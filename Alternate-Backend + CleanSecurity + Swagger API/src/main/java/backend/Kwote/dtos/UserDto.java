package backend.Kwote.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import backend.Kwote.enums.Role;

@Builder
@Getter
@Setter
public class UserDto {
    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
