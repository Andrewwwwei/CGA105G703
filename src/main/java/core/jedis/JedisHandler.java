package core.jedis;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

import com.Users.model.UsersVO;
import com.declaration.model.Announce;
import com.declaration.model.DeclarationService;
import com.declaration.model.DeclarationVO;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandler {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	private static String notice = "notice:";
	
	public static List<String> getNotice(String id) {
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(notice + id, 0, 25);
		jedis.close();
		return historyData;
	}

	public static void saveDeclaration(Announce announce , Integer id) {
		DeclarationService decSvc = new DeclarationService();
		  byte[] pic = null;
		  try{
		   System.out.println("get pic id:" +announce.getId());
		   pic = decSvc.getPic(Integer.valueOf(announce.getId()));
		  }catch(Exception e) {
		   System.out.println("Service 讀取圖片錯誤");
		  }
		  // convert to base64
		  if(pic!= null && pic.length!=0) {
		  String base64String = Base64.getEncoder().encodeToString(pic);
		  announce.setPic(base64String);
		  announce.setList(null);
		  }
		  // to Redis  
		  Jedis jedis = pool.getResource();
		  Gson gson = new Gson();
		  String declaration = gson.toJson(announce);

		  jedis.lpush(notice + id, declaration);
		  jedis.close();
	}

	
	public static void saveChatMsg(String tripId , String msg) {
		Jedis jedis = pool.getResource();
		String key = new StringBuilder("TRIP_ID").append(tripId).append(":").toString();
		jedis.rpush(key,msg);
		jedis.close();
	}
	
	public static List<String> getHistoryMsg(String tripId){
		String key = new StringBuilder("TRIP_ID").append(tripId).append(":").toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}
	
	public void saveUserInTrip(UsersVO usersVO,String tripName,Integer tripId, String userName) {
		Integer userId = usersVO.getUserId();
		Jedis jedis = pool.getResource();
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		String key = new StringBuffer("notice").append(":").append(userId).toString();
		String content = new StringBuilder(userName).append("邀請您，加入").append(tripName).append("一同規劃行程").toString();
		map.put("title", "行程邀請");
		map.put("content", content);
		map.put("status", "1");
		map.put("tripId", tripId.toString());
		String jsonStr = new Gson().toJson(map);
		jedis.lpush(key,jsonStr);
		jedis.close();
	}
	
	public void updateInvite(String userId, Integer index, String status) {
		Jedis jedis = pool.getResource();
		 String msg = getNotice(userId).get(index);
		 Gson gson = new Gson();
		 Announce annVO = gson.fromJson(msg, Announce.class);
		 annVO.setStatus(status);
		 
		 jedis.lset("notice:" + userId, index, gson.toJson(annVO));
		 
	}
}
