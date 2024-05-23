package com.procesos.parcial.repository;

import com.procesos.parcial.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
