package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
//	다음 주문번호 생성
	@Query(value="SELECT NVL2(MAX(oseq), MAX(oseq)+1, 1) FROM ORDERS o", nativeQuery=true)
	public int selectMaxOseq();
	
//	사용자별 주문내역 조회(JPQL 사용 domainClass명 사용)
	@Query( "SELECT od FROM OrderDetail od " +
			"INNER JOIN Orders o ON od.order.oseq = o.oseq " +
			"INNER JOIN Product p ON od.product.pseq = p.pseq " +
			"INNER JOIN Member m ON o.member.id = m.id " +
			"WHERE o.member.id = ?1 AND od.order.oseq = ?2  AND od.result LIKE %?3%")
	public List<OrderDetail> getListOrderById(String id, int oseq, String result);
	
//	사용자별 주문내역 조회
	@Query("SELECT order FROM Orders order " +
			"INNER JOIN Member m ON order.member.id = m.id " +
			"WHERE order.member.id = ?1 AND order.oseq = ?2 ")
	
	public Orders getOrderByMemberId(String id, int oseq);
	
	
//	사용자별 전체 주문번호 조회(JPQL 문법 적용
	@Query("SELECT DISTINCT od.order.oseq "
			+ "FROM	OrderDetail od "
			+ "INNER JOIN Orders o ON od.order.oseq = o.oseq "
			+ "INNER JOIN Member m ON o.member.id = m.id "
			+ "WHERE o.member.id =?1 AND od.result  LIKE %?2% ORDER BY od.order.oseq DESC "
			)
	public List<Integer> getSeqOrdering(String id, String result);
	
	@Query("SELECT od FROM OrderDetail od " 
			+ " WHERE od.order.member.name LIKE %?1% "
			+ " ORDER BY od.result, od.order.oseq DESC ")
	public List<OrderDetail> getOrderListByName(String mname);
}
