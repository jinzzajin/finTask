package com.jin.spring.member.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.jin.spring.member.domain.Member;

public interface MemberStore {

	int memberNoCheck(SqlSession session, String memberNo);

	int insertMember(SqlSession session, Member member);

	List<Member> selectAllMember(SqlSession session);

	Member selectMemberByNo(SqlSession session, int memberNo);

	int updateMember(SqlSession session, Member member);

	int deleteMember(SqlSession session, int memberNo);

	List<Member> selectMemberBySearch(SqlSession session, String searchValue);

	

}
