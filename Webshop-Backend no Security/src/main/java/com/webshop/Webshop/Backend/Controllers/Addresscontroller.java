package com.webshop.Webshop.Backend.Controllers;

import com.webshop.Webshop.Backend.Services.AddressService;
import com.webshop.Webshop.Backend.dtos.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class Addresscontroller {

    @Autowired
    AddressService addressService;

    @GetMapping("all")
    List<AddressDto> getAllAddress() {
        return addressService.getAllAdress();
    }
//hallo
    @PostMapping
    List<AddressDto> postAddress(@RequestBody AddressDto addressDto) {
        addressService.postAdress(addressDto);
        return getAllAddress();
    }
}
