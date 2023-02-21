package com.room.model;

import java.util.List;


public class RoomService {

	private Room_interface dao;

	public RoomService() {
		dao = new RoomJDBCDAO();
//		dao = new RoomDAO();
	}

	public RoomVO addRoom(Integer venId, String roomName, Integer roomAmount, Integer roomPeople,
			Integer roomPrice, Integer roomArea, String roomIntro, Integer roomUpdate, Integer roomView,
			Integer breakfast, Integer airCondotioner, Integer wifi, Integer television, Integer safebox,
			Integer fridge, Integer waterBoiler, Integer bathroom, Integer toiletries) {

		RoomVO roomVO = new RoomVO();

		roomVO.setVenId(venId);
		roomVO.setRoomName(roomName);
		roomVO.setRoomAmount(roomAmount);
		roomVO.setRoomPeople(roomPeople);
		roomVO.setRoomPrice(roomPrice);
		roomVO.setRoomArea(roomArea);
		roomVO.setRoomIntro(roomIntro);
		roomVO.setRoomUpdate(roomUpdate);
		roomVO.setRoomView(roomView);
		roomVO.setBreakfast(breakfast);
		roomVO.setAirCondotioner(airCondotioner);
		roomVO.setWifi(wifi);
		roomVO.setTelevision(television);
		roomVO.setSafebox(safebox);
		roomVO.setFridge(fridge);
		roomVO.setWaterBoiler(waterBoiler);
		roomVO.setBathroom(bathroom);
		roomVO.setToiletries(toiletries);
		dao.insert(roomVO);

		return roomVO;
	}

	//�w�d�� Struts 2 �� Spring MVC ��
	public void addRoom(RoomVO roomVO) {
		dao.insert(roomVO);
	}
	
	public RoomVO updateRoom(Integer roomId,Integer venId, String roomName, Integer roomAmount, Integer roomPeople,
			Integer roomPrice, Integer roomArea, String roomIntro, Integer roomUpdate, Integer roomView,
			Integer breakfast, Integer airCondotioner, Integer wifi, Integer television, Integer safebox,
			Integer fridge, Integer waterBoiler, Integer bathroom, Integer toiletries) {

		RoomVO roomVO = new RoomVO();

		roomVO.setRoomId(roomId);
		roomVO.setVenId(venId);
		roomVO.setRoomName(roomName);
		roomVO.setRoomAmount(roomAmount);
		roomVO.setRoomPeople(roomPeople);
		roomVO.setRoomPrice(roomPrice);
		roomVO.setRoomArea(roomArea);
		roomVO.setRoomIntro(roomIntro);
		roomVO.setRoomUpdate(roomUpdate);
		roomVO.setRoomView(roomView);
		roomVO.setBreakfast(breakfast);
		roomVO.setAirCondotioner(airCondotioner);
		roomVO.setWifi(wifi);
		roomVO.setTelevision(television);
		roomVO.setSafebox(safebox);
		roomVO.setFridge(fridge);
		roomVO.setWaterBoiler(waterBoiler);
		roomVO.setBathroom(bathroom);
		roomVO.setToiletries(toiletries);
		dao.update(roomVO);

		return dao.findByPrimaryKey(roomId);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateRoom(RoomVO roomVO) {
		dao.update(roomVO);
	}

	public void deleteRoom(Integer roomId) {
		dao.delete(roomId);
	}

	public RoomVO getOneRoom(Integer roomId) {
		return dao.findByPrimaryKey(roomId);
	}

	public List<RoomVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomVO> getAllByVen(Integer venId) {
		return dao.getAllByVen(venId);
	}
}
