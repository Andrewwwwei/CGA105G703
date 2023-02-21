package com.faq.model;

import java.util.List;

import com.emp.model.EmpVO;

public class FaqService {
	
	private FaqDAO_interface dao;
	
	public FaqService() {
		dao = new FaqJDBCDAO();
	}
	
	public FaqVO addFaq(String faqContent , String faqTitle) {
		FaqVO faqVO  = new FaqVO();
		faqVO.setFaqContent(faqContent);
		faqVO.setFaqTitle(faqTitle);
		dao.insert(faqVO);
		return faqVO;
	}
	
	public FaqVO updateFaq(String faqTitle,String faqContent ,Integer faqId) {
		FaqVO faqVO  = new FaqVO();
		faqVO.setFaqContent(faqContent);
		faqVO.setFaqTitle(faqTitle);
		faqVO.setFaqId(faqId);
		dao.update(faqVO);
		return faqVO;
		
	}
	
	public void deleteFaq(Integer faqID) {
		dao.delete(faqID);
	}
	
	public List<FaqVO> getAll(){
		return dao.getAll();
	}
	
	public FaqVO getOneFaq(Integer faqID) {
		return dao.findByPrimaryKey(faqID);
	}
	

}
