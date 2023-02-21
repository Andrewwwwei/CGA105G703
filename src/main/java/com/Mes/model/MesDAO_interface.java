package com.Mes.model;
import java.util.*;
public interface MesDAO_interface {
	  public void insert(MesVO mesVO);
      public void update(MesVO mesVO);
      public void updateReadMes(MesVO mesVO);
      public void delete(Integer MES_ID);
      public MesVO findByPrimaryKey(Integer MES_ID);
      public List<MesVO> getAll(Integer MES_ID);
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<MesVO> getAll(Map<String, String[]> map); 

	
}
