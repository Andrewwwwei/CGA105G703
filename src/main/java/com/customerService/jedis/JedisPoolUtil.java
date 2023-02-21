package com.customerService.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool pool = null;

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPool() {
		if (pool == null) {
			synchronized (JedisPoolUtil.class) {
				if (pool == null) { // 避免第一次創建池時，有兩個以上的使用者同步通過上面的if判斷
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(100); //最大連線數
					config.setMaxIdle(8); //控制pool最多有多少個空閒狀態的jedis實體，預設為8
					config.setMaxWaitMillis(10000);//等待可用連線的最大時間，超時將拋出Exception，預設為-1，表示永不超時
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	// 可在ServletContextListener的contextDestroyed裡呼叫此方法註銷Redis連線池
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}
}
