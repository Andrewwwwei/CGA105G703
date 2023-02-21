package com.act.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActService {

	private ActDAO_interface dao;

	public ActService() {
		dao = new ActJDBCDAO();
	}

	public ActVO addAct(/*Integer tripId,*/ Integer userId, String activityTitle,
			String activityContent, Integer currentNumber, Integer maxPeople,
			java.sql.Date registrationTime,java.sql.Date registrationEnd,java.sql.Date tripStart,
			java.sql.Date tripEnd,byte[] activityTheCover,Integer activityState) {

		ActVO actVO = new ActVO();

//		actVO.setTripId(tripId);
		actVO.setUserId(userId);
		actVO.setActivityTitle(activityTitle);
		actVO.setActivityContent(activityContent);
		actVO.setCurrentNumber(currentNumber);
		actVO.setMaxPeople(maxPeople);
		actVO.setRegistrationTime(registrationTime);
		actVO.setRegistrationEnd(registrationEnd);
		actVO.setTripStart(tripStart);
		actVO.setTripEnd(tripEnd);
		actVO.setActivityTheCover(activityTheCover);
		actVO.setActivityState(activityState);
		
		dao.insert(actVO);

		return actVO;
	}

	public ActVO updateAct(ActVO actVO) {
		
		dao.update(actVO);

		return actVO;
	}

	public void deletAct(Integer activityId) {
		dao.delete(activityId);
	}

	public ActVO getOneAct(Integer activityId) {
		return dao.findByPrimaryKey(activityId);
	}

	public List<ActVO> getAll() {
		return dao.getAll();
	}
	public List<ActVO> getOneTrip(Integer tripId) {
		return dao.getOneTrip(tripId);
	}
	
	public ActVO getOneActContent(Integer activityId) {
		return dao.getOneActContent(activityId);
	}
	
	public List<ActVO> getLeaderAct(Integer userId) {
		return dao.getLeaderAct(userId);
	}
	
	public List<ActVO> getImgs(Integer activityId){
		return dao.getImgs(activityId);
	}
//	public List<ActVO> getActivityLocation(String activityLocation){
//		return dao.getByActivityLocation(activityLocation);
//	}
//	@Override
//	public AvtVO getAllActivityTitle{ActVO actVO}{
//		if()
//		
//	}
	
	//	getAll(Integer location){
//		if() {
//			GET_ALL_STMT + "where title like %北部%"
//		}
//		if location ==1 {
//			GET_ALL_STMT + "where title like %中部%"
//
//		}
//		
//	}
	

//	<%
//		Int location = request.getParameter("location");
//	ActService actSvc = new
//		List list = actSvc.getlistfromlocation(location);
//		
//		pageScope.setAttribute("list",list);
//	
//	%>
//	<c:forEach var="actVO" items="${list}" >
//		<div>
//			<p>title : ${actVO.title}</p>
//			<p>contnet : ${actVO.content}</p>
//			
//		</div>
//	</c:forEach>
//	${list}
//	
//	public List<ActVO> getListFromLocation(Integer location){
//		if(location == 0) {
//			List<ActVO> listAll = dao.getAll();
//			List<ActVO> listNorth = new ArrayList<ActVO>();
//			for(ActVO actVO : listAll) {
//				if("North".equals(actVO.getLocation())) {
//					listNorth.add(actVO);
//				}
//			}
//			
//			return listNorth;
//		}
//		if(location == 1) {
//			
//		}
//		if(location == 2) {
//			
//		}
//		return null;
//	}
}
