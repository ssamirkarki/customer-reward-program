package com.samir.customerrewardprogram.helper;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;

import com.samir.customerrewardprogram.dto.CustomerDto;
import com.samir.customerrewardprogram.dto.TransactionDto;
import com.samir.customerrewardprogram.entity.Customer;
import com.samir.customerrewardprogram.entity.Transaction;
public class ModelMapHelper {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static CustomerDto customerToCustomerDto(Customer source) {
		return modelMapper.map(source, CustomerDto.class);
	}
	
	public static Customer customerDtoToCustomer(CustomerDto source) {
		return modelMapper.map(source, Customer.class);
	}
	
	public static Iterable<CustomerDto> customerIterableToCustomerDtoIterable(Iterable<Customer> source){
		Iterable<CustomerDto> destination = new ArrayList<CustomerDto>();
		return modelMapper.map(source, destination.getClass());
	}
	
	public static TransactionDto transactionToTransactionDto(Transaction source) {
		return modelMapper.map(source, TransactionDto.class);
	}
	
	public static Transaction transactionDtoToTransaction(TransactionDto source) {
		return modelMapper.map(source, Transaction.class);
	}
	
	public static Iterable<TransactionDto> transactionIterableToTransactionDtoIterable(Iterable<Transaction> source){
		Iterable<TransactionDto> destination = new ArrayList<TransactionDto>();
		return modelMapper.map(source, destination.getClass());
	}
}
