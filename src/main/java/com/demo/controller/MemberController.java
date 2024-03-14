package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.domain.Member;
import com.demo.service.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
//	로그인 화면 표시
	@GetMapping("/login_form")
	public String loginView() {
		return "member/login";
	}
	
//	사용자 인증(로그인)
	@PostMapping("/login")
	public String loginAction(Member vo, Model model) {
		String url = "";
		
		if(memberService.loginID(vo) == 1) {	//정상 사용자
			model.addAttribute("loginUser", memberService.getMember(vo.getId()));
			
			url = "redirect:main";
		} else {
			url = "member/login_fail";
		}
		
		return url;
	}
}
