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
	
//	이름과 이메일로 아이디 찾기
	public Member getIdByNameEmail(String name, String Email);
	
//	이름과 이메일, 아이디로 비밀번호 찾기
	public Member getPwdByNameEmailId(String name, String email, String id);
	
//	비밀번호 변경
	public Member updatePwd(String id ,String pwd);
	
//	public void changePassword(Member vo);
	
	public List<Member> getMemberList(String name);
}
