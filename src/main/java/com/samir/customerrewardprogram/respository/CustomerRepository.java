package com.samir.customerrewardprogram.respository;

import org.springframework.data.repository.CrudRepository;
import com.samir.customerrewardprogram.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
