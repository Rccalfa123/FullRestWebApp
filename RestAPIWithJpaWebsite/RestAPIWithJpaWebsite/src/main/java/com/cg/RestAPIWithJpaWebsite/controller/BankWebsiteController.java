package com.cg.RestAPIWithJpaWebsite.controller;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.cg.RestAPIWithJpaWebsite.pojo.BankAccount;

@Controller
public class BankWebsiteController {

	private RestTemplate restTemplate = new RestTemplate();
	String url;
	
	@RequestMapping("addNewAccount")
	public String addNewAccount() {
		return "addNewAccount";
	}
	
	@RequestMapping("/searchAccount/{accountNumber}")
	public String searchAccount(Model model,@PathVariable int accountNumber) {
		url = "http://localhost:8080/searchAccount/"+accountNumber;
		Resource resource = restTemplate.getForObject(url, Resource.class);
		BankAccount bankAccount = restTemplate.getForObject(url, BankAccount.class);
		System.out.println(resource.getLinks());
		System.out.println(bankAccount);
		model.addAttribute("links",resource.getLinks());
		model.addAttribute("account", bankAccount);
		return "viewAccount";
	}
    
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(@RequestBody BankAccount bankAccount,Model model) {
		url = "http://localhost:8080/addAccount";	
		model.addAttribute("addedAccount",restTemplate.postForObject(url, bankAccount, BankAccount.class));
		return "successAddPage";
	}
	
	@RequestMapping("/viewAllAccounts")
	public String viewAllAccounts(Model model) {
		url = "http://localhost:8080/viewAllAccounts";
		List<BankAccount> viewAllAccountsList = restTemplate.getForObject(url, List.class);
		model.addAttribute("viewAllAccounts", viewAllAccountsList);
		return "viewAllCustomers";
	}

	@RequestMapping("/viewAllAccounts/{start}/{count}")
	public String viewAllAccountsByPage(@PathVariable int start, @PathVariable int count, Model model) {
		
		url = "http://localhost:8080/viewAllAccounts/" + start + "/" + count;
		Resources viewAllAccountsList = restTemplate.getForObject(url, Resources.class);
		
		System.out.println(viewAllAccountsList);
		
		model.addAttribute("viewAllAccounts", viewAllAccountsList.getContent());
		model.addAttribute("links", viewAllAccountsList.getLinks());
		
		return "viewAllCustomers";
	}
}
