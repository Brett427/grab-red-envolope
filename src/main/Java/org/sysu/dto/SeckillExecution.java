package org.sysu.dto;

import org.sysu.enums.killStateEnum;
import org.sysu.pojo.SuccessKill;


//秒杀执行结果
public class SeckillExecution {

    private long seckillid;


    //秒杀执行结果状态
    private  int state;


    /*状态的文字说明*/
    private String stateInfo;

    private SuccessKill success;

    public SeckillExecution(long seckillid, killStateEnum stateEnum, SuccessKill success) {
        this.seckillid = seckillid;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateinfo();
        this.success = success;
    }

    public SeckillExecution(long seckillid, killStateEnum stateEnum) {
        this.seckillid = seckillid;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateinfo();
    }


}
