package com.webshop.Webshop.Backend.Repositories;

import com.webshop.Webshop.Backend.Entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCrudRepository extends CrudRepository <ProductEntity, Integer> {
}
