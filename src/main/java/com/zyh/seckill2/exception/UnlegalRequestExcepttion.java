package com.zyh.seckill2.exception;

/**
 * Created by Administrator on 2016/8/3.
 */
public class UnlegalRequestExcepttion  extends SecKillException{
    public UnlegalRequestExcepttion() {
        super();
    }

    protected UnlegalRequestExcepttion(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }

    public UnlegalRequestExcepttion(Throwable throwable) {
        super(throwable);
    }

    public UnlegalRequestExcepttion(String s) {
        super(s);
    }

    public UnlegalRequestExcepttion(String s, Throwable throwable) {
        super(s, throwable);
    }
}
