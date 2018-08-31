package com.cg.RestAPIWithJpa.Controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.RestAPIWithJpa.bankService.BankService;
import com.cg.RestAPIWithJpa.exceptions.AccountNotFoundException;
import com.cg.RestAPIWithJpa.exceptions.AmountNoValidException;
import com.cg.RestAPIWithJpa.exceptions.Insufficient_Balance_Exception;
import com.cg.RestAPIWithJpa.exceptions.NoAccountsToDisplayException;
import com.cg.RestAPIWithJpa.exceptions.PageNotFoundException;
import com.cg.RestAPIWithJpa.pojo.BankAccount;

@RestController
public class BankController {

	@Autowired
	BankService bankService;

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(@RequestBody BankAccount bankAccount) {
		bankService.addbankAccount(bankAccount);
		return "SuccessFully Addded";
	}

	@RequestMapping("/searchAccount/{accountToBeSearched}")
	public Resource searchAccount(@PathVariable int accountToBeSearched)
			throws AccountNotFoundException, NoAccountsToDisplayException {
		Resource resource = null;
		if (bankService.searchAccount(accountToBeSearched) != null) {

			Link viewAll = linkTo(methodOn(this.getClass()).viewAllAccounts()).withRel("ViewAllCustomers");
			resource = new Resource(bankService.searchAccount(accountToBeSearched), viewAll);
			return resource;

		} else {
			throw new AccountNotFoundException("AccountNumber " + accountToBeSearched + " Not Found.......For Search");
		}
	}

	@RequestMapping("/viewAllAccounts/{start}/{count}")
	public Resources viewAccountsByPage(@PathVariable int start, @PathVariable int count)
			throws NoAccountsToDisplayException {
		Resources resources = null;

		List<BankAccount> temporaryAccount = new ArrayList<>();
		List<BankAccount> bankAccount = bankService.viewAllAccounts();
		for (int i = start; i < start + count && i <= bankService.viewAllAccounts().size(); i++) {
			temporaryAccount.add(bankAccount.get(i - 1));
		}

		if (start == 1) {
			Link nextLink = linkTo(methodOn(this.getClass()).viewAccountsByPage(start + count, count))
					.withRel("NextLink");
			resources = new Resources<>(temporaryAccount, nextLink);
		}

		else if (start + count > bankService.viewAllAccounts().size()) {
			Link previousLink = linkTo(
					methodOn(this.getClass()).viewAccountsByPage(start - count > 0 ? start - count : 1, count))
							.withRel("previousLink");
			resources = new Resources<>(temporaryAccount, previousLink);
		}

		else {
			Link nextLink = linkTo(methodOn(this.getClass()).viewAccountsByPage(start + count, count))
					.withRel("NextLink");
			Link previousLink = linkTo(
					methodOn(this.getClass()).viewAccountsByPage(start - count > 0 ? start - count : 1, count))
							.withRel("previousLink");
			resources = new Resources<>(temporaryAccount, nextLink, previousLink);
		}
		return resources;
	}

	@RequestMapping("/viewAllAccounts")
	public List<BankAccount> viewAllAccounts() throws NoAccountsToDisplayException {
		return bankService.viewAllAccounts();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updateAccount(@RequestBody BankAccount bankAccount) throws AccountNotFoundException {
		bankService.updateAccount(bankAccount);
		return "SuccessFUlly Updated";
	}

	@RequestMapping(value = "/delete/{accountToBeDeleted}", method = RequestMethod.DELETE)
	public String updateAccount(@PathVariable int accountToBeDeleted) throws AccountNotFoundException {
		bankService.deleteAccount(accountToBeDeleted);
		return "SuccessFUlly Deleted";
	}

	@RequestMapping(value = "/deposit/{accountToBeDeposit}/{amountToBeDeposit}", method = RequestMethod.PUT)
	public String deposit(@PathVariable int accountToBeDeposit, @PathVariable double amountToBeDeposit)
			throws AccountNotFoundException, AmountNoValidException {
		bankService.deposit(accountToBeDeposit, amountToBeDeposit);
		return "SuccessFully Deposited";

	}

	@RequestMapping(value = "/withdraw/{accountToBeWithdraw}/{amountToBeWithdraw}", method = RequestMethod.PUT)
	public String wihtdraw(@PathVariable int accountToBeWithdraw, @PathVariable double amountToBeWithdraw)
			throws AccountNotFoundException, Insufficient_Balance_Exception {
		bankService.withdraw(accountToBeWithdraw, amountToBeWithdraw);
		return "SuccessFully Withdrawn";

	}

	@RequestMapping(value = "/fundTransfer/{accountInWithdraw}/{accountInDeposit}/{amountInTransfer}")
	public String fundTransfer(@PathVariable int accountInWithdraw, @PathVariable int accountInDeposit,
			@PathVariable double amountInTransfer) throws Insufficient_Balance_Exception, AccountNotFoundException {
		bankService.fundTransfer(accountInWithdraw, accountInDeposit, amountInTransfer);

		return "Fund Transfer is SuccessFull";
	}
	
	@RequestMapping("/*")
	public void Error() throws PageNotFoundException
	{
		throw new PageNotFoundException("No Page Exits Please Check URL or Method...");
	}
}
