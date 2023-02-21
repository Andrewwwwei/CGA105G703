package com.tripChat.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.tripChat.model.TripChatVO;

import core.jedis.JedisHandler;

@ServerEndpoint("/tripChat.do/{tripId}")
public class WSTripChat {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	Gson gson = new Gson();
	@OnOpen
	public void onOpen(@PathParam("tripId") String tripId, Session sessions) {
		connectedSessions.add(sessions);
		List<String> historyMsg = JedisHandler.getHistoryMsg(tripId);
		String msg = gson.toJson(historyMsg);
		for(Session session : connectedSessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(msg);
			}
		}
		System.out.println("連接成功");
	}
	
	@OnClose
	public void onClose() {
		System.out.println("已關閉");
	}
	
	@OnMessage
	public void onMsg(String msg, Session userSession) {
		TripChatVO tripChatVO = gson.fromJson(msg, TripChatVO.class);
		String tripId = tripChatVO.getTripId();
		
		for (Session session : connectedSessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(msg);
			}
		}
		JedisHandler.saveChatMsg(tripId, msg);
		System.out.println("接收訊息" + msg);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("系統異常");
		e.printStackTrace();
	}
}
