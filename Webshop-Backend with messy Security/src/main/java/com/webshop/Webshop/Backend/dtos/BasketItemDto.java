package com.webshop.Webshop.Backend.dtos;

import lombok.*;

import java.sql.Timestamp;
@Builder
@Getter
@Setter
public class BasketItemDto {
    private int productId;
    private int amount;
    private String remark;

}
