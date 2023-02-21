package com.article.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Part;

import com.article_report.model.ArticleReportService;
import com.article_report.model.ArticleReportVO;

public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleJDBCDAO();
	}

	// 依照文章編號搜尋
	public ArticleVO getOneArticle(Integer artId) {
		return dao.findByPrimaryKey(artId);
	}

	// 熱門文章
	public List<ArticleVO> findTopUserAllPost() {
		return dao.findByTopArticle();
	}

	// 會員個人已發表文章列表
		public List<ArticleVO> getAllShowStatus() {
			return dao.getAllShowStatus();
		}
		
	// 會員個人已發表文章列表 
	public List<ArticleVO> findMyPost(Integer userId) {
		return dao.findByUserId(userId);
	}

	// 依照文章類別搜尋
	public List<ArticleVO> findPostType(Integer artTypeId) {
		return dao.findByPostType(artTypeId);
	}

	// 依照關鍵字類別搜尋
	public List<ArticleVO> searchByKeyword(String keyword) {
		return dao.searchByKeyword(keyword);
	}

	// 綜合查詢
	public List<ArticleVO> getAllPowerSearch(Map<String, String[]> map) {
		return dao.getPowerAll(map);
	}

	// 查全部
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

	// 新增文章
	public ArticleVO addArticle(Integer userId, Integer artTypeId, String artTitle, String artContent, byte[] artPic) {

		ArticleVO articleVO = new ArticleVO();
		articleVO.setUserId(userId);
		articleVO.setArtTypeId(artTypeId);
		articleVO.setArtTitle(artTitle);
		articleVO.setArtContent(artContent);
		articleVO.setArtPic(artPic);
		dao.insert(articleVO);
		return articleVO;
	}

	// 修改文章
	public ArticleVO updateArticle(Integer artId, Integer userId, Integer artTypeId, String artTitle, String artContent,
			Integer artLikesCount, Integer artShowCount, Integer artStatus) {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArtId(artId);
		articleVO.setUserId(userId);
		articleVO.setArtTypeId(artTypeId);
		articleVO.setArtTitle(artTitle);
		articleVO.setArtContent(artContent);
		articleVO.setArtLikesCount(artLikesCount);
		articleVO.setArtShowCount(artShowCount);
		articleVO.setArtStatus(artStatus);
		dao.update(articleVO);
		return articleVO;
	}

	public void updateArticle(ArticleVO articleVO) {
		dao.update(articleVO);
	}

	// 修改文章狀態
	public ArticleVO updateArticleStatus(Integer artId, Integer artTypeId, Integer artStatus) {

		ArticleVO articleVO = new ArticleVO();
		articleVO.setArtId(artId);
		articleVO.setArtTypeId(artTypeId);
		articleVO.setArtStatus(artStatus);
		dao.updateArticleStatus(articleVO);
		return dao.findByPrimaryKey(artId);
	}

	// 觀看次數
	public void updateShowCount(Integer artId) {
		dao.updateShowCount(artId);
	}

	// 刪除文章 
	public void deleteArticle(Integer artId) {
		dao.delete(artId);
	}

	// 單一文章找用戶 
	public ArticleVO findPostMan(Integer userId) {
		return dao.findPostMan(userId);
	}
	
	
//  新增多張照片文章
//	public ArticleVO addArticle(Integer userId, Integer artTypeId, String artTitle,    
//			String artContent,Collection<Part> pic) {
//
//		ArticleVO articleVO = new ArticleVO();
//	    articleVO.setUserId(userId);
//	    articleVO.setArtTypeId(artTypeId);
//		articleVO.setArtTitle(artTitle);
//		articleVO.setArtContent(artContent);
//		dao.insertHasPic(articleVO,pic );
//		return articleVO;
//	}
	


}
