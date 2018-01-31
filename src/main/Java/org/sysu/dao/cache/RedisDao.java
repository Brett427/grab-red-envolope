package org.sysu.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sysu.pojo.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    private JedisPool jedisPool;

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    public RedisDao(String ip, Integer port)
    {
        jedisPool = new JedisPool(ip, port);
    }
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    public Seckill getseckill(long seckillId)
    {   //redis 缓存redis操作
        try{
            Jedis jedis =jedisPool.getResource();
            try
            {
                String key ="kill:"+seckillId;
                //并没有实现内部序列化操作
                // get->byte[]-> 反序列化 -Java Object(Seckill)
                //采用自定义序列化方式
                //有了protostuff,可以把一个对象变成一个字节数组传入redis

                byte[] bytes =jedis.get(key.getBytes());

                if(bytes!=null)
                {
                    Seckill seckill =schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema); //seckil 被反序列化
                    return seckill;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }
    public String putSeckill(Seckill seckill)
    {
        // set Object(seckill) ->byte[] 序列化 ->byte[]
        try
        {
            Jedis jedis =jedisPool.getResource();
            try
            {
                String key = "kill:"+seckill.getSeckillid();
                byte[] bytes =ProtostuffIOUtil.toByteArray(seckill,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout =60*60;
                String result =jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }
}
