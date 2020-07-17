package com.samir.customerrewardprogram.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.samir.customerrewardprogram.dto.CustomerDto;
import com.samir.customerrewardprogram.entity.Customer;
import com.samir.customerrewardprogram.entity.Transaction;
import com.samir.customerrewardprogram.exception.NotFoundException;
import com.samir.customerrewardprogram.helper.ModelMapHelper;
import com.samir.customerrewardprogram.respository.CustomerRepository;
import com.samir.customerrewardprogram.respository.TransactionRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final TransactionRepository transactionRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository,TransactionRepository transactionRepository ) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}
		
	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer customer = ModelMapHelper.customerDtoToCustomer(customerDto);
		return ModelMapHelper.customerToCustomerDto(customerRepository.save(customer));
	}

	@Override
	public Iterable<CustomerDto> getCustomer() {
		Iterable<Customer> customerList = customerRepository.findAll();
		return ModelMapHelper.customerIterableToCustomerDtoIterable(customerList);
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		return ModelMapHelper.customerToCustomerDto(optionalCustomer.orElseThrow(NotFoundException::new));
	}

	@Override
	public Iterable<Transaction> getTransactionsByCustomerId(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
		return customer.getTransactions();
	}

	@Override
	public Double getTotalRewardPointsByCustomerId(Long id) {
		List<Transaction> transactionList = customerRepository.findById(id)
															  .orElseThrow(NotFoundException::new)
															  .getTransactions();
		Double totalRewardPoints = transactionList.stream()
									.mapToDouble(Transaction :: getRewardPoints)
									.sum();
		
		return totalRewardPoints;	
	}

	@Override
	public Map<Integer, Double> getRewardPointsPerMonthByCustomerId(Long id) {
		Map<Integer,Double> results = new HashMap<Integer,Double>();
		List<Object[]> list = transactionRepository.findRewardPointsPerMonth(id);
		for (Object[] object :list) {
			results.put((Integer) object[0], (Double) object[1]);
		}
		return results;
	}

}
