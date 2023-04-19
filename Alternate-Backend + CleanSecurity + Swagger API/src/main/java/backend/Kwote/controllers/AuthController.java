package backend.Kwote.controllers;

import backend.Kwote.dtos.LoginDto;
import backend.Kwote.dtos.RegisterDto;
import backend.Kwote.dtos.UserDto;
import backend.Kwote.services.AuthService;
import backend.Kwote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/")
@EnableMethodSecurity
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("register")
    ResponseEntity <?> registerUser (@RequestBody RegisterDto newUser){
         return ResponseEntity.ok(authService.createUser(newUser));
    }
    @PostMapping("login")
    ResponseEntity <?> registerUser (@RequestBody LoginDto loginDto){
         return ResponseEntity.ok(authService.loginUser(loginDto));
    }

}
