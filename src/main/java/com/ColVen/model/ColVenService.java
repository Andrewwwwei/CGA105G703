package com.ColVen.model;

import java.util.List;

public class ColVenService {
	private ColVenDAO_interface dao;

	public ColVenService() {
		dao = new ColVenJDBCDAO();
	}

	public ColVenVO addColVen(Integer venId, Integer userId) {

		ColVenVO colVenVO = new ColVenVO();
		colVenVO.setVenId(venId);
		colVenVO.setUserId(userId);
		dao.insert(colVenVO);
		return colVenVO;
	}

	public ColVenVO updateColVen(Integer venId, Integer userId, Integer venIdW, Integer userIdW) {

		ColVenVO colVenVO = new ColVenVO();
		colVenVO.setVenId(venId);
		colVenVO.setUserId(userId);
		colVenVO.setVenIdW(venIdW);
		colVenVO.setUserIdW(userIdW);
		dao.update(colVenVO);
		return colVenVO;
	}

	public void deleteColVen(Integer venId, Integer userId) {
		dao.delete(venId, userId);
	}
	public  ColVenVO getOneColVen(Integer venId, Integer userId) {
		return dao.findByPrimaryKey(venId, userId);
	}
	public List<ColVenVO> getAll(Integer USER_ID){
		return dao.getAll(USER_ID);
	}
}
