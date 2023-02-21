package com.permission.model;

import java.util.List;
import java.util.Map;





public class PermissionService {
	
	private PermissionDAO_interface dao;

	public PermissionService() {
		dao = new PermissionJDBCDAO();
	}

	public PermissionVO addPermission(Integer funcId, Integer empNo) {

		PermissionVO permissionVO = new PermissionVO();

		permissionVO.setFuncId(funcId);
		permissionVO.setEmpNo(empNo);
		
		
		dao.insert(permissionVO);

		return permissionVO;
	}



	public void deletePermission(Integer empNo,Integer funcId ) {
		dao.delete( empNo,funcId );
	}

	public List<PermissionVO> getPermisByEmp(Integer empNo) {
		return dao.findPermisByEmp(empNo);
	}
	
	public List<PermissionVO> getPermisByFunc(Integer funcId) {
		return dao.findPermisByFunc(funcId);
	}
	
	public List<PermissionVO> getAll() {
		return dao.getAllPermis();
	}
	
	public List<PermissionVO> getAll(Map<String, String[]> map) {
		return dao.getAllPermis(map);
	}

}
