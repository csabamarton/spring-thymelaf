package com.csmarton.controllers;

import com.csmarton.model.Customer;
import com.csmarton.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("customer")
@Controller
public class CustomerController
{
	public static final String CUSTOMER_PREFIX = "customer/";

	CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@RequestMapping("/list")
	public String listCustomers(Model model){

		model.addAttribute("customers", customerService.listAll());

		return CUSTOMER_PREFIX + "customerlist";
	}

	@RequestMapping("/{id}")
	public String getCustomer(@PathVariable(name = "id") Integer id,  Model model) {
		model.addAttribute("customer", customerService.getById(id));

		return CUSTOMER_PREFIX + "customer";
	}

	@RequestMapping("/new")
	public String addCustomerForm(Model model)
	{
		model.addAttribute("customer", new Customer());

		return CUSTOMER_PREFIX + "customerform";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrUpdateCustomer(Customer customer){
		Customer savedCustomer = customerService.updateOrCreate(customer);

		return "redirect:/customer/" + savedCustomer.getId();
	}

	@RequestMapping("/edit/{id}")
	public String editCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getById(id));

		return CUSTOMER_PREFIX + "customerform";
	}

	@RequestMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		customerService.deleteById(id);

		return "redirect:/customer/list";
	}
}
