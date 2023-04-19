package com.webshop.Webshop.Backend.dtos;

import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;



@Builder
@Getter
@Setter
public class ProductDto {
   private int productId;
   private String title;
   private String description;
   private double price;
   private String imageUrl;


}
