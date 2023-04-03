package com.webshop.Webshop.Backend.dtos;

import com.webshop.Webshop.Backend.enums.ShippingEnum;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
@Setter
public class BasketDto {
    private Integer basketId;
    private int userId;
    private List<BasketItemDto> items;
    private double total;
    private AddressDto invoiceAddress;
    private AddressDto deliveryAddress;
    private ShippingEnum shippingType;
}
