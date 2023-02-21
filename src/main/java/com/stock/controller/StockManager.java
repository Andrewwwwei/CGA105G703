package com.stock.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.customerService.jedis.JedisPoolUtil;

@WebListener
public class StockManager implements ServletContextListener {
	
	private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Duration duration = Duration.between(LocalDateTime.now(),LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT));
        scheduler.scheduleAtFixedRate(new DailyStockCheck(), duration.toMillis(), 60*60*24*1000, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(new DailyStockCheck(), 0, 1, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    	JedisPoolUtil.shutdownJedisPool();
    }
}
