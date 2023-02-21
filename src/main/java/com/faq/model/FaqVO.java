package com.faq.model;

public class FaqVO implements java.io.Serializable{
	private Integer faqId;
	private String faqContent;
	private String faqTitle;
	
	public Integer getFaqId() {
		return faqId;
	}
	public void setFaqId(Integer faqId) {
		this.faqId = faqId;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
}
