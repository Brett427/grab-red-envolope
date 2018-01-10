package org.sysu.dto;


/*暴露秒杀地址dto*/
public class Exposer {
    private boolean exposed;

    /*加密措施*/
    private String md5;

    private Long seckillid;

    /*系统当前时间*/
    private Long now;

    private Long start;

    private Long  end;

    public Exposer(boolean exposed, String md5, Long seckillid) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillid = seckillid;
    }

    public Exposer(boolean exposed, Long seckillid) {
        this.exposed = exposed;
        this.seckillid = seckillid;
    }

    public Exposer(boolean exposed,Long seckillid, Long now, Long start, Long end) {

        this.exposed = exposed;
        this.seckillid = seckillid;
        this.now = now;
        this.start = start;
        this.end = end;
    }


    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillid() {
        return seckillid;
    }

    public void setSeckillid(Long seckillid) {
        this.seckillid = seckillid;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Exposer() {
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillid=" + seckillid +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
