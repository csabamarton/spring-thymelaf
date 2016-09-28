package com.csmarton.services;

import com.csmarton.model.Customer;
import com.csmarton.model.DomainObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceImpl extends AbstractMapService implements CustomerService
{

	public CustomerServiceImpl()
	{
		super();
	}

	@Override
	public List<DomainObject> listAll()
	{
		return super.listAll();
	}

	@Override
	public Customer getById(Integer id)
	{
		return (Customer) super.getById(id);
	}

	@Override
	public Customer updateOrCreate(Customer domainObject)
	{
		return (Customer) super.updateOrCreate(domainObject);
	}

	@Override
	public void deleteById(Integer id)
	{
		super.deleteById(id);
	}

	@Override
	protected void loadDomainObjects()
	{
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setFirstName("Csaba");
		customer1.setLastName("Marton");
		customer1.setEmail("martoncsab@gmail.com");

		domainMap.put(customer1.getId(), customer1);
	}


}
