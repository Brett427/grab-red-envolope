package org.sysu.pojo;

import java.util.Date;

public class SuccessKill {
    private int id;
    private Long killid;
    private Long userphone;
    private short state;
    private Date createTime;
    private Seckill seckill;

    public SuccessKill() {
    }

    //多对一

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Long getKillid() {
        return killid;
    }

    public void setKillid(Long killid) {
        this.killid = killid;
    }

    public Long getUserphone() {
        return userphone;
    }

    public void setUserphone(Long userphone) {
        this.userphone = userphone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKill{" +
                "killid=" + killid +
                ", userphone=" + userphone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
