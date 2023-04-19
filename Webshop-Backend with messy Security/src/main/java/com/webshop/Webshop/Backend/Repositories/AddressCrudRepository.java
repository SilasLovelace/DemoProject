package com.webshop.Webshop.Backend.Repositories;

import com.webshop.Webshop.Backend.Entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCrudRepository extends CrudRepository <AddressEntity, Integer> {

}
