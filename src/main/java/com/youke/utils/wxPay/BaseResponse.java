package com.youke.utils.wxPay;

public class BaseResponse<T> {
    /**
     * 状态码 200是成功，300是失败，301是超时
     */
    private String statusCode = "200";
    /**
     * 错误信息， example = "error"
     */
    private String message;
    /**
     * 返回前台的数据
     */
    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(String statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public BaseResponse failure() {
        this.statusCode = "300";
        this.message = "Response exception";
        return this;
    }

    public BaseResponse failure(String massage) {
        this.statusCode = "300";
        this.message = massage;
        return this;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
