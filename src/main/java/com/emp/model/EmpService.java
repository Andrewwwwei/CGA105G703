package com.emp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;



public class EmpService {
	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpJDBCDAO();
	}

	public EmpVO addEmp(String empPw, String empName, String empDep,
			String jobTitle,String empIdnum, String empEmail,String empTel,
			Integer empStatus,java.sql.Date empHiredate) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpPw(empPw);
		empVO.setEmpName(empName);
		empVO.setEmpDep(empDep);
		empVO.setJobTitle(jobTitle);
		empVO.setEmpIdnum(empIdnum);
		empVO.setEmpEmail(empEmail);
		empVO.setEmpTel(empTel);
		empVO.setEmpStatus(empStatus);
		empVO.setEmpHiredate(empHiredate);	
		dao.insert(empVO);
		return empVO;
	}

	public EmpVO updateEmp(Integer empNo, String empPw, String empName, String empDep,
			String jobTitle,String empIdnum, String empEmail,String empTel,
			Integer empStatus,java.sql.Date empHiredate) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpNo(empNo);
		empVO.setEmpPw(empPw);
		empVO.setEmpName(empName);
		empVO.setEmpDep(empDep);
		empVO.setJobTitle(jobTitle);
		empVO.setEmpIdnum(empIdnum);
		empVO.setEmpEmail(empEmail);
		empVO.setEmpTel(empTel);
		empVO.setEmpStatus(empStatus);
		empVO.setEmpHiredate(empHiredate);
		dao.update(empVO);
		return empVO;
	}


	public void deleteEmp(Integer empNo) {
		dao.delete(empNo);
	}

	public EmpVO getOneEmp(Integer empNo) {
		return dao.findByPrimaryKey(empNo);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	
	public List<EmpVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public EmpVO getOneEmpEmail(String empEmail) {
		return dao.findByEmpEmail(empEmail);
	}

}
