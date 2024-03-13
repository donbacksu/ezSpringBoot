package com.demo.persistence;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Product;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepo;
	
	@Test
	public void productInsert() {
		
		Product[] products = {
				new Product("크로커다일부츠", "2", 40000, 50000, 10000, "오리지날 크로커다일부츠 입니다.", "w2.jpg", "n", "y", new Date()),
				new Product("롱부츠", "2", 40000, 50000, 10000, "따뜻한 롱부츠 입니다.", "w-28.jpg", "n", "y", new Date()),
				new Product("힐", "1", 10000, 12000, 2000, "여성 전용 힐", "w-26.jpg", "n", "y", new Date()),
				new Product("슬리퍼", "4", 5000, 5500, 500, "편안한 슬리퍼 입니다.", "w-25.jpg", "y", "y", new Date()),
				new Product("회색힐", "1", 10000, 12000, 2000, "여성 전용 힐", "w9.jpg", "n", "y", new Date()),
				new Product("여성 부츠", "2", 12000, 18000, 6000, "여성용 부츠", "w4.jpg", "n", "y", new Date()),
				new Product("핑크샌달", "3", 5000, 5500, 500, "사계절용 샌달 입니다.", "w-10.jpg", "y", "y", new Date()),
				new Product("슬리퍼", "3", 5000, 5500, 500, "편안한 슬리퍼 입니다.", "w11.jpg", "y", "y", new Date()),
				new Product("스니커즈", "4", 15000, 20000, 5000, "활동성이 좋은 스니커즈입니다.", "w1.jpg", "n", "y", new Date()),
				new Product("샌달", "3", 5000, 5500, 500, "사계절용 샌달 입니다.", "w-09.jpg", "n", "y", new Date()),
				new Product("스니커즈", "5", 15000, 20000, 5000, "활동성이 좋은 스니커즈입니다.", "w-05.jpg", "n", "y", new Date()),
			};
		
		for (int i = 0; i <products.length; i++) {
			productRepo.save(products[i]);
		}
	}
}
