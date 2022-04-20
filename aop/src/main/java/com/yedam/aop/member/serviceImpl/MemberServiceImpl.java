package com.yedam.aop.member.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.aop.member.service.MemberService;
import com.yedam.aop.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService{

	@Autowired MemberMapper mapper;

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return mapper.memberSelect(vo);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = new MemberVO();
		vo.setId(username);
		MemberVO result = mapper.memberSelect(vo);
		if(result == null) {
			throw new UsernameNotFoundException("no user");
		}
		return result;
	}
	@Override
	@Transactional
	public int memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);  
		mapper.memberInsert(vo); //error
		return 1;
	}

}