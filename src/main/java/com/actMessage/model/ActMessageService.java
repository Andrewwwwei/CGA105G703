package com.actMessage.model;


import java.time.LocalDateTime;
import java.util.List;

public class ActMessageService {

	private ActMessageDAO_interface dao;

	public ActMessageService() {
		dao = new ActMessageJDBCDAO();
	}

	public ActMessageVO addActMessage(Integer activityId,Integer userId,
			String messageContent,LocalDateTime messageContentTime) {

		ActMessageVO actMessageVO = new ActMessageVO();

		actMessageVO.setActivityId(activityId);
		actMessageVO.setUserId(userId);
		actMessageVO.setMessageContent(messageContent);
		actMessageVO.setMessageContentTime(messageContentTime);
	
	
		
		dao.insert(actMessageVO);

		return actMessageVO;
	}

	public ActMessageVO updateActMessage(ActMessageVO actMessageVO) {
		
		dao.update(actMessageVO);
		return actMessageVO;
	}

	public void deletActMessage(Integer activityId) {
		dao.delete(activityId);
	}

	public ActMessageVO getOneActMessage(Integer messageId) {
		return dao.findByPrimaryKey(messageId);
	}

	public List<ActMessageVO> getAll() {
		return dao.getAll();
	}
	
	
		
	
}
