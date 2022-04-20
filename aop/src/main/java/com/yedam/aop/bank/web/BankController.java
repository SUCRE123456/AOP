package com.yedam.aop.bank.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

	@GetMapping("/accountList")
	public List<AccountVO> accountList(BankVO vo){
		return BankAPI.getAccountList(vo);
	}
	
	
	@GetMapping("/balanceList") 
	public static long getBalanceInfo(BankVO vo) {
	  return BankAPI.getBalanceInfo(vo);
	}
	  
	 
	@GetMapping("/transactionList")
	public Map getTransaction(BankVO vo) {
		return BankAPI.getTransaction(vo);
	}
	
	@RequestMapping("/bankCallback")
	public String bankCallback(String code) {
		//code로 토큰발급
		TokenVO vo = BankAPI.getToken(code);
		return "";
	}
	
}