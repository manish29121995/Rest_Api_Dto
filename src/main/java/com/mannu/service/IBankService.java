package com.mannu.service;

import java.util.List;

import com.mannu.dto.BankDto;

public interface IBankService {

	public BankDto addCustomer(BankDto bankDto);
	
	public BankDto getDataByAccountNumber(Integer accNum);
	
	public String DebitBalance(Integer accNum, Double amount);

	public String creditbalance(Integer accNum, Double amount);
	
	public String deleteCustomerAccount(Integer accNum);
	
	public List<BankDto>  getAllCustomer();
	
	public BankDto updateCustomer(Integer accNum , BankDto bankDto);
}
