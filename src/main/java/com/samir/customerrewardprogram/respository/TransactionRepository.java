package com.samir.customerrewardprogram.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.samir.customerrewardprogram.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
	
	@Query(value = "SELECT MONTH(CREATED_DATE) AS MONTH, SUM(REWARD_POINTS) AS TOTAL " + 
			"FROM TRANSACTION " + 
			"WHERE CUSTOMER_ID=?1 " + 
			"GROUP BY MONTH(CREATED_DATE)", nativeQuery = true)
	List<Object[]> findRewardPointsPerMonth (Long id);
}
