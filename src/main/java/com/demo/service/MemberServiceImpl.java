package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Member;
import com.demo.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public void insertMember(Member vo) {
//		회원 정보 저장
		memberRepo.save(vo);
	}

	@Override
	public Member getMember(String id) {
		
		return memberRepo.findById(id).get();
	}

	@Override
	public int loginID(Member vo) {
		int result = -1;
		
//		Member 테이블에서 사용자 조회
		Optional<Member> member = memberRepo.findById(vo.getId());
		
//		결과값 설정 :
//		1: ID,PWD 일치, 0: 비밀번호 불일치, -1: ID가 존재하지 않음.
		if(member.isEmpty()) {
			result = -1;
		} else if(member.get().getPwd().equals(vo.getPwd())) {
			result = 1;
		} else {
			result = 0; //비밀번호 불일치
		}
		
		return result;
	}

//	returnr값 : 1:id 존재, -1: id 존재하지 않음.
	@Override
	public int confirmID(String id) {
		Optional<Member> member = memberRepo.findById(id);
		
		if (member.isPresent()) {
			return 1;
		} else {
			return -1;
		}
	}

}
