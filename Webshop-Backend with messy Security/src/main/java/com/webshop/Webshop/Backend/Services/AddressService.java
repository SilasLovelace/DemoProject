package com.webshop.Webshop.Backend.Services;

import com.webshop.Webshop.Backend.Entities.AddressEntity;
import com.webshop.Webshop.Backend.Repositories.AddressCrudRepository;
import com.webshop.Webshop.Backend.dtos.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressCrudRepository addressCrudRepository;
    public void postAdress(AddressDto addressDto){
        AddressEntity addressEntity =new AddressEntity()
                .city(addressDto.getCity())
                .zip(addressDto.getZip())
                .country(addressDto.getCountry())
                .number(addressDto.getNumber())
                .street(addressDto.getStreet());
        addressCrudRepository.save(addressEntity);
    }
    public List <AddressDto> getAllAdress(){
        return convertAddressEntityIterableToAddressDtoList(addressCrudRepository.findAll());
    }
    public List <AddressDto> convertAddressEntityIterableToAddressDtoList(Iterable <AddressEntity> addressEntityIterable){
        List <AddressDto> addressDtoList = new ArrayList<>();
        for (AddressEntity addressEntity: addressEntityIterable){
            addressDtoList.add(convertAddressEntityToAddressDto(addressEntity));
        }
        return addressDtoList;
    }

    public AddressDto convertAddressEntityToAddressDto (AddressEntity addressEntity){
        return AddressDto.builder()
                .addressId(addressEntity.addressId())
                .city(addressEntity.street())
                .zip(addressEntity.zip())
                .country(addressEntity.country())
                .number(addressEntity.number())
                .street(addressEntity.street())
                .build();
    }
}
