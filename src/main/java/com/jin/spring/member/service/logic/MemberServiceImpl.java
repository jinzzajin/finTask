package com.jin.spring.member.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.spring.member.domain.Member;
import com.jin.spring.member.service.MemberService;
import com.jin.spring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private MemberStore mStore;
	@Override
	public int memberNoCheck(String memberNo) {
		int result = mStore.memberNoCheck(session, memberNo);
		return result;
	}
	@Override
	public int registMember(Member member) {
		int result = mStore.insertMember(session, member);
		return result;
	}
	@Override
	public List<Member> findAllMember() {
		List<Member> mList = mStore.selectAllMember(session);
		
		return mList;
	}
	@Override
	public Member findMemberByNo(int memberNo) {
		Member member = mStore.selectMemberByNo(session, memberNo);
		return member;
	}
	@Override
	public int modifyMember(Member member) {
		int result = mStore.updateMember(session, member);
		return result;
	}
	@Override
	public int deleteMember(int memberNo) {
		int result = mStore.deleteMember(session, memberNo);
		return result;
	}
	@Override
	public List<Member> findMemberBySearch(String searchValue) {
		List<Member> mList = mStore.selectMemberBySearch(session, searchValue);
		return mList;
	}
}
