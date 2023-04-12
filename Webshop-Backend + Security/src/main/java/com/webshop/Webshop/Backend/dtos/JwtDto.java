package com.webshop.Webshop.Backend.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtDto {
    String jwt;
}
