package com.samir.customerrewardprogram.service;

import com.samir.customerrewardprogram.dto.TransactionDto;

public interface TransactionService {

	TransactionDto addTransaction(TransactionDto transactionDto);

	Iterable<TransactionDto> getAllTransaction();

	TransactionDto getTransactionById(Long id);

}
