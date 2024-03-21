package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.demo.domain.Admin;
import com.demo.domain.Member;
import com.demo.domain.OrderDetail;
import com.demo.domain.Product;
import com.demo.domain.Qna;
import com.demo.service.AdminService;
import com.demo.service.MemberService;
import com.demo.service.OrderService;
import com.demo.service.ProductService;
import com.demo.service.QnaService;

import jakarta.servlet.http.HttpSession;

@SessionAttributes("adminUser")
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${com.demo.upload.path}")
	private String uploadPath;

	@GetMapping("/admin_login_form")
	public String adminLoginView() {

		return "admin/main";
	}

//	관리자 로그인 처리 구현
	@PostMapping("/admin_login")
	public String adminLogin(Admin vo, Model model) {
//		(1)관리자 로그인 확인
		int result = adminService.adminCheck(vo);

//		(2) 정상 관리자면
//			-관리자 정보 조회
//			-상품 목록으로 이동 (redirect: admin_product_list)
		if (result == 1) {
			Admin admin = adminService.getAdmin(vo.getId());
			model.addAttribute("adminUser", admin);

			return "redirect:admin_product_list";
		} else {
//		(3) 비정상 관리자면
//			-메시지를 설정하고 로그인 페이지로 이동
//			-결과 : 0 => 비밀번호를 확인해 주세요
//			-	  -1 => 아이디를 확인해 주세요
			if (result == 0) {
				model.addAttribute("message", "비밀번호를 확인해 주세요.");
			} else {
				model.addAttribute("message", "아이디를 확인해 주세요.");
			}

			return "admin/main";
		}
	}

	@GetMapping("/admin_logout")
	public String adminLogout(SessionStatus status) {

		status.setComplete();

		return "admin/main";
	}

//	전체 상품 목록 조회 처리
	/*
	 * @GetMapping("/admin_product_list") public String adminProductList(Model
	 * model) {
	 * 
	 * List<Product> productList = productService.getAllProducts("");
	 * 
	 * model.addAttribute("productList", productList);
	 * 
	 * return "admin/product/productList"; }
	 */

//	페이징 기능을 추가한 전체 상품 목록 조회

	@GetMapping("/admin_product_list")
	public String adminProductList(@RequestParam(value = "key", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, Model model) {
		Page<Product> productList = productService.getAllProductsByName(name, page, size);

		model.addAttribute("productList", productList.getContent());
		model.addAttribute("pageInfo", productList);

		return "admin/product/productList";
	}

	@GetMapping("/admin_product_wirte_form")
	public String adminProductWriteView(Model model) {
		String[] kindList = { "힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일" };

		model.addAttribute("kindList", kindList);
		return "admin/product/productWrite";
	}

	/* 상품등록 처리 */
	@PostMapping("/admin_product_write")
	public String adminProductWriteAction(Product vo, HttpSession session,
			@RequestParam(value = "product_image") MultipartFile uploadFile) {

		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename(); // 상품 이미지의 파일명 읽기
			vo.setImage(fileName);

////			이미지 파일의 실제경로로 이동하기
//			String image_path = session.getServletContext()
//					.getRealPath("/product_images");

//			UUID - 세계적으로 유니크한 ID를 생성
			String uuid = UUID.randomUUID().toString();

			String saveName = uuid + "_" + fileName;
			vo.setImage(saveName);

			try {
				uploadFile.transferTo(new File(uploadPath + File.separator + saveName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		productService.insertProduct(vo);

		return "redirect:admin_product_list";
	}

	/* 상품 상세정보 조회 */
	@RequestMapping("/admin_product_detail")
	public String adminProductDetailAction(Product vo, Model model) {
		String[] kindList = { "", "힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일" };
		Product product = productService.getProduct(vo.getPseq());

		model.addAttribute("productVO", product);
		model.addAttribute("kind", kindList[Integer.parseInt(product.getKind())]);

		return "admin/product/productDetail";
	}

	@PostMapping("/admin_product_update_form")
	public String adminProductUpdateView(Product vo, Model model) {
		String[] kindList = {"힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일" };
		Product product = productService.getProduct(vo.getPseq());

		model.addAttribute("productVO", product);
		model.addAttribute("kindList", kindList);
		model.addAttribute("kind", Integer.parseInt(product.getKind()));
		
		return "admin/product/productUpdate";

	}

	@PostMapping("/admin_product_update")
	public String adminProductUpdate(Product vo, @RequestParam(value = "product_image") MultipartFile uploadFile,
			@RequestParam(value = "nonmakeImg") String org_image) {

		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename(); // 상품 이미지의 파일명 읽기
			vo.setImage(fileName);

			String uuid = UUID.randomUUID().toString();

			String saveName = uuid + "_" + fileName;
			vo.setImage(saveName);

			try {
				uploadFile.transferTo(new File(uploadPath + File.separator + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			vo.setImage(org_image);
		}

		if (vo.getUseyn() == null) {
			vo.setUseyn("n");
		} else {
			vo.setUseyn("y");
		}

		if (vo.getBestyn() == null) {
			vo.setBestyn("n");
		} else {
			vo.setBestyn("y");
		}

		productService.updateProduct(vo);

		return "redirect:/admin_product_list";
	}
	
	@RequestMapping("/admin_order_list")
	public String adminOrderList(@RequestParam(value="key", defaultValue="") String mname,
			Model model) {
		
		List<OrderDetail> orderList = orderService.getListOrder(mname);
		model.addAttribute("orderList", orderList);
		
		return "admin/order/orderList";
	}
	
	@PostMapping("/admin_order_save")
	public String adminOrderSave(@RequestParam(value="result") int [] odseq) {
		
		for(int i = 0; i < odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		
		return "redirect:/admin_order_list";
	}
	
	@GetMapping("/admin_member_list")
	public String adminMemberList(@RequestParam(value="key", defaultValue = "") String name,
			Model model) {
		
		List<Member> memberList = memberService.getMemberList(name);
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberList";
	}
	
	@RequestMapping("/admin_qna_list")
	public String adminQnaList(Model model) {
		
		List<Qna> qnaList = qnaService.getListAllQna();
		model.addAttribute("qnaList", qnaList);
		
		return "admin/qna/qnaList";
	}
	
	@PostMapping("/admin_qna_detail")
	public String adminQnaDetail(Qna vo, Model model) {
		
		Qna qna = qnaService.getQna(vo.getQseq());
		
		model.addAttribute("qnaVO", qna);
		
		return "admin/qna/qnaDetail";
	}
	
	@PostMapping("/admin_qna_repsave")
	public String adminQnaRepSave(Qna vo) {
		
		qnaService.updateQna(vo);
		
		return "redirect:admin_qna_list";
	}
	
}
	
	