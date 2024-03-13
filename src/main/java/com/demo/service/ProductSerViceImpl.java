package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Product;
import com.demo.persistence.ProductRepository;

@Service
public class ProductSerViceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> getNewProductList() {
		
		return productRepo.getNewProduct();
	}

	@Override
	public List<Product> getBestProductList() {
		
		return productRepo.getBestProduct();
	}

}
