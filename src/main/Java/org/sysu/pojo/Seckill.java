package org.sysu.pojo;

import java.util.Date;

public class Seckill {

    private Long seckillid;
    private String name;
    private int kucun;
    private Date startTime;
    private Date endTime;
    private Date createTime;

    public Long getSeckillid() {
        return seckillid;
    }

    public void setSeckillid(Long seckillid) {
        this.seckillid = seckillid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKucun() {
        return kucun;
    }

    public void setKucun(int kucun) {
        this.kucun = kucun;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillid=" + seckillid +
                ", name='" + name + '\'' +
                ", kucun=" + kucun +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
