package com.roomPhoto.model;

import java.util.List;

public class RoomPhotoService {
	private RoomPhoto_interface dao;

	public RoomPhotoService() {
		dao = new RoomPhotoJDBCDAO();
//		dao = new RoomPhotoDAO();
	}

	public RoomPhotoVO addRoomPhoto(Integer roomId, byte[] roomPhoto) {

		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();

		roomPhotoVO.setRoomId(roomId);
		roomPhotoVO.setRoomPhoto(roomPhoto);
		dao.insert(roomPhotoVO);

		return roomPhotoVO;
	}

	//�w�d�� Struts 2 �� Spring MVC ��
	public void addRoomPhoto(RoomPhotoVO roomPhotoVO) {
		dao.insert(roomPhotoVO);
	}
	
	public RoomPhotoVO updateRoomPhoto(Integer roomPhotoId, Integer roomId, byte[] roomPhoto) {

		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		roomPhotoVO.setRoomPhotoId(roomPhotoId);
		roomPhotoVO.setRoomId(roomId);
		roomPhotoVO.setRoomPhoto(roomPhoto);
		dao.update(roomPhotoVO);

		return dao.findByPrimaryKey(roomPhotoId);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateRoomPhoto(RoomPhotoVO roomPhotoVO) {
		dao.update(roomPhotoVO);
	}

	public void deleteRoomPhoto(Integer roomPhotoId) {
		dao.delete(roomPhotoId);
	}

	public RoomPhotoVO getOneRoomPhoto(Integer roomPhotoId) {
		return dao.findByPrimaryKey(roomPhotoId);
	}

	public List<RoomPhotoVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomPhotoVO> getThisRoomPhoto(Integer roomId) {
		return dao.getARoom(roomId);
	}

}
