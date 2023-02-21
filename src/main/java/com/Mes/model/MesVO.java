package com.Mes.model;
import java.sql.Date;
import java.sql.Timestamp;
public class MesVO implements java.io.Serializable{
	private Integer mesId  ;
	private Integer userId	;
	private String sendTitle;
	private String sendContent	;
	private byte[] sendPic;
	private Timestamp sendTime	;
	private byte readMesseng;
	
	public Integer getMesId() {
		return mesId;
	}
	public void setMesId(Integer mesId) {
		this.mesId = mesId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSendTitle() {
		return sendTitle;
	}
	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	public byte[] getSendPic() {
		return sendPic;
	}
	public void setSendPic(byte[] sendPic) {
		this.sendPic = sendPic;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public byte getReadMesseng() {
		return readMesseng;
	}
	public void setReadMesseng(byte readMesseng) {
		this.readMesseng = readMesseng;
	}
	
	
	
	
	
}
	

