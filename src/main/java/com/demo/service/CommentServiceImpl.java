package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.ProductComment;
import com.demo.persistence.ProductCommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private ProductCommentRepository commentRepo;
	
	@Override
	public void saveComment(ProductComment comment) {
		
		commentRepo.save(comment);
		
	}

	@Override
	public List<ProductComment> getCommentList(int pseq) {

		return commentRepo.findCommentByPseq(pseq);
	}

	@Override
	public int getCountCommentList(int pseq) {
		
		List<ProductComment> cList = commentRepo.findCommentByPseq(pseq);
		
		int size = cList.size();
		
		return size;
	}

}
