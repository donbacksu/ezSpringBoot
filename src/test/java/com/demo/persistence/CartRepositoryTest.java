package com.demo.persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Cart;
import com.demo.domain.Member;
import com.demo.domain.Product;

@SpringBootTest
public class CartRepositoryTest {
	@Autowired
	private CartRepository cartRepo;

	@Disabled
	@Test
	public void testInsertCart() {
		// 장바구니에 추가할 회원 정보
		Member member = Member.builder().id("one").pwd("1111").name("홍길동").email("kimnari@email.com").zip_num("133-110")
				.address("서울시 성동구 성수동1가 1번지21호").phone("010-777-7777").regdate(new Date()).useyn("y").build();

		// 데이터베이스에서 특정 상품 조회
		Product product = new Product("크로커다일 부츠", "2", 40000, 50000, 10000, "오리지날 크로커다일", "w2.jpg", "n", "y",
				new Date());

		Cart cart = Cart.builder().member(member).product(product).quantity(1).build();

		cartRepo.save(cart);
	}
	@Disabled
	@Test
	public void testSelectCart() {
		Optional<Cart> item = cartRepo.findById(14);
		
		Cart cart = item.get();
		
		System.out.println(cart);
		System.out.println("장바구니 사용자명: " + cart.getMember().getName());
		System.out.println("장바구니 제품명: " + cart.getProduct().getName());
	}
	@Disabled
	@Test
	public void testCartList() {
		List<Cart> cartList = cartRepo.getCartList("one");
		
		for (Cart cart : cartList) {
            System.out.println(cart);}
	}

}
