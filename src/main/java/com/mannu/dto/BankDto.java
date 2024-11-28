package com.mannu.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BankDto {
	private Integer accNum;
	private String ifscCode;
	private String bankName;
	private String customerId;
	private String customerName;
	private String accType;
	private String gender;
	private Double balance;
	private LocalDate acc_Creation;
}
