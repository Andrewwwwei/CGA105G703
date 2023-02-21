package com.vendorPic.model;

public class VendorPicVO implements java.io.Serializable{
	private Integer venPicid;
	private Integer venId;
	private byte[] venPic;
	public Integer getVenPicid() {
		return venPicid;
	}
	public Integer getVenId() {
		return venId;
	}
	public byte[] getVenPic() {
		return venPic;
	}
	public void setVenPicid(Integer venPicid) {
		this.venPicid = venPicid;
	}
	public void setVenId(Integer venId) {
		this.venId = venId;
	}
	public void setVenPic(byte[] venPic) {
		this.venPic = venPic;
	}
	
}
