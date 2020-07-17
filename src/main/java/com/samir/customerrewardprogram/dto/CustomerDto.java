package com.samir.customerrewardprogram.dto;

import java.util.List;

import com.samir.customerrewardprogram.entity.Transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private List<Transaction> transactions;
}
