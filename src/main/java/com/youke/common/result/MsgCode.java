package com.youke.common.result;

public enum MsgCode {
    SUCCESS(true, "成功"),
    LAYOUT_SUCEESS(true,"退出登录成功"),
    IINSERT_SUCCESS(true, "新增成功"),
    UPDATE_SUCCESS(true, "修改成功"),
    FIND_SUCCESS(true,"查询成功"),
    DELETE_SUCCESS(true,"删除成功"),
    SUBMIT_SUCEESS(true,"提交成功"),
    LOGIN_SUCCESS(true, "登录成功"),
    CREATE_SUCCESS(true,"创建成功"),
    REGISTER_SUCCESS(true,"注册成功"),
    SUBMIT_SUCCESS(true,"提交成功"),
    USER_EXIST(true,"用户已存在"),
    HANDLE_SUCCESS(true,"处理成功"),

    HANDLE_FAIL(false,"处理失败"),
    SCORE_FAIL(false,"该用户不可重复评分"),
    LOGIN_FAIL(false,"用户或密码错误"),
    REGISTER_FAIL(false,"注册失败"),
    AUTHOR_FAIL(false,"未授权"),
    PHONE_FALL(false,"此号码已注册"),
    INSERT_FAIL(false, "新增失败"),
    FIND_FAIL(false, "查询失败"),
    AUTHORIZATION_FAIL(false,"你没有此权限"),
    UPDATE_FAIL(false, "修改失败"),
    DELETE_FAIL(false, "删除失败"),
    DELETE_USE(false, "角色在使用中禁止删除"),
    CONFIRM_FAIl(false, "验收失败"),
    PASSWORD_FAIL(false,"密码不正确"),
    COUNT_FIAL(false,"账号错误"),
    PRINT_COUNT_FIAL(false,"请输入账号"),
    PASSWORD_NULL_FIAL(false,"密码不能为空"),
    DEFUALT_FAIL(false, "请选择默认地址类型"),
    CREATE_FAIL(true,"创建失败"),
    SUBMIT_FAIL(false,"提交失败"),
    EXPORT_FAIL(false,"导出失败"),
    FAIL(false, "失败");
    
    private boolean status;
    private String msg;

    MsgCode(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }
}
