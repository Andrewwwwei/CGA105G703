package com.ColIti.model;

import java.util.List;

public class ColItiService {
private ColItiDAO_interface dao;
public ColItiService () {
	dao= new ColItiJDBCDAO();
}
public ColItiVO addColIti(Integer tripShareId , Integer userId){
	ColItiVO colItiVO = new ColItiVO();
	colItiVO.setTripShareId(tripShareId);
	colItiVO.setUserId(tripShareId);
	return colItiVO;
}
public ColItiVO updateColIti(Integer tripShareId , Integer userId	, Integer tripShareIdW , Integer userIdW){
	ColItiVO colItiVO = new ColItiVO();
	colItiVO.setTripShareId(tripShareId);
	colItiVO.setUserId(tripShareId);
	colItiVO.setTripShareIdW(tripShareId);
	colItiVO.setUserIdW(tripShareId);
	return colItiVO;
}
public void deleteColIti (Integer tripShareId , Integer userId){
	dao.delete(tripShareId, userId);
}
public ColItiVO getOneColIti(Integer tripShareId , Integer userId){
	return dao.findByPrimaryKey(userId);
}
public List<ColItiVO> getAll(Integer userId) {
	return dao.getAll(userId);
}
}
