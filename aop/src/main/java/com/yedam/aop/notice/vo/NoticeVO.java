package com.yedam.aop.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*@Getter
@Setter
@ToString*/
@Data  //위 3개랑 대체가능.//
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class NoticeVO {
	private int id;
	private String title;
	private String content;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date wdate;
	private int hit;
	@JsonIgnore private String fileName;
	@JsonIgnore private String uuidFile;
}
