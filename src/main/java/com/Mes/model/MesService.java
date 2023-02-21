package com.Mes.model;

import java.util.List;

public class MesService {
	private MesDAO_interface dao;

	public MesService() {
		dao = new MesJDBCDAO();
	}

	public MesVO addMesVO(Integer userId, String sendTitle,String sendContent,byte[] sendPic, java.sql.Timestamp sendTime, byte readMesseng) {
		MesVO mesVO = new MesVO();
		mesVO.setUserId(userId);
		mesVO.setSendTitle(sendTitle);
		mesVO.setSendContent(sendContent);
		mesVO.setSendPic(sendPic);
		mesVO.setSendTime(sendTime);
		mesVO.setReadMesseng(readMesseng);
		dao.insert(mesVO);
		return mesVO;
	}

	public MesVO updateMesVO(Integer userId,  String sendTitle, String sendContent,byte[] sendPic,java.sql.Timestamp sendTime, byte readMesseng,
			Integer mesId) {
		MesVO mesVO = new MesVO();
		mesVO.setUserId(userId);
		mesVO.setSendTitle(sendTitle);
		mesVO.setSendContent(sendContent);
		mesVO.setSendPic(sendPic);
		mesVO.setSendTime(sendTime);
		mesVO.setReadMesseng(readMesseng);
		mesVO.setMesId(mesId);
		dao.update(mesVO);
		return mesVO;
	}
	public MesVO updateReadMesVO(Integer userId) {
		MesVO mesVO = new MesVO();
		mesVO.setUserId(userId);
		dao.updateReadMes(mesVO);
		return mesVO;
	}
	public void deleteMes(Integer mesId) {
		dao.delete(mesId);
	}

	public MesVO getOneMesVO(Integer userId) {
		return dao.findByPrimaryKey(userId);
	}

	public List<MesVO> getAll(Integer userId) {
		return dao.getAll(userId);
	}
}
