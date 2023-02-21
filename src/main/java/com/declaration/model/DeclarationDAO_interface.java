package com.declaration.model;

import java.util.*;

import com.Users.model.UsersVO;

public interface DeclarationDAO_interface {
          public int insert(DeclarationVO decVO);
          public void deleteStatus(Integer declarationID);
          public List<DeclarationVO> findPage(Integer currPage);
          public DeclarationVO findByPrimaryKey(Integer declarationID);
          public DeclarationVO findByTitle(String title);
          public List<DeclarationVO> getAll();
          public List<UsersVO> getUsers(String input);
          public void update(DeclarationVO decVO);
          public void delete(Integer declarationID);
          public byte[] getPic(Integer declarationID);
}
