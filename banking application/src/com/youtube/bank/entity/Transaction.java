package com.youtube.bank.entity;
import java.time.LocalDate;
public class Transaction {
	private LocalDate transactionDate;
	private String TransactionUserId;
	private Double TransactionAmount;
	private String TransactionType;
	private Double initialBalance;
	private Double finalBalance;
	private String transactionPerformedBy;
	
	public Transaction(LocalDate transactionDate, String transactionUserId, Double transactionAmount,
			String transactionType, Double initialBalance, Double finalBalance, String transactionPerformedBy) {
		super();
		this.transactionDate = transactionDate;
		TransactionUserId = transactionUserId;
		TransactionAmount = transactionAmount;
		TransactionType = transactionType;
		this.initialBalance = initialBalance;
		this.finalBalance = finalBalance;
		this.transactionPerformedBy = transactionPerformedBy;
	}
	
	
	public String getTransactionPerformedBy() {
		return transactionPerformedBy;
	}


	public void setTransactionPerformedBy(String transactionPerformedBy) {
		this.transactionPerformedBy = transactionPerformedBy;
	}


	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionUserId() {
		return TransactionUserId;
	}
	public void setTransactionUserId(String transactionUserId) {
		TransactionUserId = transactionUserId;
	}
	public Double getTransactionAmount() {
		return TransactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		TransactionAmount = transactionAmount;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	public Double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}
	public Double getFinalBalance() {
		return finalBalance;
	}
	public void setFinalBalance(Double finalBalance) {
		this.finalBalance = finalBalance;
	}


	@Override
	public String toString() {
		return "Transaction [transactionDate=" + transactionDate + ", TransactionUserId=" + TransactionUserId
				+ ", TransactionAmount=" + TransactionAmount + ", TransactionType=" + TransactionType
				+ ", initialBalance=" + initialBalance + ", finalBalance=" + finalBalance + ", transactionPerformedBy="
				+ transactionPerformedBy + "]";
	}
	
	

}
