package com.declaration.model;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class DeclarationVO implements java.io.Serializable ,  Comparable<DeclarationVO>{
	private static final long serialVersionUID = 1L;
	private Integer declarationID;
	private String title;
	private String content;
	private byte[] pic;
	private Integer status;
	private Date date;
	private String announceTime;
	private String announceList;
	
	public String getAnnounceList() {
		return announceList;
	}
	public void setAnnounceList(String announceList) {
		this.announceList = announceList;
	}
	public Integer getDeclarationID() {
		return declarationID;
	}
	public void setDeclarationID(Integer declarationID) {
		this.declarationID = declarationID;
	}
	public String getAnnounceTime() {
		return announceTime;
	}
	public void setAnnounceTime(String announceTime) {
		this.announceTime = announceTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	
	@Override
	public String toString() {
		return "Declaration ID : " + declarationID + "\nTitle : " + title + "\nContent :" + content + "\nStatus : " + status + "\nDate : "+date + "\n ---------------------------------------";
	}
	// for sort
	@Override 
	public int compareTo(DeclarationVO decVO){
        if (this.declarationID > decVO.declarationID) {
            return 1;
        }
        else if (this.declarationID < decVO.declarationID) {
            return -1;
        }
        else {
            return 0;
        }
    }
	// for set 
	@Override
	public int hashCode() {
		return Objects.hash(declarationID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeclarationVO other = (DeclarationVO) obj;
		return Objects.equals(declarationID, other.declarationID);
	}

}
