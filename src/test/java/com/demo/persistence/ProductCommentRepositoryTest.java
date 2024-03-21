package com.demo.persistence;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Member;
import com.demo.domain.Product;
import com.demo.domain.ProductComment;

@SpringBootTest
public class ProductCommentRepositoryTest {

	@Autowired
	ProductCommentRepository commentRepo;
	
	@Test
	public void testInsertComment() {
		
		// 장바구니에 추가할 회원 정보
		Member member = Member.builder().id("one").pwd("1111").name("홍길동").email("kimnari@email.com").zip_num("133-110")
				.address("서울시 성동구 성수동1가 1번지21호").phone("010-777-7777").regdate(new Date()).useyn("y").build();

		// 데이터베이스에서 특정 상품 조회
		Product product = new Product(1, "크로커다일 부츠", "2", 40000, 50000, 10000, "오리지날 크로커다일", "w2.jpg", "n", "y",
				new Date());

		ProductComment comment = ProductComment.builder()
				.product(product)
				.content("양념치킨 먹고싶다.")
				.member(member)
				.build();
		
		commentRepo.save(comment);
	}
}
