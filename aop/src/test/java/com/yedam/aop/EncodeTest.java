package com.yedam.aop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {

	@Test
	public void endcode() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String result = encoder.encode("1111");
		String result2 = encoder.encode("1111");
		
		System.out.println(result);
		System.out.println(result2);
		
		assertTrue(encoder.matches("1111", result));

	}
}