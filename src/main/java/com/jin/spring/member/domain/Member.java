package com.jin.spring.member.domain;

public class Member {
	private int memberNo;
	private String memberName;
	private String memberPo;
	private String memberTel;
	private String memberEmail;
	private int befMemberNo;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPo() {
		return memberPo;
	}
	public void setMemberPo(String memberPo) {
		this.memberPo = memberPo;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	public int getBefMemberNo() {
		return befMemberNo;
	}
	public void setBefMemberNo(int befMemberNo) {
		this.befMemberNo = befMemberNo;
	}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberName=" + memberName + ", memberPo=" + memberPo + ", memberTel="
				+ memberTel + ", memberEmail=" + memberEmail + ", befMemberNo=" + befMemberNo + "]";
	}

	

}
