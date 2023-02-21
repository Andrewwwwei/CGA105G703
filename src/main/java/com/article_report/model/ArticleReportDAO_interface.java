package com.article_report.model;

import java.util.*;

import com.article_reply.model.ArticleReplyVO;

public interface ArticleReportDAO_interface {

	//依照文章檢舉編號查詢
	public ArticleReportVO findByPrimaryKey(Integer artRpId);
    //所有文章檢舉
	public List<ArticleReportVO> getAll();
	// 新增文章檢舉
	public void insert(ArticleReportVO articlereportVO);
	// 修改文章檢舉
	public void update(ArticleReportVO articlereportVO);
	
	//會在首頁顯示的檢舉文章列表
	public List<ArticleReportVO> getAllShowStatus();
	
	
	//	修改文章檢舉狀態
	//	public void updateStatus(ArticleReportVO articlereportVO);
	//          public void delete(Integer artRpId);   沒有刪除
		

	
}
