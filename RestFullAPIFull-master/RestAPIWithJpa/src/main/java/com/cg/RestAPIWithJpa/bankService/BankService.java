package com.cg.RestAPIWithJpa.bankService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.RestAPIWithJpa.BankDao.BankAccountDaoJPA;
import com.cg.RestAPIWithJpa.BankDao.BankAccountDaoMongo;
import com.cg.RestAPIWithJpa.exceptions.AccountNotFoundException;
import com.cg.RestAPIWithJpa.exceptions.AmountNoValidException;
import com.cg.RestAPIWithJpa.exceptions.Insufficient_Balance_Exception;
import com.cg.RestAPIWithJpa.exceptions.NoAccountsToDisplayException;
import com.cg.RestAPIWithJpa.pojo.BankAccount;

@Service
public class BankService {

	@Autowired
	private BankAccountDaoJPA bankAccountDaoJPA;
	
	@Autowired
	private BankAccountDaoMongo bankAccountDaoMongo;

	public void addbankAccount(BankAccount bankAccount) {
		bankAccountDaoJPA.save(bankAccount);
	}

	public BankAccount searchAccount(int accountToBeSearched) throws AccountNotFoundException {
		System.out.println("*********************Hello************************");
		 if(bankAccountDaoJPA.findById(accountToBeSearched).isPresent())
		 {	 
			 return bankAccountDaoJPA.findById(accountToBeSearched).get();
		 }
		 else
		 {
			 throw new AccountNotFoundException("No Account Exists of this Number.....");
		 }
	}

	public List<BankAccount> viewAllAccounts() throws NoAccountsToDisplayException {
		if (bankAccountDaoJPA.findAll().size() == 0) {
			throw new NoAccountsToDisplayException("OOps... There is No Account To Display");
		}
		return bankAccountDaoJPA.findAll();
	}

	public void updateAccount(BankAccount bankAccount) throws AccountNotFoundException {
		if (searchAccount(bankAccount.getAccountNumber()) != null)
			bankAccountDaoJPA.save(bankAccount);
	}

	public void deleteAccount(int accountToBeDeleted) throws AccountNotFoundException {
		if (searchAccount(accountToBeDeleted) != null)
			bankAccountDaoJPA.deleteById(accountToBeDeleted);
	}

	public void deposit(int accountToBeDeposit, double amountToBeDeposit)
			throws AccountNotFoundException, AmountNoValidException {
		if (amountToBeDeposit <= 0) {
			throw new AmountNoValidException("Amount is Not Valid Please Check The Entered Amount.....");
		}
		BankAccount bankAccount = searchAccount(accountToBeDeposit);
		bankAccount.setAccountBalance(bankAccount.getAccountBalance() + amountToBeDeposit);
		bankAccountDaoJPA.save(bankAccount);
	}

	public void withdraw(int accountToBeWithdraw, double amountToBeWithdraw)
			throws AccountNotFoundException, Insufficient_Balance_Exception {
		BankAccount bankAccount = searchAccount(accountToBeWithdraw);
		if (bankAccount.getAccountBalance() < amountToBeWithdraw) {
			throw new Insufficient_Balance_Exception("Withdrawal amount is greater than Available Balance......");
		}
		bankAccount.setAccountBalance(bankAccount.getAccountBalance() - amountToBeWithdraw);
		bankAccountDaoJPA.save(bankAccount);
	}

	public void fundTransfer(int accountInWithdraw, int accountInDeposit, double amountInTransfer)
			throws Insufficient_Balance_Exception, AccountNotFoundException {
		BankAccount bankAccountinWithDraw = searchAccount(accountInWithdraw);
		BankAccount bankAccountinDeposit = searchAccount(accountInDeposit);
		if (bankAccountinWithDraw.getAccountBalance() < amountInTransfer) {
			throw new Insufficient_Balance_Exception("Withdrawal amount is greater than Available Balance......");
		}
		bankAccountinWithDraw.setAccountBalance(bankAccountinWithDraw.getAccountBalance() - amountInTransfer);
		bankAccountDaoJPA.save(bankAccountinWithDraw);
		bankAccountinDeposit.setAccountBalance(bankAccountinDeposit.getAccountBalance() + amountInTransfer);
		bankAccountDaoJPA.save(bankAccountinDeposit);
	}
}