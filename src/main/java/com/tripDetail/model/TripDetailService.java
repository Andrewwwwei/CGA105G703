package com.tripDetail.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class TripDetailService {
	private TripDetailDAO_interface dao;
	
	public TripDetailService() {
		dao = new TripDetailJDBCDAO();
	}
	
	public TripDetailVO addTripDetail(Integer tripId, Integer LocId, Timestamp arrivalTime, Timestamp leaveTime) {
		
		TripDetailVO tripDetailVO = new TripDetailVO();
		tripDetailVO.setTripId(tripId);
		tripDetailVO.setLocId(LocId);
		tripDetailVO.setArrivalTime(arrivalTime);
		tripDetailVO.setLeaveTime(leaveTime);
		dao.insert(tripDetailVO);
		return tripDetailVO;
	}
	
	public void deleteTripDetail(Integer tripDetailId) {
		dao.delete(tripDetailId);
	}
	
	public List<TripDetailVO> getTrip_TripDetail(Integer tripId,Date date){
		return dao.getAll_ForTRIP(tripId, date);
	}

	public void deleteByDate(Integer tripId, Date date) {
		dao.deleteByDate(tripId, date);
	}

	public List<TripDetailVO> ajaxGetTripDetail(Integer tripId,String startDate,String endDate){
		return dao.getAllByForeignKey(tripId, startDate, endDate);
	}
}
