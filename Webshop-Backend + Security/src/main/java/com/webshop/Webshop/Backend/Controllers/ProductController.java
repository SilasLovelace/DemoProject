package com.webshop.Webshop.Backend.Controllers;

import com.webshop.Webshop.Backend.Services.ProductService;
import com.webshop.Webshop.Backend.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    List<ProductDto> postProduct (@RequestBody ProductDto newProduct){
        productService.postProduct(newProduct);
        return getAllProducts();
    }

    @GetMapping("all")
    List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
}
