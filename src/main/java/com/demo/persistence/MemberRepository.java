package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Cart;
import com.demo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	public Member findByNameAndEmail(String name, String email);
	
	public Member findByNameAndEmailAndId(String name, String email, String id);
	
//	@Modifying
//    @Transactional
//    @Query(value = "UPDATE member SET pwd= :pwd WHERE id= :id", nativeQuery=true) // nativeQery
//    public void changePassword(@Param("id") String id, @Param("pwd") String pwd);
	
//	회원별 장바구니 상품 목록
	@Query("SELECT c FROM Cart c WHERE c.member.id=?1 AND c.result='1'")
	public List<Cart> getCartList(String userId);
	
//	회원명을 조건으로 회원목록 조회
	public List<Member> findMemberByNameContaining(String name);
}
