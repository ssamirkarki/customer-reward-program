package com.samir.customerrewardprogram.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.samir.customerrewardprogram.dto.TransactionDto;
import com.samir.customerrewardprogram.entity.Transaction;
import com.samir.customerrewardprogram.exception.NotFoundException;
import com.samir.customerrewardprogram.helper.ModelMapHelper;
import com.samir.customerrewardprogram.respository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	
	private TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public TransactionDto addTransaction(TransactionDto transactionDto) {
		transactionDto.setRewardPoints(calculateRewardPoints(transactionDto.getPrice()));
		Transaction customer = ModelMapHelper.transactionDtoToTransaction(transactionDto);
		return ModelMapHelper.transactionToTransactionDto(transactionRepository.save(customer));
	}

	@Override
	public Iterable<TransactionDto> getAllTransaction() {
		Iterable<Transaction> transactionList = transactionRepository.findAll();
		return ModelMapHelper.transactionIterableToTransactionDtoIterable(transactionList);
	}

	@Override
	public TransactionDto getTransactionById(Long id) {
		Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
		return ModelMapHelper.transactionToTransactionDto(optionalTransaction.orElseThrow(NotFoundException::new));
	}

	public Double calculateRewardPoints(Double price) {

		Double totalPoints = 0.00,priceOver100 = price - 100;

		if(priceOver100 > 0) {
			totalPoints += 2*priceOver100; 
		}
		
		if(price > 50) {
			totalPoints += 50.00;
		}
		
		return totalPoints;
	}
}
