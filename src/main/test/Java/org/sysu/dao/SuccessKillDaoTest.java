package org.sysu.dao;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysu.pojo.SuccessKill;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
/*告诉junit spring 配置文件*/
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;


    @Test
    public void insertSuccessKill() throws Exception {
//        long id =1000L;
//        long phone = 12345678901L;
//        int a = successKillDao.insertSuccessKill(id,phone);
//        System.out.println(a);

    }

    @Test
    public void selectByidwithSeckill() throws Exception {
    }


    @Test
    public void judge() throws Exception
    {
        long id =1000L;
        long phone = 12345678901L;
        List<SuccessKill> list = successKillDao.judge(id,phone);
        System.out.println(list.size());
        System.out.println(list.get(0).getSeckill().toString());
    }
}