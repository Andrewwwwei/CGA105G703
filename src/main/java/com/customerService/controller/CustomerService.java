package com.customerService.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.customerService.jedis.JedisHandleMessage;
import com.customerService.model.ChatMessage;
import com.customerService.model.State;
import com.google.gson.Gson;
import com.Users.model.UsersService;

@ServerEndpoint("/CustomerService/{userId}")
public class CustomerService {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); // P357 並行
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userId, userSession);

//		String text = String.format("Session ID = %s, connected; userId = %s", userSession.getId(), userId);
//		System.out.println(text);

	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();

		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, "", "");
			if (userSession != null && userSession.isOpen() && !sender.equals("employee")) { //請求來自會員
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory)); //用戶歷史訊息加載
//				System.out.println("history = " + gson.toJson(cmHistory));
				Session receiverSession = sessionsMap.get(receiver); //刷新客服用戶清單
				if(receiverSession != null && receiverSession.isOpen()) {
					ChatMessage refreshUserList = new ChatMessage("onAndOff", sender, receiver, "", "", "");
					receiverSession.getAsyncRemote().sendText(gson.toJson(refreshUserList));
				}else {
					ChatMessage noEmployee = new ChatMessage("noEmployee", sender, receiver, "", "", "");
					userSession.getAsyncRemote().sendText(gson.toJson(noEmployee));
				}
				return;
			} else if (userSession != null && userSession.isOpen() && sender.equals("employee")) { //請求來自客服
				if(chatMessage.getMessage() != null) {
					JedisHandleMessage.readAll(sender, receiver);
				}
				UsersService usersService = new UsersService();
				String userName = usersService.getOneUser(Integer.parseInt(receiver)).getUserName();
				Map<String, String> cmAndName = new HashMap<String, String>();
				cmAndName.put("msgObj", gson.toJson(cmHistory));
				cmAndName.put("userName", userName);
				cmAndName.put("type", "history");
				userSession.getAsyncRemote().sendText(gson.toJson(cmAndName));
//				System.out.println("history = " + gson.toJson(cmAndName));
				return;
			}
		} else if ("userlist".equals(chatMessage.getType())) {
			Set<String> userIds = sessionsMap.keySet();
			UsersService usersService = new UsersService();
			////////////// 取得上線用戶
			List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
			for (String id : userIds) {
				if (!id.equals("employee")) {
					Map<String, String> users = new HashMap<String, String>();
					users.put("userId", id);
					users.put("onoff", "online");
					users.put("userName", usersService.getOneUser(Integer.parseInt(id)).getUserName());
					List<String> historyData = JedisHandleMessage.getHistoryMsg("employee", id);
					String status = "read";
					for(String oneChat : historyData) {
						ChatMessage cm = gson.fromJson(oneChat, ChatMessage.class);
						if(cm.getStatus() == null) {
							continue;
						}else if(cm.getStatus().equals("unread")) {
							status = "unread";
							break;
						}
					}
					users.put("status", status);
					try {
						users.put("lastmsg", historyData.get(historyData.size() - 1));
					} catch (IndexOutOfBoundsException e) {
						users.put("lastmsg", "");
					}
					userList.add(users);
				}
			}
			////////////// 取得離線用戶
			List<String> allKey = JedisHandleMessage.getAllKey();
			List<String> userIdList = new ArrayList<String>();
			for (String key : allKey) {
				String userId = key.substring(9);
				Set<String> userIdSet = sessionsMap.keySet();
				if (!userIdSet.contains(userId)) {
					userIdList.add(userId);
				}
			}
			for (String id : userIdList) {
				Map<String, String> users = new HashMap<String, String>();
				users.put("userId", id);
				users.put("onoff", "offline");
				users.put("userName", usersService.getOneUser(Integer.parseInt(id)).getUserName());
				List<String> historyData = JedisHandleMessage.getHistoryMsg("employee", id);
				String status = "read";
				for(String oneChat : historyData) {
					ChatMessage cm = gson.fromJson(oneChat, ChatMessage.class);
					if(cm.getStatus() == null) {
						continue;
					}else if(cm.getStatus().equals("unread")) {
						status = "unread";
						break;
					}
				}
				users.put("status", status);
				try {
					users.put("lastmsg", historyData.get(historyData.size() - 1));
				} catch (IndexOutOfBoundsException e) {
					users.put("lastmsg", "");
				}
				userList.add(users);
			}

			State stateMessage = new State("open", "employee", userList);
			String stateMessageJson = gson.toJson(stateMessage);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(stateMessageJson);
//				System.out.println("users = " + stateMessageJson);
			}
			return;
		}else if ("offline".equals(chatMessage.getType())) {
			Session receiverSession = sessionsMap.get(receiver); //刷新客服用戶清單
			if(receiverSession != null && receiverSession.isOpen()) {
				ChatMessage refreshUserList = new ChatMessage("onAndOff", sender, receiver, "", "", "");
				receiverSession.getAsyncRemote().sendText(gson.toJson(refreshUserList));
			}
			return;
		}else if ("read".equals(chatMessage.getType())){
			JedisHandleMessage.readAll(sender, receiver);
			return;
		}

		Session receiverSession = sessionsMap.get(receiver);
		JedisHandleMessage.saveChatMessage(sender, receiver, message);
		userSession.getAsyncRemote().sendText(message);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
		}
//		System.out.println("Message received: " + message);
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

//		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
//				reason.getCloseCode().getCode(), userNames);
//		System.out.println(text);
	}
}
