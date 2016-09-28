package com.csmarton.controllers;

import com.csmarton.model.Product;
import com.csmarton.services.ProductService;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testRequestProductListPageAndGetBackRightNumberOfProducts() throws Exception
	{

		List<Product> products = Lists.newArrayList();
		int numberOfProducts = 2;

		IntStream.range(0, numberOfProducts).forEach(counter -> products.add(new Product()));

		when(productService.listAll()).thenReturn((List)products);

		mockMvc.perform(get("/product/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("product/products"))
				.andExpect(model().attribute("products", hasSize(numberOfProducts)));
	}

	@Test
	public void testShowProduct() throws Exception{
		int id = 1;

		when(productService.getById(id)).thenReturn(new Product());

		mockMvc.perform(get("/product/"+id))
				.andExpect(status().isOk())
				.andExpect(view().name("product/product"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void testShowEditProductPage() throws Exception{
		int id = 1;

		when(productService.getById(id)).thenReturn(new Product());

		mockMvc.perform(get("/product/edit/"+id))
				.andExpect(status().isOk())
				.andExpect(view().name("product/productform"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}
}