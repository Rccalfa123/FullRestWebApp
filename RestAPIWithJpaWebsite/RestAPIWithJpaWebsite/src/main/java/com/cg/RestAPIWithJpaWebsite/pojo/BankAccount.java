package com.cg.RestAPIWithJpaWebsite.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int accountNumber;
	private String accountHolderName;
	private String accountType;
	private double accountBalance;
	
	public BankAccount(String accountHolderName, String accountType, double accountBalance) {
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	
	public BankAccount() {
		
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", accountType=" + accountType + ", accountBalance=" + accountBalance + "]";
	}
	
	
	
	
}
