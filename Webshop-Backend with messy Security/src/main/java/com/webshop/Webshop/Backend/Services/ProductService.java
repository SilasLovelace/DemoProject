package com.webshop.Webshop.Backend.Services;

import com.webshop.Webshop.Backend.Entities.ProductEntity;
import com.webshop.Webshop.Backend.Entities.UserEntity;
import com.webshop.Webshop.Backend.Repositories.ProductCrudRepository;
import com.webshop.Webshop.Backend.dtos.ProductDto;
import com.webshop.Webshop.Backend.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductCrudRepository productCrudRepository;

    public void postProduct(ProductDto newProduct){
        productCrudRepository.save(new ProductEntity()
                .title(newProduct.getTitle())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .imageUrl(newProduct.getImageUrl()));
    }

    public List <ProductDto> getAllProducts(){
        return convertProductEntityListToDtoList(productCrudRepository.findAll());
    }

//    public ProductEntity convertProductDtoToProductEntity (ProductDto productDto){
//        return  new ProductEntity()
//                .title(productDto.title())
//                .price(productDto.price())
//                .description(productDto.description())
//                .imageUrl(productDto.imageUrl());
//    }

    public List <ProductDto> convertProductEntityListToDtoList(Iterable <ProductEntity> productEntityIterable){
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductEntity productEntity :productEntityIterable){
            productDtoList.add(convertProductEntityToProductDto(productEntity));
        }
        return productDtoList;
    }

    public ProductDto convertProductEntityToProductDto (ProductEntity productEntity){
     return  ProductDto.builder()
             .productId(productEntity.productId())
             .title(productEntity.title())
             .description(productEntity.description())
             .price(productEntity.price())
             .imageUrl(productEntity.imageUrl())
             .build();
    }
}


