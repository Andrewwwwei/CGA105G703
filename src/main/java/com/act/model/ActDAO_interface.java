package com.act.model;

import java.util.*;


public interface ActDAO_interface {
	      public void insert(ActVO actVO);
          public void update(ActVO actVO);
          public void delete(Integer activityId);
          public ActVO findByPrimaryKey(Integer activityId);
	      public List<ActVO> getAll();
		  public List<ActVO> getOneTrip(Integer tripId);
		  public List<ActVO> getLeaderAct(Integer userId);//用團主的會員編號搜尋開的揪團
		  public ActVO getOneActContent(Integer activityId);
		  public List<ActVO> getImgs(Integer activityId);
//		  public List<ActVO> getByActivityLocation(String activityTitle);
}
