package com.webshop.Webshop.Backend.Services;

import com.webshop.Webshop.Backend.Entities.TokenEntity;
import com.webshop.Webshop.Backend.Entities.UserEntity;
import com.webshop.Webshop.Backend.Repositories.TokenCrudRepository;
import com.webshop.Webshop.Backend.Repositories.UserCrudRepository;
import com.webshop.Webshop.Backend.dtos.JwtDto;
import com.webshop.Webshop.Backend.dtos.LoginDto;
import com.webshop.Webshop.Backend.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserCrudRepository userCrudRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    TokenCrudRepository tokenCrudRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    public JwtDto loginUser(LoginDto loginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        var user = userCrudRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(new HashMap<>(), user);
        revokeAllUserTokens(user);
        tokenCrudRepository.save(TokenEntity.builder()
                        .token(jwt)
                        .user(user)
                .build());
        return JwtDto.builder()
                .jwt(jwt)
                .build();
    }

    public void revokeAllUserTokens(UserEntity userEntity){
        var userTokens = tokenCrudRepository.findAllValidTokenByUser(userEntity.userId());
        if (userTokens.isEmpty()){
            return;
        } else {
            userTokens.forEach(token ->{
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenCrudRepository.saveAll(userTokens);
        }
    }

    public void createUser(UserDto newUser) {
        UserEntity newUserEntity = new UserEntity().firstName(newUser.getFirstName()).lastName(newUser.getLastName()).email(newUser.getEmail()).password(passwordEncoder.encode(newUser.getPassword())).role(newUser.getRole());
        userCrudRepository.save(newUserEntity);
        var jwt = jwtService.generateToken(new HashMap<>(), newUserEntity);
        tokenCrudRepository.save(TokenEntity.builder().token(jwt).user(newUserEntity).build());
    }

//    public void createUser (UserDto newUser){
//        UserEntity newUserEntity = UserEntity.builder().email(newUser.getEmail()).role(newUser.getRole()).firstName(newUser.getFirstName()).lastName(newUser.getLastName()).password(newUser.getPassword()).build();
//        userCrudRepository.save(newUserEntity);
//    }

    //here new variant probiert
    public void editUser(int userId, UserDto newUser) {
        UserEntity userEntity = userCrudRepository.findById(userId).get();
        userEntity.firstName(newUser.getFirstName()).lastName(newUser.getLastName()).email(newUser.getEmail()).password(passwordEncoder.encode(newUser.getPassword())).role(newUser.getRole());
        userCrudRepository.save(userEntity);
    }

    public void deleteUser(int userId) {
        userCrudRepository.deleteById(userId);
    }

    public List<UserDto> getAllUsers() {
        return convertUserEntityListToDtoList(userCrudRepository.findAll());
    }

    public UserDto getUser(int userId) {
        return convertUserEntityToUserDto(userCrudRepository.findById(userId).get());
    }

    public List<UserDto> convertUserEntityListToDtoList(Iterable<UserEntity> userEntityIterable) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserEntity userEntity : userEntityIterable) {
            userDtoList.add(convertUserEntityToUserDto(userEntity));
        }
        return userDtoList;
    }

    public UserDto convertUserEntityToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.userId())
                .email(userEntity.email())
                .password(userEntity.password())
                .firstName(userEntity.firstName())
                .lastName(userEntity.lastName())
                .role(userEntity.role())
                .build();
    }
//    public UserDto OldconvertUserEntityToUserDto (UserEntity userEntity){
//        return UserDto.builder()
//                .userId(userEntity.userId())
//                .email(userEntity.getEmail())
//                .password(userEntity.getPassword())
//                .createdAt(userEntity.getCreatedAt())
//                .updatedAt(userEntity.getUpdatedAt())
//                .firstName(userEntity.getFirstName())
//                .lastName(userEntity.getLastName())
//                .role(userEntity.getRole())
//                .build();
//    }
}
