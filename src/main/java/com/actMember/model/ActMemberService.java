package com.actMember.model;

import java.time.LocalDateTime;
import java.util.List;

public class ActMemberService {

	private ActMemberDAO_interface dao;

	public ActMemberService() {
		dao = new ActMemberJDBCDAO();
	}

	public ActMemberVO addActMember(Integer activityId,String activityNotice,String evaluationContent,
			Integer evaluationScore,LocalDateTime evaluationTime,Integer memberStatus) {

		ActMemberVO actMemberVO = new ActMemberVO();

		actMemberVO.setActivityId(activityId);
		actMemberVO.setActivityNotice(activityNotice);
		actMemberVO.setEvaluationContent(evaluationContent);
		actMemberVO.setEvaluationScore(evaluationScore);
		actMemberVO.setEvaluationTime(evaluationTime);
		actMemberVO.setMemberStatus(memberStatus);
		
		dao.insert(actMemberVO);

		return actMemberVO;
	}
	
	public void addActMember(ActMemberVO actMemberVO) {
		dao.insert(actMemberVO);
	}
	public ActMemberVO addOneMember(Integer userId,Integer activityId,LocalDateTime evaluationTime) {
		
		ActMemberVO actMemberVO = new ActMemberVO();

		actMemberVO.setUserId(userId);
		actMemberVO.setActivityId(activityId);
		actMemberVO.setEvaluationTime(evaluationTime);
		
		dao.OneMemberInsert(actMemberVO);
		return actMemberVO;
	}
//	public ActMemberVO insertActMember(Integer activityId,Integer evaluationScore,
//			LocalDateTime evaluationTime,Integer memberStatus) {
//		
//		ActMemberVO actMemberVO = new ActMemberVO();
//		
//		dao.insert(actMemberVO);
//		
//		return actMemberVO;
//		
//	}
	
	public ActMemberVO updateActMember(ActMemberVO actMemberVO) {
		
		dao.update(actMemberVO);
		
		return actMemberVO;
	}

	public void deletActMember(Integer userId) {
		dao.delete(userId);
	}

	public ActMemberVO getOneActMember(Integer userId,Integer activityId) {
		return dao.findByPrimaryKey(userId,activityId);
	}

	public List<ActMemberVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActMemberVO> getThisActMembers(Integer activityId){
		return dao.getThisActMembers(activityId);
	}
	
	public List<ActMemberVO> getByUserId(Integer userId){
		return dao.getByUserId(userId);
	}
	
}
