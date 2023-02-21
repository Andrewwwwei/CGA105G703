package com.stock.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class StockService {
	private Stock_interface dao;

	public StockService() {
		dao = new StockJDBCDAO();
//		dao = new StockDAO();
	}

	public StockVO addStock(Integer roomId, LocalDate stayDate, Integer roomRest) {

		StockVO stockVO = new StockVO();
		stockVO.setRoomId(roomId);
		stockVO.setStayDate(stayDate);
		stockVO.setRoomRest(roomRest);
		dao.insert(stockVO);

		return stockVO;
	}

	//�w�d�� Struts 2 �� Spring MVC ��
	public void addStock(StockVO stockVO) {
		dao.insert(stockVO);
	}
	
	public StockVO updateStock(Integer roomId, LocalDate stayDate, Integer roomRest, Connection con) {

		StockVO stockVO = new StockVO();

		stockVO.setRoomId(roomId);
		stockVO.setStayDate(stayDate);
		stockVO.setRoomRest(roomRest);
		dao.update(stockVO, con);

		return dao.findByPrimaryKey(roomId, stayDate);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateStock(StockVO stockVO) {
		dao.update(stockVO);
	}
	public void updateStock(StockVO stockVO, Connection con) {
		dao.update(stockVO, con);
	}

	public void deleteStock(Integer roomId, LocalDate stayDate) {
		dao.delete(roomId, stayDate);
	}

	public StockVO getOneStock(Integer roomId, LocalDate stayDate) {
		return dao.findByPrimaryKey(roomId, stayDate);
	}

	public List<StockVO> getAll() {
		return dao.getAll();
	}
	public List<StockVO> getByRoomId(Integer roomId) {
		return dao.getByRoomId(roomId);
	}

}
