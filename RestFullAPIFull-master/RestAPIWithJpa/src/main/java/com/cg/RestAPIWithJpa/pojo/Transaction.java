package com.cg.RestAPIWithJpa.pojo;

public class Transaction{

	private int bankaccount;
	private int transactionId;
	private String transactionType;
	private Double transactionAmount;
	
	public Transaction(int bankaccount, int transactionId, String transactionType, Double transactionAmount) {
		super();
		this.bankaccount = bankaccount;
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(int bankaccount) {
		this.bankaccount = bankaccount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
		return "Transaction [bankaccount=" + bankaccount + ", transactionId=" + transactionId + ", transactionType="
				+ transactionType + ", transactionAmount=" + transactionAmount + "]";
	}
	
}
