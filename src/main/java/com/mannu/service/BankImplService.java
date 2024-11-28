package com.mannu.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mannu.dto.BankDto;
import com.mannu.entity.BankEntity;
import com.mannu.repo.BankRepository;

@Service
public final class BankImplService implements IBankService {

	@Autowired
	private BankRepository repository;

	@Override
	public BankDto addCustomer(BankDto bankDto) {
		ModelMapper mapper = new ModelMapper();
		BankEntity bankEntity = mapper.map(bankDto, BankEntity.class);
		
		  Double balance = bankEntity.getBalance();
	        if (balance <= 0) {
	            throw new IllegalArgumentException("Balance should be greater than 0");
	        }
		    bankEntity.setAcc_Creation(LocalDate.now().minusYears(2));
		    BankEntity saveCustomer = repository.save(bankEntity);
		return   mapper.map(saveCustomer, BankDto.class);
		
	}
	@Override
	public BankDto getDataByAccountNumber(Integer accNum) {
		ModelMapper mapper = new ModelMapper();
		BankEntity bankEntity = repository.findById(accNum).orElseThrow(()->
		new IllegalArgumentException("invalid account number"));
		return mapper.map(bankEntity, BankDto.class);
	}
	
	@Override
	public String DebitBalance(Integer accNum, Double amount) {
		BankEntity bankEntity = repository.findById(accNum).orElseThrow(()->
		new IllegalArgumentException("invalid account number"));
		Double currentBalance = bankEntity.getBalance();
		 Double finalBalance ;
		if(currentBalance<amount) {
			throw new IllegalArgumentException("insuffeciant balance : "+ currentBalance );
		}else if (amount<0) {
			throw new IllegalArgumentException("amount must be positive : " + amount );
		}
	          finalBalance = currentBalance - amount;
	          bankEntity.setBalance(finalBalance);
	          repository.save(bankEntity);
		return  " your remaining balance is : " + finalBalance;
	}

	@Override
	public String creditbalance(Integer accNum, Double amount) {
		BankEntity bankEntity = repository.findById(accNum).orElseThrow(()->
		new IllegalArgumentException("invalid account number"));
		Double currentBalance = bankEntity.getBalance();
		
		if(amount<0) {
			throw new IllegalArgumentException("invalid amount" + amount);
		}
		Double TotalBalance = currentBalance + amount;
		bankEntity.setBalance(TotalBalance);
		    repository.save(bankEntity);
		return "total balance : " + TotalBalance;
	}

	@Override
	public String deleteCustomerAccount(Integer accNum) {
		   if(repository.existsById(accNum)) {
			   repository.deleteById(accNum);
			   return "account has deleted..."; 
		   }
		return "account number not found..";
	}

		@Override
		public List<BankDto> getAllCustomer() {
			List<BankEntity> customerList = repository.findAll();
			ModelMapper mapper = new ModelMapper();
			
	      return		customerList.stream()
			.map(e->mapper.map(e, BankDto.class))
			.collect(Collectors.toList());
			
			
//			List<BankDto> bankDtoList = new ArrayList<>();
//			for(BankEntity bankCustomer : customerList) {
//				BankDto bankDto = mapper.map(bankCustomer, BankDto.class);
//				
//				bankDtoList.add(bankDto);
//			}
//		    return bankDtoList;
//		    
		}

	@Override
	public BankDto updateCustomer(Integer accNum, BankDto bankDto) {
    ModelMapper mapper = new ModelMapper();
		Optional<BankEntity> byId = repository.findById(accNum);
		  if(byId.isPresent()) {
			  BankEntity bankCustomer = byId.get();
			  BankEntity bankEntity = mapper.map(bankDto, BankEntity.class);
			bankCustomer.setAcc_Creation(LocalDate.now());
			bankCustomer.setAccType(bankEntity.getAccType());
			bankCustomer.setBalance(bankEntity.getBalance());
			bankCustomer.setBankName(bankEntity.getBankName());
			bankCustomer.setCustomerId(bankEntity.getCustomerId());
			bankCustomer.setCustomerName(bankEntity.getCustomerName());
			bankCustomer.setGender(bankEntity.getGender());
		    bankCustomer.setIfscCode(bankEntity.getIfscCode());
		    BankEntity entity = repository.save(bankCustomer);
	  return	    mapper.map(entity, BankDto.class);
		  }
		return bankDto;
	}
}
