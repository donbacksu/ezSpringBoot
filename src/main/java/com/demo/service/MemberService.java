package com.demo.service;

import java.util.List;

import com.demo.domain.Address;
import com.demo.domain.Member;

public interface MemberService {
	
	public void insertMember(Member vo);
	
	// 회원정보 상세 조회
	public Member getMember(String id);
	
	// 회원 로그인
	public int loginID(Member vo);
	
	// 회원 인증
	public int confirmID(String id);
	
	// 동이름으로 주소 찾기
	public List<Address> getAddressByDong(String dong);
	
}
