package com.samir.customerrewardprogram.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {

	private Long id;
	private Double price;
	private Double rewardPoints;
    private Long customerId ;
}
