package org.sysu.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.sysu.dao.SeckillDao;
import org.sysu.dao.SuccessKillDao;
import org.sysu.dto.Exposer;
import org.sysu.dto.SeckillExecution;
import org.sysu.enums.killStateEnum;
import org.sysu.exception.RepeatKillException;
import org.sysu.exception.SeckillCloseException;
import org.sysu.exception.SeckillException;
import org.sysu.pojo.Seckill;
import org.sysu.pojo.SuccessKill;
import org.sysu.service.SeckillService;


import java.util.Date;
import java.util.List;

import static org.sysu.utils.jiami.getMD5;

public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private SeckillDao seckillDao;

    private SuccessKillDao successKillDao;


    public List<Seckill> getSeckillList() {

        return seckillDao.selectAll(0,4);
    }

    public Seckill getByid(long seckillid) {
        return seckillDao.selectbyId(seckillid);
    }
    /* 展示秒杀的接口地址*/
    public Exposer exportSeckillUrl(long seckillid) {
        Seckill seckill =seckillDao.selectbyId(seckillid);
        if(seckill==null)
        {
            return new Exposer(false,seckillid);
        }
        //获取秒杀单的开始时间和结束时间
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date  nowTime = new Date();
        if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime())
        {
            return new Exposer(false,seckillid,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }

        String md5 = getMD5(seckillid);
        return new Exposer(true, md5, seckillid);
    }




    public SeckillExecution executeSeckill(long seckillid, long phone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if(md5==null||md5.equals(getMD5(seckillid)))
        {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑  减库存,记录秒杀行为
        Date nowTime = new Date();
        try
        {
            int updatecount =seckillDao.reduceNumber(seckillid,nowTime);
            if(updatecount<=0)
            {
                //没有更新数据库,秒杀已结束
                throw new SeckillCloseException("closed");
            }
            else
            {
                List<SuccessKill> list1 = successKillDao.judge(seckillid,phone);
                if(list1.size()>1)
                {
                    throw new RepeatKillException("repeat kill");
                }
                else
                {
                    int insertCount = successKillDao.insertSuccessKill(seckillid,phone);
                    if(insertCount<=0)
                    {
                        throw new RepeatKillException("repeat kill");
                    }
                    else
                    {
                        List<SuccessKill> list =  successKillDao.selectByidwithSeckill(seckillid,phone);
                        return new SeckillExecution(seckillid, killStateEnum.SUCCESS,list.get(0));
                    }
                }

            }
        }
        catch (SeckillCloseException e1)
        {
            throw e1;
        }
        catch (RepeatKillException e2)
        {
            throw e2;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            //所有编译器异常转换为运行期异常
            throw new SeckillException("seckill inner error" + e.getMessage());

            //spring 自动进行事务回滚
        }




    }
}
