package com.webshop.Webshop.Backend.Repositories;

import com.webshop.Webshop.Backend.Entities.ProductEntity;
import com.webshop.Webshop.Backend.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCrudRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
}
