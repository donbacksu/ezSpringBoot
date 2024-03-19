package com.demo.persistence;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Member;
import com.demo.domain.Qna;

@SpringBootTest
public class QnaRepositoryTest {

	@Autowired
    QnaRepository qnaRepo;
	
	@Disabled
	@Test
	public void testInsertQna() {
		
		Member member = new Member("book11", "1111", "서태웅", "shohoku11@gmail.com", "", "", "", "y", new Date());
		
		Qna qna1 = Qna.builder().subject("Qna 테스트")
				.content("질문 내용 1")
				.reply("답변 드립니다")
				.rep("2")
				.indate(new Date())
				.member(member).build();
		qnaRepo.save(qna1);
		
		Qna qna2 = Qna.builder().subject("치킨먹고싶다")
				.content("배고프다")
				.indate(new Date())
				.member(member).build();
		qnaRepo.save(qna2);
	}
	
	@Disabled
	@Test
	public void testGetQnaList() {
		
		List<Qna> qnaList = qnaRepo.getQnaList("book11");
		
		for (Qna qna : qnaList) {
			System.out.println(qna);
		}
	}
	
}
