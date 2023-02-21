package com.article_report.model;

import java.util.List;

import com.article.model.ArticleVO;

public class ArticleReportService {

	private ArticleReportDAO_interface dao;

	public ArticleReportService() {
		dao = new ArticleReportJDBCDAO();
	}
	
	// 所有文章檢舉
	public List<ArticleReportVO> getAll() {
		return dao.getAll();
	}
	
	// 顯示正確顯示的檢舉文章 JOIN用
	public List<ArticleReportVO> getAllShowStatus() {
		return dao.getAllShowStatus();
	}
	
	
	// 依照文章檢舉編號查詢
	public ArticleReportVO getOneArticleReport(Integer artRpId) {
		return dao.findByPrimaryKey(artRpId);
	}
	// 新增文章檢舉
	public ArticleReportVO addArticleReport(Integer userId,Integer rpReason, String rpContent, Integer rpStatus,Integer artId) {
		ArticleReportVO articleReportVO = new ArticleReportVO();
		articleReportVO.setUserId(userId);
		articleReportVO.setRpReason(rpReason);
		articleReportVO.setRpContent(rpContent);
		articleReportVO.setRpStatus(rpStatus);
		articleReportVO.setArtId(artId);
		dao.insert(articleReportVO);
		return articleReportVO;
	}

	// 一般會員修改文章
	public void updateArtRep(ArticleReportVO articleReportVO) {
			dao.update(articleReportVO);
		} 
	
   // 會員新增檢舉
	public void addArticleReport (ArticleReportVO articleReportVO) {
		dao.insert(articleReportVO);
	} 
//	public void deleteArticleReport(Integer artRpId) {  沒有刪除
//		dao.delete(artRpId);
//	}

}
