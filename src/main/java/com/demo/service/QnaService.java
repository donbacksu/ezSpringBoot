package com.demo.service;

import java.util.List;

import com.demo.domain.Qna;

public interface QnaService {
	void insertQna(Qna qna);
	
	Qna getQna(int qseq);
	
	List<Qna> getListQna(String id);
	
	List<Qna> getListAllQna();
	
	void updateQna(Qna vo);
}
