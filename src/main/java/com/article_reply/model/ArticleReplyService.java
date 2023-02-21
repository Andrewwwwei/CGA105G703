package com.article_reply.model;

import java.util.List;
import java.util.Set;

public class ArticleReplyService {

	private ArticleReplyDAO_interface dao;

	public ArticleReplyService() {
		dao = new ArticleReplyJDBCDAO();
	}

	// 顯示單一文章所有留言
	public List<ArticleReplyVO> getOnePostAllMsg(Integer artId) {
		return dao.findArtPostMsg(artId);
	}

	// 依留言編號搜尋
	public ArticleReplyVO getOneArticleReply(Integer artReplyId) {
		return dao.findByPrimaryKey(artReplyId);
	}

	// 所有留言
	public List<ArticleReplyVO> getAll() {
		return dao.getAll();
	}

	// 新增文章留言
	public ArticleReplyVO addArticleReply(Integer artId, Integer userId, Integer replyStatus, String replyContent) {

		ArticleReplyVO articleReplyVO = new ArticleReplyVO();
		articleReplyVO.setArtId(artId);
		articleReplyVO.setUserId(userId);
		articleReplyVO.setReplyStatus(replyStatus);
		articleReplyVO.setReplyContent(replyContent);
		dao.insert(articleReplyVO);
		return articleReplyVO;
	}

	// 後台修改留言狀態
	public ArticleReplyVO updateArtReplyStatus( Integer artReplyId, Integer replyStatus) {

		ArticleReplyVO articleReplyVO = new ArticleReplyVO();
		articleReplyVO.setArtReplyId(artReplyId);
		articleReplyVO.setReplyStatus(replyStatus);
		dao.updateReplyStatus(articleReplyVO);
		return dao.findByPrimaryKey(artReplyId);
	}
}
