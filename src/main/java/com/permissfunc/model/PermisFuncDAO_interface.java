package com.permissfunc.model;

import java.util.*;

public interface PermisFuncDAO_interface {
	public void insertFunc(PermisFuncVO permisFuncVO);
    public void updateFunc(PermisFuncVO permisFuncVO);
    public void deleteFunc(Integer funcId);
    public PermisFuncVO findOneFunc(Integer funcId);
    public List<PermisFuncVO> getAllFunc();
}
