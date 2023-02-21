package com.roomPhoto.model;

public class RoomPhotoVO implements java.io.Serializable{
	private Integer roomPhotoId;
	private Integer roomId;
	private byte[] roomPhoto;
	
	public Integer getRoomPhotoId() {
		return roomPhotoId;
	}
	public void setRoomPhotoId(Integer roomPhotoId) {
		this.roomPhotoId = roomPhotoId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public byte[] getRoomPhoto() {
		return roomPhoto;
	}
	public void setRoomPhoto(byte[] roomPhoto) {
		this.roomPhoto = roomPhoto;
	}
	
	
}
