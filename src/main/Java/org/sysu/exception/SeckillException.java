package org.sysu.exception;


/*秒杀相关业务异常*/
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
