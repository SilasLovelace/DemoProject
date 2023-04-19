package backend.Kwote.services;


import backend.Kwote.dtos.*;
import backend.Kwote.entities.UserEntity;
import backend.Kwote.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AuthService {
    @Autowired
    UserCrudRepository userCrudRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public LoginResponseDto loginUser(LoginDto loginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        var user = userCrudRepository.findByEmail(loginDto.getEmail()).orElseThrow();
       return convertUserEntityToLoginResponseDto(user);
    }

    public RegisterResponseDto createUser(RegisterDto newUser) {
        UserEntity newUserEntity = userEntityFromRegisterDto(newUser, new UserEntity());
        userCrudRepository.save(newUserEntity);
        return convertUserEntityToRegisterResponseDto(newUserEntity);
    }

  private UserEntity userEntityFromRegisterDto(RegisterDto newUser, UserEntity userEntity) {
        return userEntity
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(newUser.getRole());
    }

    public RegisterResponseDto convertUserEntityToRegisterResponseDto(UserEntity userEntity) {
        Map claims = new HashMap<String, Object>();
        claims.put("role", userEntity.role());
        return RegisterResponseDto.builder()
                .jwt(jwtService.generateToken(claims, userEntity))
                .userDto(UserDto.builder()
                        .userId(userEntity.userId())
                        .firstName(userEntity.firstName())
                        .lastName(userEntity.lastName())
                        .email(userEntity.email())
                        .password(userEntity.password())
                        .role(userEntity.role())
                        .build())
                .build();
    }
    public LoginResponseDto convertUserEntityToLoginResponseDto(UserEntity userEntity) {
        Map claims = new HashMap<String, Object>();
        claims.put("role", userEntity.role());
        return LoginResponseDto.builder()
                .userId(userEntity.userId())
                .jwt(jwtService.generateToken(claims,userEntity))
                .build();
    }

}
