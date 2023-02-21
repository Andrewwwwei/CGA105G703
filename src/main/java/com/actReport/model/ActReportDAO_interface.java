package com.actReport.model;

import java.util.*;

public interface ActReportDAO_interface {
	      public void insert(ActReportVO actReportVO);
          public void update(ActReportVO actReportVO);
          public void delete(Integer activityReportId);
          public ActReportVO findByPrimaryKey(Integer activityReportId);
	      public List<ActReportVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
