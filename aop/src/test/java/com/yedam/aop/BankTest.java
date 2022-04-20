package com.yedam.aop;


import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yedam.aop.bank.web.AccountVO;
import com.yedam.aop.bank.web.BankAPI;
import com.yedam.aop.bank.web.BankVO;

public class BankTest {
	
	//@Test
	public void getAccountList() {
		BankVO vo = new BankVO();
		List<AccountVO> list =  BankAPI.getAccountList(vo);
		//System.out.println(list);
	}
	
	//@Test
	public void getBalance() {
			BankVO vo = new BankVO();
			long balance = BankAPI.getBalanceInfo(vo);
			//System.out.println(balance);
	}
	
	//@Test
	public void getRealName() {
		BankAPI bankAPI = new BankAPI();
		String str = bankAPI.getRealName(new BankVO());
		//System.out.println("계좌실명 : " + str);
		
		assertEquals(str, "홍길동");
	}
	
	@Test
	public void getTransaction() {
		BankAPI bankAPI = new BankAPI();
		Map sttt = bankAPI.getTransaction(new BankVO());
		System.out.println(sttt);
	}
}