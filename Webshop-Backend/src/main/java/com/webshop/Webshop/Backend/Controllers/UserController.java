package com.webshop.Webshop.Backend.Controllers;

import com.webshop.Webshop.Backend.Services.UserService;
import com.webshop.Webshop.Backend.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping("{userId}")
    List<UserDto>  registerUser (@PathVariable int userId){
        userService.deleteUser(userId);
        return getAllUsers();
    }

    @GetMapping("{userId}")
    UserDto getUser (@PathVariable int userId){
        return userService.getUser(userId);
    }

    @GetMapping("/all")
    List<UserDto> getAllUsers (){
        return userService.getAllUsers();
    }

    @PostMapping("register")
    List<UserDto>  registerUser (@RequestBody UserDto newUser){
         userService.createUser(newUser);
         return getAllUsers();
    }
    @PutMapping("{userId}")
    List<UserDto>  editUser (@RequestBody UserDto editUser, @PathVariable int userId){
        userService.editUser(userId, editUser);
        return getAllUsers();
    }



}
