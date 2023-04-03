package com.webshop.Webshop.Backend.dtos;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class UserDto {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}
