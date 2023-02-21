package com.stock.model;

import java.time.*;
import java.util.*;

public interface Stock_interface {
	public void insert(StockVO stockVO);
	public void update(StockVO stockVO);
    public void update(StockVO stockVO, java.sql.Connection con);
    public void delete(Integer roomId, LocalDate stayDate);
    public StockVO findByPrimaryKey(Integer roomId, LocalDate stayDate);
    public List<StockVO> getAll();
    public List<StockVO> getByRoomId(Integer roomId);
}
