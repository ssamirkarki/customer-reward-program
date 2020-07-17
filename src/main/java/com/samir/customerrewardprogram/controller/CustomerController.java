package com.samir.customerrewardprogram.controller;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samir.customerrewardprogram.dto.CustomerDto;
import com.samir.customerrewardprogram.entity.Transaction;
import com.samir.customerrewardprogram.helper.ApiConstants;
import com.samir.customerrewardprogram.response.ApiResponse;
import com.samir.customerrewardprogram.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {this.customerService = customerService;}
	
	@PostMapping
	public ResponseEntity<ApiResponse<CustomerDto>> addCustomer(@RequestBody CustomerDto customerDto){
		if(IsInvalidInput(customerDto)) {
			ApiResponse.Error clientError = new ApiResponse.Error(HttpStatus.BAD_REQUEST.value(),
												ApiConstants.CLIENT_ERROR, 
												"FirstName and/or LastName cannot be empty.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(new ApiResponse<>(null, clientError));				
		}
		return ResponseEntity.ok(new ApiResponse<>(customerService.addCustomer(customerDto), null));
		
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Iterable<CustomerDto>>> getCustomer(){
		return ResponseEntity.ok(new ApiResponse<>(customerService.getCustomer(), null));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerDto>> getCustomerById(@PathVariable("id") @NotNull Long id){
		return ResponseEntity.ok(new ApiResponse<>(customerService.getCustomerById(id), null));
	}
	
	@GetMapping("/{id}/transactions")
	public Iterable<Transaction> getTransactionsByCustomerId(@PathVariable("id") @NotNull Long id){
		return customerService.getTransactionsByCustomerId(id);
	}
	
	
	@GetMapping("/{id}/transactions/totalrewardpts")
	public Double getTotalRewardPointsByCustomerId(@PathVariable("id") @NotNull Long id){
		return customerService.getTotalRewardPointsByCustomerId(id);
	}
	
	
	@GetMapping("/{id}/transactions/rewardptspermonth")
	public Map<Integer,Double> getRewardPointsPerMonthByCustomerId(@PathVariable("id") @NotNull Long id){
		return customerService.getRewardPointsPerMonthByCustomerId(id);
	}
	
	private boolean IsInvalidInput(CustomerDto customerDto) {
		return customerDto.getFirstName() == null || customerDto.getFirstName().trim().isEmpty() || 
			   customerDto.getLastName() ==  null || customerDto.getLastName().trim().isEmpty();
	}
}
