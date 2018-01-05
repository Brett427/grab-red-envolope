package org.sysu.dto;


/*暴露秒杀地址dto*/
public class Exposer {
    private boolean exposed;

    /*加密措施*/
    private String md5;

    private long seckillid;

    /*系统当前时间*/
    private long now;

    private long start;

    private long  end;

    public Exposer(boolean exposed, String md5, long seckillid) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillid = seckillid;
    }

    public Exposer(boolean exposed, long seckillid) {
        this.exposed = exposed;
        this.seckillid = seckillid;
    }

    public Exposer(boolean exposed,long seckillid, long now, long start, long end) {

        this.exposed = exposed;
        this.seckillid = seckillid;
        this.now = now;
        this.start = start;
        this.end = end;
    }
}
