package org.sysu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysu.pojo.Seckill;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;



//配置spring和junit整合,为了junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
/*告诉junit spring 配置文件*/
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    /*注入DAo实现类依赖*/
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void selectbyId() throws Exception {
        long id =1000;
        Seckill seckill = seckillDao.selectbyId(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void selectAll() throws Exception {
        List<Seckill> list = seckillDao.selectAll(0,4);
        for(Seckill s:list)
        {
            System.out.println(s);
        }
    }
    @Test
    public void reduceNumber() throws Exception {
        Date killtime = new Date();
        int update = seckillDao.reduceNumber(1000,killtime);
        System.out.println(killtime);
    }
}