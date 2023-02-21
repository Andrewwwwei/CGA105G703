package com.trip.model;

import java.util.List;

public interface TripDAO_interface {
	public void insert(TripVO tripVO, Integer userId);
	public void update(TripVO tripVO);
	public void delete(Integer tripId);
	public TripVO findByPrimaryKey(Integer tripId);
	public List<TripVO> getAll(Integer userId);
}
