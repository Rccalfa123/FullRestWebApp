package com.cg.RestAPIWithJpaWebsite.pojo;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Transaction")
public class Transaction{

	@Id
	private ObjectId _id;
	private int transactionId;
	private LocalDate transactionDate;
	private int accountNumber;
	private String transactionType;
	private Double transactionAmount;
	
	public Transaction(int bankaccount,int transactionId, LocalDate transactionDate,String transactionType, Double transactionAmount) {
		this.accountNumber = bankaccount;
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Transaction() {}

	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", transactionId=" + transactionId + ", transactionType="
				+ transactionType + ", transactionAmount=" + transactionAmount + "]";
	}
	
}
