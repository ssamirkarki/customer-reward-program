package com.samir.customerrewardprogram.controller;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samir.customerrewardprogram.dto.TransactionDto;
import com.samir.customerrewardprogram.helper.ApiConstants;
import com.samir.customerrewardprogram.response.ApiResponse;
import com.samir.customerrewardprogram.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<TransactionDto>> addTransaction (@RequestBody TransactionDto transactionDto){
		if(IsInvalidInput(transactionDto)) {
			ApiResponse.Error clientError = new ApiResponse.Error(HttpStatus.BAD_REQUEST.value(),
															ApiConstants.CLIENT_ERROR, 
															"Price and/or CustomerIds cannot be empty.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(new ApiResponse<>(null, clientError));	
		}
		return ResponseEntity.ok(new ApiResponse<>(transactionService.addTransaction(transactionDto), null));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Iterable<TransactionDto>>> getAllTransaction(){
		return ResponseEntity.ok(new ApiResponse<>(transactionService.getAllTransaction(), null));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TransactionDto>>getTransactionById (@PathVariable("id") @NotNull Long id) {
		return ResponseEntity.ok(new ApiResponse<>(transactionService.getTransactionById(id), null));
	}
	
	
	private boolean IsInvalidInput(TransactionDto transactionDto) {
		return transactionDto.getCustomerId() == null || ApiConstants.zeroValue.equals(transactionDto.getCustomerId()) || 
			   transactionDto.getPrice() ==  null || new Double(0.0).equals(transactionDto.getPrice());
	}

}
