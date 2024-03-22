package com.demo.service;

import java.util.List;

import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;
import com.demo.dto.SalesCountInterface;

public interface OrderService {

//	다음 저장할 주문번호 생성
	int getMaxOseq();
	
//	주문 저장후 주문범호 반환
	int insertOrder(Orders vo);

//	사용자별 주문 내역
	List<OrderDetail> getListOrderDetailById(String id, int oseq);
	
//	사용자별 주문 내역
	Orders getListOrderById(String id, int oseq);
	
//	사용자별 전체 주문번호 내역
	List<Integer> getSeqOrdering(String id, String result);
	
//	주문 결과처리
	void updateOrderResult(int odseq);
	
//	주문 상세정보 저장
	void insertOrderDetail(OrderDetail vo);
	
//	회원명을 조건으로 주문목록 조회
	List<OrderDetail> getListOrder(String name);
	
//	제품별 판매실적 조회
	List<SalesCountInterface> getProductSales();
}
