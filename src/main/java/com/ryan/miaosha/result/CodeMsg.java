package com.ryan.miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;

    public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(200, "server error");
    //客户端错误
    public static final CodeMsg MOBILE_EMPTY = new CodeMsg(101, "手机号码不能为空！");
    public static final CodeMsg MOBILE_ERROR = new CodeMsg(102, "手机号码格式错误！");
    public static final CodeMsg MOBILE_NOTEXIST = new CodeMsg(103, "手机号码未注册！");
    public static final CodeMsg PASSWORD_EMPTY = new CodeMsg(104, "密码不能为空！");
    public static final CodeMsg PASSWORD_ERROR = new CodeMsg(104, "密码错误！");
    public static final CodeMsg BIND_ERROR = new CodeMsg(105, "参数校验异常：%s");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillAllArgs(Object... objects) {
        return new CodeMsg(this.code, String.format(this.msg, objects));
    }
}
