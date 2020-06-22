package com.youke.utils.wxPay;

public class BaseRequest<T> {
    /**
     *  请求对象
     */
    T requestData;


    public BaseRequest(){
       super();

    }
    public T getRequestData(){
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }
}
