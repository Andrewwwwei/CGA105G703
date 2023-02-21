package com.declaration.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.declaration.model.Announce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.jedis.JedisHandler;

@ServerEndpoint("/DeclarationWS/{userID}")
public class DeclarationWS {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("userID") String userID, Session userSession) throws IOException {
		// 0: from admin
		sessionsMap.put(userID, userSession);
		
		Set<String> userIDs = sessionsMap.keySet();

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userID, userIDs);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		Announce announce = gson.fromJson(message, Announce.class );
		Integer[] list = announce.getList();
		
		for( int id :list) {
			JedisHandler.saveDeclaration(announce, id);
		}		
		Set<String> userIDs = sessionsMap.keySet();
		for(Integer id :list) {
			String idStr = id.toString();
			System.out.println(idStr);
			Session session = sessionsMap.get(idStr);
//			System.out.println(session.isOpen());
			if( session != null && session.isOpen()) {
				session.getAsyncRemote().sendText("您有新的訊息通知 ! ");
			}
		}
		System.out.println("OnMessage out");
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}
		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
