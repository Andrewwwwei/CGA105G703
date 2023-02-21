package com.permission.model;

import java.util.List;
import java.util.Map;



public interface PermissionDAO_interface {
	
    public void insert(PermissionVO permissionVO);
    public void delete(Integer funcIdInteger ,Integer empNo);
    public List<PermissionVO>findPermisByEmp(Integer empNo);
    public List<PermissionVO>findPermisByFunc(Integer funcId);
    public List<PermissionVO> getAllPermis();
    public List<PermissionVO> getAllPermis(Map<String, String[]> map);
   

}
