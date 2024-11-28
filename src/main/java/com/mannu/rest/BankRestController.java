package com.mannu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mannu.dto.BankDto;
import com.mannu.service.IBankService;

@RestController
public class BankRestController {

	@Autowired
	private IBankService service;

	@PostMapping("/add")
  public ResponseEntity<BankDto> addCustomer (@RequestBody BankDto bankDto ) {
	  BankDto customer = service.addCustomer(bankDto);
	  return new ResponseEntity<BankDto>(customer, HttpStatus.CREATED);
  }
	
	@GetMapping("/get/{accNum}")
	public  ResponseEntity<BankDto>  getUserByAccount(@PathVariable Integer accNum) {
		  BankDto dataByAccountNumber = service.getDataByAccountNumber(accNum);
		  return new ResponseEntity<BankDto>(dataByAccountNumber, HttpStatus.OK);
	}
	
	@GetMapping("/debit/{accNum}/{amount}")
	public ResponseEntity<String> debitBalance(@PathVariable Integer accNum,@PathVariable Double amount) {
	   String balance = service.DebitBalance(accNum, amount);
	   return new ResponseEntity<String>(balance, HttpStatus.OK);
		
	}
	@GetMapping("/credit/{accNum}/{amount}")
	public ResponseEntity<String> creditAmount(@PathVariable Integer accNum, @PathVariable Double amount){
		String creditbalance = service.creditbalance(accNum, amount);
		return new ResponseEntity<String>(creditbalance, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{accNum}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer accNum){
		String customerAccount = service.deleteCustomerAccount(accNum);
		return new ResponseEntity<String>(customerAccount, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BankDto>> getAllCustomer(){
		List<BankDto> allCustomer = service.getAllCustomer();
		return new ResponseEntity<List<BankDto>>(allCustomer, HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{accNum}")
	  public ResponseEntity<BankDto> updateCustomer(@PathVariable Integer accNum, @RequestBody BankDto bankDto ){
		  BankDto updateCustomer = service.updateCustomer(accNum, bankDto);
		  return new ResponseEntity<BankDto>(updateCustomer, HttpStatus.OK);
		  
	  }
	
}
