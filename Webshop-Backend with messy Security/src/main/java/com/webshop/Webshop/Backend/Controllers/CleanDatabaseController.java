package com.webshop.Webshop.Backend.Controllers;

import com.webshop.Webshop.Backend.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/delete")
public class CleanDatabaseController {
    @Autowired
    AddressCrudRepository addressCrudRepository;
    @Autowired
    UserCrudRepository userCrudRepository;
    @Autowired
    ProductCrudRepository productCrudRepository;
    @Autowired
    BasketProductsCrudRepository basketProductsCrudRepository;

    @Autowired
    BasketCrudRepository basketCrudRepository;

    @DeleteMapping("database")
    public void cleanAll() {
        cleanProductBaskets();
        cleanBaskets();
        cleanAddresses();
        cleanArticles();
        cleanUsers();
    }

    @DeleteMapping("users")
    public void cleanUsers() {
        userCrudRepository.deleteAll();
    }

    @DeleteMapping("productbaskets")
    public void cleanProductBaskets() {
        basketProductsCrudRepository.deleteAll();
    }

    @DeleteMapping("products")
    public void cleanArticles() {
        productCrudRepository.deleteAll();
    }

    @DeleteMapping("addresses")
    public void cleanAddresses() {
        addressCrudRepository.deleteAll();
    }

    @DeleteMapping("baskets")
    public void cleanBaskets() {
        basketCrudRepository.deleteAll();
    }
}
