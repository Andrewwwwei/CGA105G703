package com.emp.model;
import java.util.*;
public interface EmpDAO_interface {
	  public void insert(EmpVO empVO);
      public void update(EmpVO empVO);
      public void delete(Integer empNo);
      public EmpVO findByPrimaryKey(Integer empNo);
      public List<EmpVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
  	  public List<EmpVO> getAll(Map<String, String[]> map);
      public EmpVO findByEmpEmail(String empEmail);
	
}
