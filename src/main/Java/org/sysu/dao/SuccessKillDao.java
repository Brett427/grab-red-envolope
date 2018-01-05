package org.sysu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.sysu.pojo.SuccessKill;

import java.util.List;

@Component

public interface SuccessKillDao {

    int insertSuccessKill(@Param("seckillid")long seckillid, @Param("userphone") long userphone);


    //根据id查询successkilled
    List<SuccessKill> selectByidwithSeckill(@Param("seckillid")long seckillid, @Param("phone") long userphone);

    List<SuccessKill> judge(@Param("seckillid")long id, @Param("phone") long phone);
}
