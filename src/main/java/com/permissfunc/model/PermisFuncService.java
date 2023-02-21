package com.permissfunc.model;

import java.util.List;

import com.emp.model.EmpDAO_interface;
import com.emp.model.EmpJDBCDAO;
import com.emp.model.EmpVO;

public class PermisFuncService {
	private PermisFuncDAO_interface dao;

	public PermisFuncService() {
		dao = new PermisFuncJDBCDAO();
	}

	public PermisFuncVO addPermisFunc(String funcName) {

		PermisFuncVO permisFuncVO = new PermisFuncVO();

		permisFuncVO.setFuncName(funcName);
	

		dao.insertFunc(permisFuncVO);

		return permisFuncVO;
	}

	public PermisFuncVO updatePermisFunc(String funcName,Integer funcId) {

		PermisFuncVO permisFuncVO = new PermisFuncVO();

		permisFuncVO.setFuncName(funcName);
		permisFuncVO.setFuncId(funcId);
		dao.updateFunc(permisFuncVO);

		return permisFuncVO;
	}

	public void deletePermisFunc(Integer funcId) {
		dao.deleteFunc(funcId);
	}

	public PermisFuncVO getOnePermisFunc(Integer funcId) {
		return dao.findOneFunc(funcId);
	}
	


	public List<PermisFuncVO> getAllPermisFunc() {
		return dao.getAllFunc();
	}
}
