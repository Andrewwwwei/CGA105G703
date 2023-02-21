package com.locationPic.model;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

public interface LocationPicDAO_interface {
	public void insert(LocationPicVO locationPicVO,Collection<Part> locPic);
	public void insertHasPic(LocationPicVO locationPicVO, Connection con);
	public void delete(Integer locPicId);
	public LocationPicVO findByPrimaryKey(Integer locPicId);
	public List<LocationPicVO> findByForeignKey(Integer locId);
}
