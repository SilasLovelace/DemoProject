package com.webshop.Webshop.Backend.Controllers;

import com.webshop.Webshop.Backend.Services.BasketService;
import com.webshop.Webshop.Backend.dtos.BasketDto;
import com.webshop.Webshop.Backend.dtos.GetBasketsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/basket")
@RestController
public class Basketcontroller {
    @Autowired
    BasketService basketService;

    @PostMapping
    void postBasket(@RequestBody BasketDto basketDto) {
        basketService.postBasket(basketDto);
    }

    @GetMapping("all")
    GetBasketsDto getAllBaskets() {
        return basketService.getAllBaskets();
    }
}
