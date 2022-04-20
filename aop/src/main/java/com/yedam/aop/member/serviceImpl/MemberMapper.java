package com.yedam.aop.member.serviceImpl;


import com.yedam.aop.member.service.MemberVO;

public interface MemberMapper {
	
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
	
	
}
