package com.webshop.Webshop.Backend.Repositories;

import com.webshop.Webshop.Backend.Entities.TokenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenCrudRepository extends CrudRepository <TokenEntity, Integer> {

    @Query (value = "Select * from token_entity t where user_Id = :id and (expired = false or revoked = false)" , nativeQuery = true)
    List<TokenEntity> findAllValidTokenByUser(Integer id);
    Optional<TokenEntity> findByToken(String token);
}
