package com.trip.model;

import java.sql.Date;
import java.util.List;

public class TripService {
	private TripDAO_interface dao;
	
	public TripService() {
		dao = new TripJDBCDAO();
	}
	
	public TripVO addTrip(String tripName, Date startDate, Date endDate, byte[] coverPic, String note, Integer userId) {
		
		TripVO tripVO = new TripVO();
		tripVO.setTripName(tripName);
		tripVO.setStartDate(startDate);
		tripVO.setEndDate(endDate);
		tripVO.setCoverPic(coverPic);
		tripVO.setNote(note);
		dao.insert(tripVO, userId);
		return tripVO;
		
	}
	
	public TripVO updateTrip(String tripName, Date startDate, Date endDate, byte[] coverPic, String note,Integer tripId) {
		TripVO tripVO = new TripVO();
		tripVO.setTripName(tripName);
		tripVO.setStartDate(startDate);
		tripVO.setEndDate(endDate);
		tripVO.setCoverPic(coverPic);
		tripVO.setNote(note);
		tripVO.setTripId(tripId);
		dao.update(tripVO);
		return tripVO;
	}
	
	public void deleteTrip(Integer tripId) {
		dao.delete(tripId);
	}
	
	public TripVO getOneTrip(Integer tripId) {
		return dao.findByPrimaryKey(tripId);
	}
	
	public List<TripVO> getAll(Integer userId){
		return dao.getAll(userId);
	}
}
