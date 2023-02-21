package com.faq.model;
import java.util.*;

public interface FaqDAO_interface {
	public void insert(FaqVO faqVO);
    public void update(FaqVO faqVO);
    public void delete(Integer faqId);
    public FaqVO findByPrimaryKey(Integer faqId);
    public List<FaqVO> getAll();

}
