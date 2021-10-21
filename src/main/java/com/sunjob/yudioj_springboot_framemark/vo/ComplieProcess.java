package com.sunjob.yudioj_springboot_framemark.vo;




public class ComplieProcess {
   public enum result{
       CompileError , CompileSucess , CompileTimeout  //编译成功 编译失败 编译超时
   }
   private result type;

    public result getType() {
        return type;
    }

    public void setType(result type) {
        this.type = type;
    }

    private String msg; //编译过程中产生的信息
    private Long time; //编译使用的时间
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
