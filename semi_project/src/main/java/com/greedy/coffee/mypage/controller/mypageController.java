package com.greedy.coffee.mypage.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.service.AuthenticationService;
import com.greedy.coffee.mypage.dto.OrderDTO;
import com.greedy.coffee.mypage.service.MypageService;
import com.greedy.coffee.product.dto.ProDTO;
import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.service.QnaService;
import com.greedy.coffee.review.Service.RevBoardService;
import com.greedy.coffee.review.dto.RevBoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class mypageController {

	private final MypageService mypageServcie;
	private final AuthenticationService authenticationService;
	private final MessageSourceAccessor messageSourceAccessor;
	private final RevBoardService revBoardService;
	private final QnaService qnaService;

	public mypageController(MypageService mypageServcie, AuthenticationService authenticationService,
			MessageSourceAccessor messageSourceAccessor, QnaService qnaService, RevBoardService revBoardService) {

		this.mypageServcie = mypageServcie;
		this.authenticationService = authenticationService;
		this.messageSourceAccessor = messageSourceAccessor;
		this.revBoardService = revBoardService;
		this.qnaService = qnaService;
	}

	/* 회원정보수정 화면 이동 */
	@GetMapping("/userpage")
	public String goModifyMember() {

		return "mypage/userpage";
	}

	@PostMapping("/update")
	public String modifyMember(@ModelAttribute MemberDTO updateMember, @AuthenticationPrincipal MemberDTO loginMember,
			RedirectAttributes rttr) {

		/* 로그인 멤버 정보로 부터 가져온 pk를 update용 member dto에 전달 */
		updateMember.setMemId(loginMember.getMemId());

		log.info("[[[[1_로그인멤버]]]MemberController] loginMember : {}", loginMember);
		log.info("[[[[2_디비상에서 수정/업데이트멤버]]]MemberController] modifyMember request Member : {}", updateMember); // 디비상의 수정

		/* 세션에 저장 되어 있는 로그인 회원의 정보를 변경한다. */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 현재저장 정보
		SecurityContextHolder.getContext()
				.setAuthentication(createNewAuthentication(authentication, loginMember.getMemId()));// 데이터수정
		// SecurityContextHolder.getContext().setAuthentication 인증객체를 새롭게 설정해주는 코드

		log.info("[[[[3_데이터 수정]]]]MemberController] authentication : {}", authentication);

		/* 멤버서비스로 부터 업데이트 요청 하기 */
		mypageServcie.modifyMember(updateMember);

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("mypage.modify"));

		return "redirect:/";
	}

	/* 회원 탈퇴 */
	/*
	 * 저장 된 인증 객체로 부터 정보를 얻어 memberService.removeMember()호출
	 * SecurityContextHolder.clearContext() 메서드 호출하여 세션 초기화 member.delete 메시지 가져가서
	 * redirect아고 alert
	 */
	@GetMapping("/delete") // (@AuthenticationPrincipal MemberDTO member 로그인된 유저객체 정보
	public String deleteMember(@AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {

		log.info("[MemberController] deleteMember ==========================================================");
		log.info("[MemberController] member : " + member);

		mypageServcie.removeMember(member); // 서비스 쪽으로 비즈니스 로직을 수행

		SecurityContextHolder.clearContext(); // 시큐리티 컨텍스트홀더를 글리어하여 로그인상태 해지

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("mypage.delete")); // 메세지 저장

		log.info(" 탈퇴[MemberController] deleteMember ==========================================================");

		return "redirect:/"; // 메인화면에서알럿창 띄워줌
	}

	@GetMapping("/mybag")
	public void mybagPage() {
	}

	@GetMapping("/mypost")
	public String mypostPage(Model model) {

		// 현재 로그인된 유저 정보 가져오기
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("principal {}", auth.getPrincipal());

		Page<QnaDTO> qnaList = null;
		Page<RevBoardDTO> revBoardList = null;
		String searchValue = "";
		int page = 1;

		MemberDTO member = (MemberDTO) auth.getPrincipal();
		qnaList = mypageServcie.selectQnaListInMypage(member);
		revBoardList = mypageServcie.selectRevBoardListInMypage(member);

		PagingButtonInfo revPaging = Pagenation.getPagingButtonInfo(revBoardList);
		PagingButtonInfo qnaPaging = Pagenation.getPagingButtonInfo(qnaList);

		model.addAttribute("revBoardList", revBoardList);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("revBoardSize", revBoardList.getTotalElements());
		model.addAttribute("qnaListSize", qnaList.getTotalElements());

		return "mypage/mypost";
	}

	@GetMapping("/myorder")
	public String myorderPage(@RequestParam(defaultValue = "1") int page, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("principal {}", auth.getPrincipal());
		MemberDTO member = (MemberDTO) auth.getPrincipal();

		Page<OrderDTO> orderList = mypageServcie.selectOrderListInMypage(page, member);

		PagingButtonInfo orderPaging = Pagenation.getPagingButtonInfo(orderList);

		model.addAttribute("orderList", orderList);
		model.addAttribute("orderPaging", orderPaging);

		model.addAttribute("orderListSize", orderList.getTotalElements());

		log.info("orderList {} paging {} size {}", orderList.getContent(), orderPaging, orderList.getTotalElements());

		return "mypage/myorder";
	}

	@PostMapping("/order/cancel")
	public String cancelOrder(@ModelAttribute OrderDTO orderDTO) {
		// 로그인 검증 & 본인 주문 확인 추가하기
		log.info("[OrderCotroller] =======================================");
		log.info("[order cancel] request : {}", orderDTO);

		if (orderDTO == null || orderDTO.getOrderCode() == null) {
			return "redirect:/";
		}

		mypageServcie.cancelOrder(orderDTO);

		return "redirect:/mypage/myorder";
	}

	@PostMapping("/order/back")
	public String takeBackOrder(@ModelAttribute OrderDTO orderDTO) {
		// 로그인 검증 & 본인 주문 확인 추가하기
		log.info("[OrderCotroller] =======================================");
		log.info("[order takeback] request : {}", orderDTO);

		if (orderDTO == null || orderDTO.getOrderCode() == null) {
			return "redirect:/";
		}

		mypageServcie.takeBackOrder(orderDTO);

		return "redirect:/mypage/myorder";
	}

	/* createNewAuthentication메소드정의 */
	protected Authentication createNewAuthentication(Authentication currentAuth, String memId) {

		// newPrincipal새로운 인증객체가 된다.
		/* 인증토큰 만들기 = 토큰을 만들어서 반환 */
		UserDetails newPrincipal = authenticationService.loadUserByUsername(memId);
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal,
				currentAuth.getCredentials(), newPrincipal.getAuthorities());
		newAuth.setDetails(currentAuth.getDetails());

		return newAuth;

	}

}
