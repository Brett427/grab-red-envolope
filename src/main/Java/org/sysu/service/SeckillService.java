package org.sysu.service;

import org.sysu.dto.Exposer;
import org.sysu.dto.SeckillExecution;
import org.sysu.exception.RepeatKillException;
import org.sysu.exception.SeckillCloseException;
import org.sysu.exception.SeckillException;
import org.sysu.pojo.Seckill;

import java.util.List;

public interface SeckillService {

    /*查询所有秒杀记录*/
    List<Seckill> getSeckillList();

    /*查询单个秒杀记录*/
    Seckill getByid(Long seckillid);


    /*秒杀开启时输出秒杀接口地址,否则输出系统时间和秒杀时间*/
    Exposer exportSeckillUrl(Long seckillid);


    /*
    * 执行秒杀操作*/
    SeckillExecution executeSeckill(Long seckill, Long phone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;


}
