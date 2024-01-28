package com.Bank.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitCreditRequest {

	private String accountNumber;
	private BigDecimal amount;
}
