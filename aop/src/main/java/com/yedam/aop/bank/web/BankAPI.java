package com.yedam.aop.bank.web;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BankAPI {
	static String oob_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJNMjAyMjAwNzMwIiwic2NvcGUiOlsib29iIl0sImlzcyI6Imh0dHBzOi8vd3d3Lm9wZW5iYW5raW5nLm9yLmtyIiwiZXhwIjoxNjU4MTkwMTA0LCJqdGkiOiIyNzA3MzM0YS1hYTNkLTQ3ZGEtOTNiZC1iOTZiZTE4MjY3YmYifQ.dxAhYB9Z2VD9MGJlK2SzyT1DbtdFhEufm8cXnOvUBqs";
	static String use_org_code = "";
	public static long getBalanceInfo(BankVO vo) {
		long balance = 0;

		BankVO vo1 = new BankVO();
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num";

		String param = "bank_tran_id=M202200730U" + getSequence(); //
		param += "&tran_dtime=20220419115300"; // SimpleDateFormat
		param += "&fintech_use_num=" + vo1.getFintechUseNum();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + vo1.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null,
				headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, Map.class);
		Map map = response.getBody();
		balance = Long.valueOf((String) map.get("balance_amt"));
		return balance;
		// System.out.println(map.get("balance_amt"));
	}

	public static String getSequence() {

		long t = System.nanoTime();
		String result = String.valueOf(t);
		result = result.substring(result.length() - 9);

		//System.out.println(result);

		return result;

	}

	public static String getDate() {

		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = new Date();
		result = sdf.format(date);
		//System.out.println(result);
		return result;

	}

	public static List<AccountVO> getAccountList(BankVO vo) {
		ObjectMapper om;
		
		BankVO vo1 = new BankVO();
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";

		String param = "user_seq_no=" + vo.getUserSeqNo(); //
		param += "&include_cancel_yn=Y";
		param += "&sort_order=D";

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + vo1.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null,
				headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AccountList> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request,
				AccountList.class);

		
		// ObjectMapper

		// System.out.println(response.getBody());
		
		/*
		 * JsonNode json = om.readTree(response.getBody()); json = json.get
		 * str -> jsonNode(readtree) "res_list" 필드 ->jsonString -> AccountVO[] ->
		 * List<VO>
		 */
		  
		return response.getBody().getRes_list();
	}
	
	public String getRealName(BankVO vo) {
		String reqUrl = "https://testapi.openbanking.or.kr/v2.0/inquiry/real_name";
		Map<String, String> param = new HashMap<>();
		param.put("bank_tran_id","M202200730U"+getSequence());
		param.put("bank_code_std", "097");
		param.put("account_num", "1234567890123456");
		param.put("account_holder_info_type", " ");
		param.put("account_holder_info", "880101");
		param.put("tran_dtime", getDate());

		
		String jsonValue ="";
		ObjectMapper objectMapper = new ObjectMapper();
				
		try {
			jsonValue = objectMapper.writeValueAsString(param);  //json으로 변환  
			//System.out.println(jsonValue);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Content-Type","application/json; charset=UTF-8");
		headers.set("Authorization", "Bearer " + oob_token);
		HttpEntity<String> request = new HttpEntity<String>(jsonValue, headers);

		RestTemplate restTemplate = new RestTemplate();
		Map response = restTemplate.postForObject(reqUrl, request, Map.class);
		//System.out.println(response);
		String realName = (String) response.get("account_holder_name");  //이름출력
		
		return realName;
	}
	public static Map getTransaction(BankVO vo) {
		String reqUrl ="https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";

		//파라미터 setting
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("bank_tran_id", "M202200730"+"U"+getSequence());
		map.add("fintech_use_num", "120220073088941057195168");
		map.add("inquiry_type", "A");
		map.add("inquiry_base", "D");
		map.add("from_date", "20220415");
		map.add("to_date", "20220420");
		map.add("sort_order", "D");
		map.add("tran_dtime", getDate());
		//MultiValueMap = > queryString 으로 변환
		//String param = "";
		URI uri = UriComponentsBuilder.fromUriString(reqUrl)
		        .queryParams(map)	
		        .build().encode()
		        .toUri();
		
		//header setting
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + vo.getAccessToken());
		
		//request 생성 (파라미터와 헤더를 지정)
		HttpEntity<MultiValueMap<String, String>> request = 
				new HttpEntity<MultiValueMap<String, String>>(null, headers);
		
		
		//RestTemplate을 이용하여 request를 보내고 response를 받고, json 결과를 객체로 파싱
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, request,
				Map.class);
		
		System.out.println(response);
		return response.getBody();
	}
	
	public static TokenVO getToken(String authcode) {
		String reqUrl ="https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";
		BankVO vo = new BankVO();
		
		//파라미터 setting
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("code", "m6fYF8CrSQqfAwTvFWDtwYj9X9fmtR");
		map.add("client_id", "2e27b786-1f6c-46e1-8e9f-c3b8f6ba51c5");
		map.add("client_secre", "33366f0b-294d-4462-9800-f16ab7f588bf");
		map.add("redirect_uri", "http://localhost:800/html/callback.html");
		map.add("grant_type", "m6fYF8CrSQqfAwTvFWDtwYj9X9fmtR");
		
		//MultiValueMap = > queryString 으로 변환
		//String param = "";
		
		
		//header setting
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + vo.getAccessToken());
		headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		
		//request 생성 (파라미터와 헤더를 지정)
		HttpEntity<MultiValueMap<String, String>> request = 
				new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		
		//RestTemplate을 이용하여 request를 보내고 response를 받고, json 결과를 객체로 파싱
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.exchange(reqUrl, HttpMethod.POST, request,
				Map.class);
		
		System.out.println(response);
		return response.getBody();
	}
}