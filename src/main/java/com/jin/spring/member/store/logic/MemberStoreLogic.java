package com.jin.spring.member.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jin.spring.member.domain.Member;
import com.jin.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int memberNoCheck(SqlSession session, String memberNo) {
		int result = session.selectOne("MemberMapper.memberNoCheck",memberNo );
		return result;
	}

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public List<Member> selectAllMember(SqlSession session) {
		List<Member> mList = session.selectList("MemberMapper.selectAllMember");
		return mList;
	}

	@Override
	public Member selectMemberByNo(SqlSession session, int memberNo) {
		Member member = session.selectOne("MemberMapper.selectMemberByNo", memberNo);
		return member;
	}

	@Override
	public int updateMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, int memberNo) {
		int result = session.delete("MemberMapper.deleteMember", memberNo);
		return result;
	}

	@Override
	public List<Member> selectMemberBySearch(SqlSession session, String searchValue) {
		List<Member> mList = session.selectList("MemberMapper.selectMemberBySearch", searchValue);
		return mList;
	}

}
