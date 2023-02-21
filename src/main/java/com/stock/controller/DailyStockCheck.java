package com.stock.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;

public class DailyStockCheck implements Runnable {
	
	@Override
    public void run() {
        StockService stockService = new StockService();
        //刪除過去庫存
        List<StockVO> stockVOAll = stockService.getAll();
        for(StockVO vo : stockVOAll) {
        	if(vo.getStayDate().isBefore(LocalDate.now())) {
        		stockService.deleteStock(vo.getRoomId(), vo.getStayDate());
        	}
        }
        //盤點新增6個月庫存
        LocalDate toSixMonths = LocalDate.now().plusMonths(6).plusDays(2);
        Stream<LocalDate> stream = LocalDate.now().datesUntil(toSixMonths, Period.ofDays(1));
		List<LocalDate> dateList = stream.collect(Collectors.toList());
		RoomService roomService = new RoomService();
		List<RoomVO> roomVOAll = roomService.getAll();
		for(RoomVO vo : roomVOAll) {
			if(vo.getRoomUpdate().equals(1)) { //房型是上架的
				for(LocalDate date : dateList) {
					if(stockService.getOneStock(vo.getRoomId(), date) == null) {  //查不到庫存就新增
						stockService.addStock(vo.getRoomId(), date, vo.getRoomAmount());
					}
				}
			}
		}
    }
}
