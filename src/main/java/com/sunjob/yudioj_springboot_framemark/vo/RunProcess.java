package com.sunjob.yudioj_springboot_framemark.vo;




public class RunProcess {
    public enum result{
        RunSuccess , RunError , RunTimeout // 运行成功 运行出错 运行超时
    }
    private result type;
    private String[] msg; //运行过程中产生的信息
    private String[] input;//该运行过程中的输入
    private long time; //该运行消耗的时间

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg;
    public result getType() {
        return type;
    }

    public void setType(result type) {
        this.type = type;
    }

    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    public String[] getInput() {
        return input;
    }

    public void setInput(String[] input) {
        this.input = input;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }



}
