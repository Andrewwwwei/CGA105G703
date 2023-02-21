package com.ColIti.model;
import java.util.*;
public interface ColItiDAO_interface {
	  public void insert(ColItiVO colItiVO);
      public void update(ColItiVO colItiVO);
      public void delete(Integer TRIP_SHARE_ID ,Integer USER_ID);
      public ColItiVO findByPrimaryKey(Integer USER_ID);
      public List<ColItiVO> getAll(Integer USER_ID);
     
//    public List<ColItiVO> getAll(Map<String, String[]> map); 

	
}
