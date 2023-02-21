package com.roomPhoto.model;

import java.util.*;

public interface RoomPhoto_interface {
	public void insert(RoomPhotoVO roomPhotoVO);
    public void update(RoomPhotoVO roomPhotoVO);
    public void delete(Integer roomPhotoId);
    public RoomPhotoVO findByPrimaryKey(Integer roomPhotoId);
    public List<RoomPhotoVO> getAll();
    public List<RoomPhotoVO> getARoom(Integer roomId);
}
