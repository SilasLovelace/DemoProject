package com.webshop.Webshop.Backend.Repositories;

import com.webshop.Webshop.Backend.Entities.BasketEntity;
import com.webshop.Webshop.Backend.Entities.BasketProductsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketProductsCrudRepository extends CrudRepository<BasketProductsEntity, Integer> {
     Iterable<BasketProductsEntity> findByBasketEntity(BasketEntity basketEntity);
}
