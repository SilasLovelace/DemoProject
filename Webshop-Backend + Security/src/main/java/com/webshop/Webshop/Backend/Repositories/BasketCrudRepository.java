package com.webshop.Webshop.Backend.Repositories;

import com.webshop.Webshop.Backend.Entities.BasketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketCrudRepository extends CrudRepository <BasketEntity, Integer> {
}
