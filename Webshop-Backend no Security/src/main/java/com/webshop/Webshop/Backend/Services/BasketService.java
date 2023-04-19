package com.webshop.Webshop.Backend.Services;

import com.webshop.Webshop.Backend.Entities.BasketEntity;
import com.webshop.Webshop.Backend.Entities.BasketProductsEntity;
import com.webshop.Webshop.Backend.Repositories.*;
import com.webshop.Webshop.Backend.dtos.BasketDto;
import com.webshop.Webshop.Backend.dtos.BasketItemDto;
import com.webshop.Webshop.Backend.dtos.GetBasketsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    BasketProductsCrudRepository basketProductsCrudRepository;
    @Autowired
    ProductCrudRepository productCrudRepository;
    @Autowired
    AddressCrudRepository addressCrudRepository;
    @Autowired
    UserCrudRepository userCrudRepository;
    @Autowired
    BasketCrudRepository basketCrudRepository;
    @Autowired
    AddressService addressService;

    public GetBasketsDto getAllBaskets(){
        List <BasketDto> basketDtoList = new ArrayList<>();
        for (BasketEntity basketEntity:basketCrudRepository.findAll()){

            List <BasketItemDto> basketItemDtoList= new ArrayList<>();

            for(BasketProductsEntity basketProductsEntity : basketProductsCrudRepository.findByBasketEntity(basketEntity)){
                basketItemDtoList.add(BasketItemDto.builder()
                                .productId(basketProductsEntity.productEntity().productId())
                                .amount(basketProductsEntity.amount())
                                .remark(basketProductsEntity.remark())
                        .build());
            }

            basketDtoList.add( BasketDto.builder()
                            .userId(basketEntity.userEntity().userId())
                            .basketId(basketEntity.basketId())
                            .total(basketEntity.totalPrice())
                            .deliveryAddress(addressService.convertAddressEntityToAddressDto(basketEntity.deliveryAddressEntity()))
                            .invoiceAddress(addressService.convertAddressEntityToAddressDto(basketEntity.invoiceAddressEntity()))
                            .shippingType(basketEntity.shippingType())
                            .items(basketItemDtoList)
                    .build());
        };
        return GetBasketsDto.builder()
                .baskets(basketDtoList)
                .build();
    }
    public void postBasket(BasketDto basketDto){
        BasketEntity basketEntity = new BasketEntity()
                .userEntity(userCrudRepository.findById(basketDto.getUserId()).get())
                .totalPrice(basketDto.getTotal())
                .invoiceAddressEntity(addressCrudRepository.findById(basketDto.getInvoiceAddress().getAddressId()).get())
                .deliveryAddressEntity(addressCrudRepository.findById(basketDto.getDeliveryAddress().getAddressId()).get())
                .shippingType(basketDto.getShippingType());

        basketCrudRepository.save(basketEntity);

        for (BasketItemDto basketItemDto : basketDto.getItems())
        basketProductsCrudRepository.save(
                new BasketProductsEntity()
                        .basketEntity(basketEntity)
                        .productEntity(productCrudRepository.findById(basketItemDto.getProductId()).get())
                        .remark(basketItemDto.getRemark())
                        .amount(basketItemDto.getAmount())
        );
    }
}
