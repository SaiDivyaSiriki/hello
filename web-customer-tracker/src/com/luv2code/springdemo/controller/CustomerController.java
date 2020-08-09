package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get the customers from customer service

		List<Customer> customers = customerService.getCustomers();

		// add customers to the model
		theModel.addAttribute("customers", customers);

		return "list-customer";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		theModel.addAttribute("customer", new Customer());

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		// save customer
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from the database
		Customer customer = customerService.getCustomer(theId);

		// add it as model attribute
		theModel.addAttribute("customer", customer);

		return "customer-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from the database ;
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}

}
