package com.yedam.aop.advice;

import org.aspectj.lang.JoinPoint;

//@Service //또는@component. 이렇게하면 빈등록 안해도됨. 
public class AfterAdvice {
	public void afterLog(JoinPoint jp, Object returnObj) {
			String method = jp.getSignature().getName();
			String result = returnObj != null ? returnObj.toString() : "";
		
		System.out.println("[사후처리] 로직 수행 후 동작" +
							method + " : " +
							result );
		
		
	}
}
