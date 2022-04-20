package com.yedam.aop.bank.web;

import lombok.Data;

@Data
public class AccountVO {
	private String bank_name;
	private String account_num_masked;
	private String account_type;
	
}
