package org.sysu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysu.dto.Exposer;
import org.sysu.pojo.Seckill;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                       "classpath:/spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getByid() throws Exception {
        Long id =1001l;
        Seckill seckill =seckillService.getByid(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Long id =1000l;
        Exposer exposer =seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        Long id = 1000l;
        Long phone = 12345678990L;

    }

}