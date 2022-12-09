package com.jin.spring.member.service;

import java.util.List;

import com.jin.spring.member.domain.Member;

public interface MemberService {

	public int memberNoCheck(String memberNo);

	public int registMember(Member member);

	public List<Member> findAllMember();

	public Member findMemberByNo(int memberNo);

	public int modifyMember(Member member);

	public int deleteMember(int memberNo);

	public List<Member> findMemberBySearch(String searchValue);

}
