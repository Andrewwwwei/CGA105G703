package com.actMessage.model;

import java.util.*;

public interface ActMessageDAO_interface {
	      public void insert(ActMessageVO actMessageVO);
          public void update(ActMessageVO actMessageVO);
          public void delete(Integer messageId);
          public ActMessageVO findByPrimaryKey(Integer messageId );
	      public List<ActMessageVO> getAll();
	    

}
