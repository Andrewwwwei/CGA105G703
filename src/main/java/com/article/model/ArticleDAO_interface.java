package com.article.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.article_report.model.ArticleReportVO;

public interface ArticleDAO_interface {

	
	public ArticleVO findPostMan (Integer userId);
	
   // 以文章編號搜尋
	public ArticleVO findByPrimaryKey(Integer artId);
	
    // 會員個人已發表文章列表
	public List<ArticleVO> findByUserId(Integer userId);
	
	// 用文章標題&分類收尋
	public List<ArticleVO> findByPostType(Integer artTypeId);

	// 用關鍵字搜尋
	public List<ArticleVO> searchByKeyword(String keyword);
	
	//所有狀態為顯示的文章 狀態0為顯示
	public List<ArticleVO> getAllShowStatus();
	
	//單一文章搜尋
	public List<ArticleVO> findonePost(Integer artId);
	
	// 熱門文章
	public List<ArticleVO> findByTopArticle( );
	
	// 搜全部
	public List<ArticleVO> getAll();
	
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<ArticleVO> getPowerAll(Map<String, String[]> map);
	
	// 新增文章 
	public void insert(ArticleVO articleVO);

	// 一般會員修改文章
	public void update(ArticleVO articleVO);
	
   //	後台人員修改文章狀態、一般會員刪除文章
	public void updateArticleStatus(ArticleVO articleVO);
	
	// 後台人員刪除文章
	public void delete(Integer artId);

	// 增加觀看次數
	public void updateShowCount(Integer artId);





	
    //	多張照片再使用
    //	public void insertHasPic(ArticleVO articleVO, Collection<Part> Pic);  
	
	// 同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    //public void insert(ArticleVO articleVO, java.sql.Connection con);
}
