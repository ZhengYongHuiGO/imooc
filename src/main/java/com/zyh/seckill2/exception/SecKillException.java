package com.zyh.seckill2.exception;

/**
 * Created by Administrator on 2016/8/3.
 */
public class SecKillException extends RuntimeException {
    public SecKillException() {
        super();
    }

    public SecKillException(String s) {
        super(s);
    }

    public SecKillException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SecKillException(Throwable throwable) {
        super(throwable);
    }

    protected SecKillException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
