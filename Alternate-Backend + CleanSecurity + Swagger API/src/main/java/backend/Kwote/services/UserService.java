package backend.Kwote.services;


import backend.Kwote.dtos.*;
import backend.Kwote.entities.UserEntity;
import backend.Kwote.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserService {
    @Autowired
    UserCrudRepository userCrudRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDto editUser(int userId, EditUserDto editUser) {
        UserEntity userEntity = userCrudRepository.findById(userId).get();
        UserEntity editedUserEntity = editUserEntity(editUser, userEntity);
        userCrudRepository.save(editedUserEntity);
        return convertUserEntityToUserDto(editedUserEntity);
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

    private UserEntity editUserEntity(EditUserDto editUser, UserEntity userEntity) {
        return userEntity
                .firstName(editUser.getFirstName())
                .lastName(editUser.getLastName())
                .email(editUser.getEmail())
                .password(passwordEncoder.encode(editUser.getPassword()))
                .role(editUser.getRole());
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
                .firstName(userEntity.firstName())
                .lastName(userEntity.lastName())
                .email(userEntity.email())
                .password(userEntity.password())
                .role(userEntity.role())
                .build();
    }
}
