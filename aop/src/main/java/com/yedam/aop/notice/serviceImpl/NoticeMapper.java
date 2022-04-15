package com.yedam.aop.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.aop.notice.vo.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList(int page);
	List<NoticeVO> noticeSearchList(@Param("key") String key, @Param("val") String val);
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
}
