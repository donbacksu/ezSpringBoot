package com.demo.persistence;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Member;
import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;
import com.demo.domain.Product;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
    OrderRepository orderRepo;
	
	@Autowired
	OrderDetailRepository odRepo;
	
	@Disabled
	@Test
	public void testOrderInsert() {
		
//		주문번호 할당
		int oseq = orderRepo.selectMaxOseq();
		
		Member member = new Member("book11", "1111", "서태웅", "shohoku11@gmail.com", "", "", "", "y", new Date());
		
		Orders order1 = Orders.builder()
				.oseq(oseq)
                .member(member)
                .build();
		
//		첫번째 주문 데이터
		orderRepo.save(order1);
		
//		두번째 주문 데이터
		oseq = orderRepo.selectMaxOseq();
		Orders order2 = Orders.builder()
				.oseq(oseq)
                .member(member)
                .build();
		
		orderRepo.save(order2);
		
//		세번째 주문 데이터
		Member member2 = new Member("book10", "1111", "강백호", "shohoku10@gmail.com", "", "", "", "y", new Date());
		oseq = orderRepo.selectMaxOseq();
		Orders order3 = Orders.builder()
				.oseq(oseq)
                .member(member2)
                .build();
		orderRepo.save(order3);
	}
	
	@Disabled
	@Test
	public void testOrderDetailInsert() {
		Member member = new Member("book11", "1111", "서태웅", "shohoku11@gmail.com", "", "", "", "y", new Date());
		
		Orders order1 = new Orders(1, member, null, null);
		Orders order2 = new Orders(2, member, null, null);
		Orders order3 = new Orders(3, member, null, null);
		
		Product product1 = new Product(1, null, null, 0, 0, 0, null, null, null, null, new Date());
		Product product2 = new Product(2, null, null, 0, 0, 0, null, null, null, null, new Date());
		Product product3 = new Product(3, null, null, 0, 0, 0, null, null, null, null, new Date());
		Product product4 = new Product(4, null, null, 0, 0, 0, null, null, null, null, new Date());
		Product product6 = new Product(6, null, null, 0, 0, 0, null, null, null, null, new Date());
		
		OrderDetail[] odArr = {
			new OrderDetail(0, order1, product1, 1, "1"),
			new OrderDetail(0, order1, product2, 2, "1"),
			new OrderDetail(0, order2, product4, 3, "1"),
			new OrderDetail(0, order3, product3, 1, "1"),
			new OrderDetail(0, order3, product2, 1, "1"),
			new OrderDetail(0, order3, product6, 2, "1"),
			new OrderDetail(0, order3, product1, 1, "1"),
		};
		
		for (int i=0; i<odArr.length; i++) {
			odRepo.save(odArr[i]);
		}
	}
	
	@Disabled
	@Test
	public void testGetListOrderById() {
		List<OrderDetail> orderList = orderRepo.getListOrderById("book11", 1, "1");
		
		System.out.println("<<< 주문 상세 목록>>>");
		for (OrderDetail od : orderList) {
			System.out.println(od);
		}
	}
	
	@Test
	public void testGetSeqOrdering() {
		List<Integer> oseqList = orderRepo.getSeqOrdering("book11", "1");
		
		System.out.println("<<< 사용자별 주문번호 조회(사용자 ID : book11 >>>");
		for (int oseq : oseqList) {
			System.out.println(oseq);
		}
	}
}
