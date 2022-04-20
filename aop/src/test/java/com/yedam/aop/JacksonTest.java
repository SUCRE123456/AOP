package com.yedam.aop;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yedam.aop.notice.vo.NoticeVO;

public class JacksonTest {
	
	static ObjectMapper om;
	
	@BeforeClass
	public static void init() {
		om = new ObjectMapper();
		
	}
	
	@Test
	public void readTreeTest2() throws MalformedURLException, IOException {
		String infoUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=20212725"; // 20212725
		JsonNode json = om.readTree(new URL(infoUrl));
		//첫번째 감독 출력
		System.out.println(json);
		JsonNode movie = json.get("movieInfoResult")
								.get("movieInfo")
								.get("directors")
								.get(0)
								.get("peopleNm");
		System.out.println(movie);
	
	}
	
	@Test
	public void readTreddTest() throws MalformedURLException, IOException {
		String boxUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20220418";
		RestTemplate restTemplate = new RestTemplate();
		JsonNode json = restTemplate.getForObject(boxUrl,JsonNode.class);
		
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//첫번째 영화제목출력.
		JsonNode mvlist = json.get("boxOfficeResult")	
							  .get("dailyBoxOfficeList");
		MovieVO[] arr = om.treeToValue(mvlist, MovieVO[].class);					
							
		/*
		 * System.out.println(mv.get("movieNm") + ":" + mv.get("movieCd"));
		 * System.out.println(json);
		 */
		List<MovieVO> list = Arrays.asList(arr);
		System.out.println(list.get(0).getMovieNm() + "test");
	}
	
	
	
	
	@Test
	public void readTest() {
		String str = "{\"id\":0,\"title\":\"제목\",\"content\":\"test content\",\"wdate\":null,\"hit\":0}\r\n"
				+ "";
		try {
			NoticeVO vo = om.readValue(str, NoticeVO.class);
			assertEquals(vo.getTitle(), "제목");
			
			//System.out.println(vo.getTitle());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void writeTest() {
		NoticeVO vo = NoticeVO.builder()
							  .title("제목")
							  .content("test content")
							  .build();
		
		try {
			String str = om.writeValueAsString(vo);
			System.out.println(str);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}