package backend.Kwote.controllers;

import backend.Kwote.dtos.EditUserDto;
import backend.Kwote.dtos.LoginDto;
import backend.Kwote.dtos.UserDto;
import backend.Kwote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
@EnableMethodSecurity
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

    @PutMapping("{userId}")
    ResponseEntity <?>  editUser (@RequestBody EditUserDto editUser, @PathVariable int userId){
       return  ResponseEntity.ok(userService.editUser(userId, editUser));
    }

}
