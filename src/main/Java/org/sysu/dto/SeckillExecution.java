package org.sysu.dto;

import org.sysu.enums.killStateEnum;
import org.sysu.pojo.SuccessKill;


//秒杀执行结果
public class SeckillExecution {

    private Long seckillid;


    //秒杀执行结果状态
    private  int state;


    /*状态的文字说明*/
    private String stateInfo;

    private SuccessKill success;

    public SeckillExecution(Long seckillid, killStateEnum stateEnum, SuccessKill success) {
        this.seckillid = seckillid;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateinfo();
        this.success = success;
    }

    public SeckillExecution(Long seckillid, killStateEnum stateEnum) {
        this.seckillid = seckillid;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateinfo();
    }

    public Long getSeckillid() {
        return seckillid;
    }

    public void setSeckillid(Long seckillid) {
        this.seckillid = seckillid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKill getSuccess() {
        return success;
    }

    public void setSuccess(SuccessKill success) {
        this.success = success;
    }
}
