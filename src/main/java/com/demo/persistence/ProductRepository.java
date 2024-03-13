package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

//	신상품 조회
	@Query(value="SELECT * FROME new_pro_view", nativeQuery = true)
	List<Product> getNewProduct();
	
//	베스트 상품 조회
	@Query(value="SELECT * FROME new_pro_view", nativeQuery = true)
	List<Product> getBestProduct();
}
