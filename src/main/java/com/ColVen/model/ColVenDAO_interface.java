package com.ColVen.model;
import java.util.*;
public interface ColVenDAO_interface {
	  public void insert(ColVenVO colVenVO);
      public void update(ColVenVO colVenVO);
      public void delete(Integer VEN_ID,Integer USER_ID);
      public ColVenVO findByPrimaryKey(Integer VEN_ID,Integer USER_ID);
      public List<ColVenVO> getAll(Integer USER_ID);
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<ColVenVO> getAll(Map<String, String[]> map); 

	
}
