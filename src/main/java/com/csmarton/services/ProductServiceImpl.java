package com.csmarton.services;

import com.csmarton.model.DomainObject;
import com.csmarton.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductServiceImpl extends AbstractMapService implements ProductService
{

	@Override
	protected void loadDomainObjects()
	{
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Product 1");
		p1.setPrice(new BigDecimal(23.20));
		p1.setImageUrl("1.jpg");

		domainMap.put(1, p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Product 2");
		p2.setPrice(new BigDecimal(50.40));
		p2.setImageUrl("2.jpg");

		domainMap.put(2, p2);
	}

	@Override
	public List<DomainObject> listAll()
	{
		return super.listAll();
	}

	@Override
	public Product getById(Integer id)
	{
		return (Product) super.getById(id);
	}

	@Override
	public void deleteById(Integer id)
	{
		super.deleteById(id);
	}

	@Override
	public Product updateOrCreate(Product product)
	{
		return (Product) super.updateOrCreate(product);
	}
}
