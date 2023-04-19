package com.webshop.Webshop.Backend.dtos;

import lombok.*;

@Builder
@Getter
@Setter
public class AddressDto {

    private Integer addressId;
    private String street;
    private String number;
    private String zip;
    private String city;
    private String country;

}
