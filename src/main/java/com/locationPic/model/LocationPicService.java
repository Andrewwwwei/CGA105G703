package com.locationPic.model;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

public class LocationPicService {
	private LocationPicDAO_interface dao ;
	
	public LocationPicService() {
		dao = new LocationPicJDBCDAO();
	}
	
	public LocationPicVO addLocPic(Integer LocId, Collection<Part> locPic) {
		
		LocationPicVO locPicVO = new LocationPicVO();
		locPicVO.setLocId(LocId);
		dao.insert(locPicVO,locPic);
		
		return locPicVO;
	}
	
	public void deleteLocPic(Integer locPicId) {
		dao.delete(locPicId);
	}
	
	public List<LocationPicVO> getLocPic(Integer locId) {
		LocationPicVO locPicVO = new LocationPicVO();
		locPicVO.setLocId(locId);
		return dao.findByForeignKey(locId);
	}

	public LocationPicVO getOnePic(Integer locPicId) {
		return dao.findByPrimaryKey(locPicId);
	}
}

