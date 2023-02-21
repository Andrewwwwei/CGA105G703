package com.tripMember.model;

import java.util.List;

public class TripMemberService {
	private TripMemberDAO_interface dao;
	
	public TripMemberService() {
		dao = new TripMemberJDBCDAO();
	}
	
	public TripMemberVO addTripMbr(Integer tripId, Integer userId, Boolean isMbr) {
		
		TripMemberVO tripMbrVO = new TripMemberVO();
		tripMbrVO.setTripId(tripId);
		tripMbrVO.setUserId(userId);
		tripMbrVO.setIsMbr(isMbr);
		dao.insert(tripMbrVO);
		return tripMbrVO;
	}
	
	public TripMemberVO updateTripMbr(TripMemberVO tripMbrVO) {
		dao.update(tripMbrVO);
		return tripMbrVO;
	}
	
	public void deleteTripMbr(Integer tripMbrId) {
		dao.delete(tripMbrId);
	}
	
	public List<TripMemberVO> getTripMbr(Integer tripId) {
		return dao.findByTripId(tripId);
	}
	
	public TripMemberVO getOneTripMbr(Integer tripId, Integer userId){
		return dao.getOneMbr(tripId, userId);
	}
}
