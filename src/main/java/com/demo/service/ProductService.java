package com.demo.service;

import java.util.List;

import com.demo.domain.Product;

public interface ProductService {
	
	public List<Product> getNewProductList();
	
	public List<Product> getBestProductList();	
}
