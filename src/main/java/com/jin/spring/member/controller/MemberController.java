package com.jin.spring.member.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jin.spring.member.domain.Member;
import com.jin.spring.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		return "home";
	
	}
	@RequestMapping(value="/member/registMember", method=RequestMethod.GET)
	public String memberRegistView(Model model) {
		
		return "member/registMember";
	
	}
	
	@RequestMapping("/member/memberNoCheck")
	public @ResponseBody int memberNoCheck(String memberNo) {
		int result = mService.memberNoCheck(memberNo);
		return result;
	}
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public ModelAndView memberRegister(ModelAndView mv,
			@ModelAttribute Member member ) {
		try {
			int result = mService.registMember(member);
			if(result>0) {
				mv.setViewName("redirect:/home");
			}else {
				mv.addObject("msg", "직원등록에 실패했습니다.");
				mv.setViewName("errorPage");
			}
		} catch (Exception e) {
			
			mv.addObject("msg", e.toString()).setViewName("errorPage");
		}
		return mv;
	}
	//전체직원 출력
	@RequestMapping("/member/memberList")
	public ModelAndView memberList(ModelAndView mv) {
		List<Member> mList = mService.findAllMember();
		
		mv.addObject("mList", mList);
		return mv;
	}
	
	//직원검색
	@RequestMapping(value="/member/search", method=RequestMethod.GET)
	public ModelAndView memberSearchList(ModelAndView mv,
			@RequestParam("searchValue") String searchValue) {
		try {
			List<Member> mList = mService.findMemberBySearch(searchValue);
			if(!mList.isEmpty()) {
				mv.addObject("mList", mList);
			}else {
				mv.addObject("mList", null);
			}
			mv.addObject("searchValue", searchValue);
			mv.setViewName("member/memberList");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("errorPage");
		}
		
		
		return mv;
	}
	
	//직원상세페이지
	@RequestMapping("/member/memberDetail")
	public ModelAndView memberDetail(ModelAndView mv,
			@RequestParam("memberNo") int memberNo) {
		Member member = mService.findMemberByNo(memberNo);
		System.out.println(member);
		mv.addObject("member", member);
		return mv;
	}
	//직원상세페이지->수정
	@RequestMapping(value="/member/modify", method=RequestMethod.POST)
	public ModelAndView memberModify(ModelAndView mv,
			@ModelAttribute Member member ) {
		
		try {
			int result = mService.modifyMember(member);
			if(result>0) {
				mv.setViewName("redirect:/member/memberList");
			}else {
				mv.addObject("msg", "직원정보 수정에 실패했습니다.");
				mv.setViewName("errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("errorPage");
		}

		return mv;
	}
	//직원상세페이지->삭제
	@RequestMapping("/member/delete")
	public ModelAndView memberDelete(ModelAndView mv,
			@RequestParam("memberNo") int memberNo ) {
		
		try {
			int result = mService.deleteMember(memberNo);
			if(result>0) {
				mv.setViewName("redirect:/member/memberList");
			}else {
				mv.addObject("msg", "직원정보 삭제에 실패했습니다.");
				mv.setViewName("errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("errorPage");
		}

		return mv;
	}
	
	
	
}
