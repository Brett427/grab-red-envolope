package org.sysu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.sysu.pojo.Seckill;

import java.util.Date;
import java.util.List;
@Component
public interface SeckillDao {

    //减少库存 秒杀发生时的时间
    int reduceNumber(@Param("seckillid") Long seckillid,@Param("killTime") Date killTime);

    // 根据ID查询秒杀对象
    Seckill selectbyId(Long seckillId);


    //根据偏移量查询秒杀商品列表
    List<Seckill> selectAll(@Param("offset") int offset, @Param("limit") int limit);
}
