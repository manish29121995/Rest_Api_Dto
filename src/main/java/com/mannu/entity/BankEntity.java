package com.mannu.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Mannu_Bank")
public class BankEntity {
@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
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
