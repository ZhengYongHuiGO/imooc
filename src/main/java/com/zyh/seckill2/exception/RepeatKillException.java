package com.zyh.seckill2.exception;

/**
 * Created by Administrator on 2016/8/3.
 */
public class RepeatKillException extends SecKillException {
    public RepeatKillException() {
        super();
    }

    public RepeatKillException(String s) {
        super(s);
    }

    public RepeatKillException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RepeatKillException(Throwable throwable) {
        super(throwable);
    }

    protected RepeatKillException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
