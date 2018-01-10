package org.sysu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysu.pojo.SuccessKill;
import javax.annotation.Resource;
import java.util.List;




@RunWith(SpringJUnit4ClassRunner.class)
/*告诉junit spring 配置文件*/
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;


    @Test
    public void insertSuccessKill() throws Exception {
        Long id =1001L;
        Long phone = 13938292822L;
        int a = successKillDao.insertSuccessKill(id,phone);
        System.out.println(a);

    }

    @Test
    public void selectByidwithSeckill() throws Exception {

        Long id =1000L;
        Long phone = 12345678901L;
        List<SuccessKill> list = successKillDao.selectByidwithSeckill(id,phone);
        System.out.println(list.size());
        System.out.println(list.get(0).getSeckill().toString());
    }


    @Test
    public void judge() throws Exception
    {
        Long id =1000L;
        Long phone = 12345678901L;
        List<SuccessKill> list = successKillDao.judge(id,phone);
        System.out.println(list.size());
        System.out.println(list.get(0).getSeckill().toString());
    }
}