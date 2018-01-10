package org.sysu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.sysu.pojo.SuccessKill;

import java.util.List;

@Component
public interface SuccessKillDao {

    int insertSuccessKill(@Param("seckillid")Long seckillid, @Param("userphone") Long userphone);


    //根据id查询successkilled
    List<SuccessKill> selectByidwithSeckill(@Param("seckillid")Long seckillid, @Param("phone") Long userphone);

    List<SuccessKill> judge(@Param("seckillid")Long id, @Param("phone") Long phone);
}
