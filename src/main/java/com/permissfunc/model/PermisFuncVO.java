package com.permissfunc.model;

public class PermisFuncVO implements java.io.Serializable{
	private Integer funcId;
	private String funcName;
	
	public Integer getFuncId() {
		return funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	
}
