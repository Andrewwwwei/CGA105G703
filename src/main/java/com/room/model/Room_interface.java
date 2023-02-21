package com.room.model;

import java.util.*;

public interface Room_interface {
	public void insert(RoomVO roomVO);
    public void update(RoomVO roomVO);
    public void delete(Integer roomId);
    public RoomVO findByPrimaryKey(Integer roomId);
    public List<RoomVO> getAll();
	public List<RoomVO> getAllByVen(Integer venId);
}
