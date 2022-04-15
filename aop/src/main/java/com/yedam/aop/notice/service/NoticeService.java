package com.yedam.aop.notice.service;

import java.util.List;

import com.yedam.aop.notice.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> noticeSelectList(int page);
	List<NoticeVO> noticeSearchList(String key, String val);
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
}
