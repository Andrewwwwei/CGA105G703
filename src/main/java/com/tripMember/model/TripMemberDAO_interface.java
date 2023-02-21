package com.tripMember.model;

import java.sql.Connection;
import java.util.List;

public interface TripMemberDAO_interface {
	public void insert(TripMemberVO tripMemberVO);
	public void jointInsert(TripMemberVO tripMemberVO, Connection con);
	public void update(TripMemberVO tripMemberVO);
	public void delete(Integer tripMbrId);
	public List<TripMemberVO> findByTripId(Integer tripId);
	public TripMemberVO getOneMbr(Integer tripId, Integer userId);
}
