package com.csmarton.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csmarton.model.Product;
import com.csmarton.services.ProductService;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("product")
@Controller
public class ProductController
{
	public static final String PRODUCT_PREFIX = "product/";
	ProductService productService;

	@Autowired
	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}

	@RequestMapping("/list")
	public String listProducts(Model model){

		model.addAttribute("products", productService.listAll());

		return PRODUCT_PREFIX + "products";
	}

	@RequestMapping("/{id}")
	public String getProduct(@PathVariable(name = "id") Integer id,  Model model) {
		model.addAttribute("product", productService.getById(id));

		return PRODUCT_PREFIX + "product";
	}

	@RequestMapping("/new")
	public String addProductForm(Model model)
	{
		model.addAttribute("product", new Product());

		return PRODUCT_PREFIX + "productform";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrUpdateProduct(Product product){
		Product savedProduct = productService.updateOrCreate(product);

		return "redirect:/product/" + savedProduct.getId();
	}

	@RequestMapping("/edit/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));

		return PRODUCT_PREFIX + "productform";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productService.deleteById(id);

		return "redirect:/product/list";
	}
}
