package com.yedam.aop.movie.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieApi {
	
	static ObjectMapper om = new ObjectMapper();
//1위 영화
	public static String firstMovie(String targetDt) throws MalformedURLException, IOException {
		String name = "";
		String boxUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt="+targetDt;
		JsonNode json = om.readTree(new URL(boxUrl));
		//첫번째 영화제목출력.
		JsonNode mv = json.get("boxOfficeResult")
							.get("dailyBoxOfficeList")
							.get(0);
		
		//System.out.println(mv.get("movieNm") + ":" + mv.get("movieCd"));
		//System.out.println(json);
		name =	mv.get("movieNm").asText();
		
		return name;
	}
//영화감독
	
//영화리스트
}
