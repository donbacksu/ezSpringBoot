package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.domain.Member;
import com.demo.domain.Qna;
import com.demo.service.QnaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class QnaController {

	@Autowired
	QnaService qnaService;

	@GetMapping("/qna_list")
	public String qnaListView(Model model, HttpSession session) {

		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인이 안되어 있음.
			return "member/login"; // 로그인 화면으로 이동
		} else {

			List<Qna> qnaList = qnaService.getListQna(loginUser.getId());
			model.addAttribute("qnaList", qnaList);
			return "qna/qnaList";
		}
	}

	@GetMapping("/qna_view")
	public String qnaView(Qna vo, Model model, HttpSession session) {

		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인이 안되어 있음.
			return "member/login"; // 로그인 화면으로 이동
		} else {

			Qna qna = qnaService.getQna(vo.getQseq());
			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";
		}
	}

	@GetMapping("/qna_write_form")
	public String qnaWriteView(Qna vo, Model model, HttpSession session) {

		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인이 안되어 있음.
			return "member/login"; // 로그인 화면으로 이동
		} else {

			return "qna/qnaWrite";
		}
	}

	@PostMapping("/qna_write")
	public String qnaWriteAction(Qna qna, HttpSession session) {

		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인이 안되어 있음.
			return "member/login"; // 로그인 화면으로 이동
		} else {

			qna.setMember(loginUser);
			qnaService.insertQna(qna);
			
			return "redirect:qna_list";
		}
	}
	
}
