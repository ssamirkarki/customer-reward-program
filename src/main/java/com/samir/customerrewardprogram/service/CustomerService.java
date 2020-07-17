package com.samir.customerrewardprogram.service;

import java.util.Map;
import com.samir.customerrewardprogram.dto.CustomerDto;
import com.samir.customerrewardprogram.entity.Transaction;

public interface CustomerService {

	CustomerDto addCustomer(CustomerDto customerDto);

	Iterable<CustomerDto> getCustomer();

	CustomerDto getCustomerById(Long id);

	Iterable<Transaction> getTransactionsByCustomerId(Long id);

	Double getTotalRewardPointsByCustomerId(Long id);

	Map<Integer, Double> getRewardPointsPerMonthByCustomerId(Long id);

}
