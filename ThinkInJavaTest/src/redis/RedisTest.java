package redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("127.0.0.1",6382);
		
//		jedis.set("hello", "yangwu");
		jedis.lpush("1", "1","2","2","3","3");
		
	}

}
