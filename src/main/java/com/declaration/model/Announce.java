package com.declaration.model;
public class Announce {


 private String id;
 private String title;
 private String content;
 private Integer[] list;
 private String status;
 private String tripId;
 private String pic;

 public String getId() {
  return id;
 }
 
 public void setId(String id) {
  this.id = id;
 }
 public String getPic() {
  return pic;
 }
 
 public void setPic(String pic) {
  this.pic = pic;
 }
 
 public String getTripId() {
  return tripId;
 }

 public void setTripId(String tripId) {
  this.tripId = tripId;
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

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public Integer[] getList() {
  return list;
 }

 public void setList(Integer[] list) {
  this.list = list;
 }

}