package com.webshop.Webshop.Backend.dtos;

import com.webshop.Webshop.Backend.enums.ShippingEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetBasketsDto {
    List<BasketDto> baskets;
}
