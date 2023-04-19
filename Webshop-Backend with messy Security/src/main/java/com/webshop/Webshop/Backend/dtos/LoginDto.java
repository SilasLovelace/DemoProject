package com.webshop.Webshop.Backend.dtos;

import com.webshop.Webshop.Backend.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    private String email;

    private String password;

}
