package com.customerService.jedis;

import java.util.ArrayList;
import java.util.List;

import com.customerService.model.ChatMessage;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}
	
	public static List<String> getAllKey() {
		Jedis jedis = pool.getResource();
		List<String> allKey = new ArrayList<String>();
		for (String key : jedis.keys("employee*")) {
			if (jedis.type(key).equals("list")) {
				allKey.add(key);
			}
		}
		return allKey;
	}
	
	public static void readAll(String sender, String receiver) {
		Jedis jedis = pool.getResource();
		Gson gson = new Gson();
		List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		jedis.del(key);
		for(String oneChat : historyData) {
			ChatMessage cm = gson.fromJson(oneChat, ChatMessage.class);
			if(cm.getStatus() != null) {
				cm.setStatus("read");
			}
			jedis.rpush(key, gson.toJson(cm));
		}
		
		return;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		jedis.close();
	}

}
