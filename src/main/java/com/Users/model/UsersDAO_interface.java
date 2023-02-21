package com.Users.model;

import java.util.*;
public interface UsersDAO_interface {
	  public void insert(UsersVO userVO);
      public void update(UsersVO userVO);
      public void delete(Integer USER_ID);
      public UsersVO findByPrimaryKey(Integer USER_ID);
      public List<UsersVO> getAll();
      
      //user功能新增
      public void updatePassword(UsersVO userVO);
      public void resetPassword(UsersVO userVO);
      public void updateUserStatusByUserAccount(UsersVO userVO);
      public void restorationUserStatusByUserAccount(UsersVO userVO);
      public boolean login(String USER_ACCOUNT,String USER_PASSWORD);
      public byte[] getPic (String USER_PIC) ;
      public UsersVO findByUserAccount(String USER_ACCOUNT);
      public UsersVO findByUserName(String USER_NAME);
      public List<UsersVO> getAllByReportTotalCount(Integer REPORT_TOTAL_COUNT);
      public void updateinfo(UsersVO userVO);
      public Integer findStatusByUserAccount(String USER_ACCOUNT);
      public Integer findIdByUserAccount(String USER_ACCOUNT);
      
      //room功能新增
      //同時新增訂單與明細，並更新會員
      public void update2(UsersVO usersVO, java.sql.Connection con);
	
}
