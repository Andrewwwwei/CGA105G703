package com.locationPic.model;

import java.io.Serializable;

public class LocationPicVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer locPicId;
	private Integer locId;
	private byte[] locPic;
	
	public Integer getLocPicId() {
		return locPicId;
	}
	
	public void setLocPicId(Integer locPicId) {
		this.locPicId = locPicId;
	}
	
	public Integer getLocId() {
		return locId;
	}
	
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	
	public byte[] getLocPic() {
		return locPic;
	}
	
	public void setLocPic(byte[] locPic) {
		this.locPic = locPic;
	}
	

	
}
