package com.yangxuhao.info.until;

/**
 * Created by yangxvhao on 17-4-27.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * redis连接池
 */
public class RedisUtil {
    private static JedisPool jedisPool;
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    static {
        try {
            initPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化线程池
     */
    private static void initPool() throws IOException {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(Config.getInt("redis_max_idle", 50));
//        jedisPoolConfig.setMaxWaitMillis(Config.getInt("redis_max_wait_mills",2000));
//        jedisPoolConfig.setMaxTotal(Config.getInt("redis_max_total",500));
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestOnReturn(true);
//        String host = Config.getString("redis_host","localhost");
//        int port = Config.getInt("redis_port", 6379);
//        int timeout = Config.getInt("redis_timeout", 2000);
//        String password = Config.getString("redis_password");
        String host="127.0.0.1";
        int port=6379;
        int timeout=2000;
        String password="123456";
        // 构造连接池
        jedisPool = password == null || password.equals("") ?
                new JedisPool(jedisPoolConfig, host, port, timeout) : new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }

    //获取连接
    public static Jedis getConnection() throws SQLException {
        return jedisPool.getResource();
    }

    //回到线程池
    public static void returnResource(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }

    //释放资源
    public static void returnBrokenResource(Jedis jedis) {
        jedisPool.returnBrokenResource(jedis);
    }

    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getConnection();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis get Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return value;
    }

    public static long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getConnection();
            return jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis del Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return 0;
    }

    public static String setex(String key, int seconds, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getConnection();
            result = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return result;
    }

    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getConnection();
            result = jedis.set(key, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return result;
    }

    //如果没有此个值,插入成功返回1,如果set中有此值,插入失败返回0
    public static Long sadd(String key, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = getConnection();
            result =  jedis.sadd(key,value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return result;
    }

    public static long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getConnection();
            return jedis.incr(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return -1;
    }

    public static long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getConnection();
            return jedis.ttl(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }

        return -1;
    }

    public static void saveList(String key, Object object){
        Jedis jedis = null;
        try {
            jedis = getConnection();
            jedis.lpush(key,object.toString());
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }
    }

    public static List<String> getList(String key){
        Jedis jedis = null;
        List<String> list = new ArrayList<String>();
        try {
            jedis = getConnection();
            long len = jedis.llen(key);
            list = jedis.lrange(key,0,len);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            logger.error("redis setex Exception", e);
        } finally {
            //返还到连接池
            jedisPool.returnResource(jedis);
        }
        return list;
    }

    public  static  void main(String[] args){
        RedisUtil.set("newtest","wukaiwei");
        logger.info("插入成功");
        RedisUtil.del("newtest");
        logger.info("删除成功");
    }

}

