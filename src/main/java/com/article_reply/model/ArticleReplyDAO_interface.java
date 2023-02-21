package com.article_reply.model;

import java.util.*;

public interface ArticleReplyDAO_interface {

	// 顯示單一文章所有留言
	public List<ArticleReplyVO> findArtPostMsg(Integer artId);

	// 依留言編號搜尋
	public ArticleReplyVO findByPrimaryKey(Integer artReplyId);

	// 所有留言
	public List<ArticleReplyVO> getAll();
    
	// 新增文章留言
	public void insert(ArticleReplyVO articleReplyVO);

	// 後台修改留言狀態
	public void updateReplyStatus(ArticleReplyVO articleReplyVO);
   
	
	public void update(ArticleReplyVO articleReplyVO);
   
//       // 修改留言內容 沒有
//       public void update(ArticleReplyVO articleReplyVO);

//       public void delete(Integer artReplyId);   沒有刪除

}
