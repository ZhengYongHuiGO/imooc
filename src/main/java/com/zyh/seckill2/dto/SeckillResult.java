package com.zyh.seckill2.dto;

/**
 * Created by Administrator on 2016/8/12.
 */
public class SeckillResult<T> {
    private boolean result;
    private String resultDetail;
    private T data;

    public SeckillResult( boolean result,String resultDetail) {
        this.resultDetail = resultDetail;
        this.result = result;
    }

    public SeckillResult(boolean result, String resultDetail, T data) {
        this.result = result;
        this.resultDetail = resultDetail;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getResultDetail() {
        return resultDetail;
    }

    public void setResultDetail(String resultDetail) {
        this.resultDetail = resultDetail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeckillResult{" +
                "result=" + result +
                ", resultDetail='" + resultDetail + '\'' +
                ", data=" + data +
                '}';
    }
}
