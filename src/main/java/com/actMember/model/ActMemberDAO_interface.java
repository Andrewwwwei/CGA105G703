package com.actMember.model;

import java.util.*;

import com.act.model.ActVO;

public interface ActMemberDAO_interface {
	      public void insert(ActMemberVO actMemberVO);
          public void update(ActMemberVO actMemberVO);
          public void delete(Integer activityId);
          public ActMemberVO findByPrimaryKey(Integer userId , Integer activityId);
	      public List<ActMemberVO> getAll();
	      public void OneMemberInsert(ActMemberVO actMemberVO);
	      public List<ActMemberVO> getThisActMembers(Integer activityId);  //用揪團編號搜尋成員
	      public List<ActMemberVO> getByUserId(Integer userId);  //用成員的會員編號搜尋參加的揪團
}
