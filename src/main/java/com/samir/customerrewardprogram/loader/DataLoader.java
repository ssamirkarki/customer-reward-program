package com.samir.customerrewardprogram.loader;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.samir.customerrewardprogram.entity.Customer;
import com.samir.customerrewardprogram.entity.Transaction;
import com.samir.customerrewardprogram.respository.CustomerRepository;
import com.samir.customerrewardprogram.respository.TransactionRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Customer consumer1 = new Customer();
		consumer1.setFirstName("Tim");
		consumer1.setMiddleName("J");
		consumer1.setLastName("Sim");
		consumer1.setEmail("Tim@email.com");
		consumer1.setPhoneNumber("111-222-2121");
		consumer1.setCreatedDate(LocalDateTime.now());
		
		customerRepository.save(consumer1);
		
		Customer consumer2 = new Customer();
		consumer2.setFirstName("Sam");
		consumer2.setMiddleName("K");
		consumer2.setLastName("Kam");
		consumer2.setEmail("Sam@email.com");
		consumer2.setPhoneNumber("444-222-5151");
		
		customerRepository.save(consumer2);
		
		Transaction transaction1 = new Transaction();
		transaction1.setPrice(120.00);
		transaction1.setRewardPoints(90.00);
		transaction1.setCustomer(consumer1);
		
		transactionRepository.save(transaction1);
		
		Transaction transaction2 = new Transaction();
		transaction2.setPrice(1000.00);
		transaction2.setRewardPoints(1850.00);
		transaction2.setCustomer(consumer1);
		
		transactionRepository.save(transaction2);
		
		Transaction transaction3 = new Transaction();
		transaction3.setPrice(100.00);
		transaction3.setRewardPoints(50.00);
		transaction3.setCustomer(consumer2);
		
		transactionRepository.save(transaction3);
		
		Transaction transaction4 = new Transaction();
		transaction4.setPrice(200.00);
		transaction4.setRewardPoints(250.00);
		transaction4.setCustomer(consumer2);

		transactionRepository.save(transaction4);
		
		Transaction transaction5 = new Transaction();
		transaction5.setPrice(60.00);
		transaction5.setRewardPoints(50.00);
		//transaction5.setCreatedDate(LocalDateTime.now());
		transaction5.setCustomer(consumer2);
		
		transactionRepository.save(transaction5);
	}

}
