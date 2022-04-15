package com.yedam.aop.advice;

import org.aspectj.lang.JoinPoint;

public class LogAdvice {
	
	public void printLog(JoinPoint jp) {
		//메서드명
		String methodName = jp.getSignature().getName();
		
		//인수(argument)
		Object[] args = jp.getArgs();
		Object arg1 = (args != null ? args[0] : "");
		System.out.println("[사전처리] beforeLog"+methodName +"\n arg:" + arg1);
		
	}
}