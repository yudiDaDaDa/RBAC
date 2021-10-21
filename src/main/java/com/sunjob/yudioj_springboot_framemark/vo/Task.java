package com.sunjob.yudioj_springboot_framemark.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Task {
    public Task(){
        this.complieProcess = new ComplieProcess();
        this.runProcess = new RunProcess();
    }
    public enum resut{
        Complete,CompileError,CompileTimeout,RunTimeout,RunError;//任务完成 任务失败
    }
    private resut rType;

    public resut getrType() {
        return rType;
    }

    public void setrType(resut rType) {
        this.rType = rType;
    }

    public enum type{
        UserSub , UserTest , AnsBuild , TestBuild   //用户提交任务 用户测试任务 标准用例答案生成任务 测试用例答案生成任务
    }
    private type taskType;
    private ComplieProcess complieProcess; //编译过程
    private RunProcess runProcess; // 运行过程
    private User user; //执行该任务的用户
    private Question question; //任务对应的问题
    private long time ;//任务执行时间
    private String code; //任务指定的代码
    private String path; //任务文件所在路径
    private String filename; // 任务文件名称
    private List<List<String>> input;  //该任务的输入
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public ComplieProcess getComplieProcess() {
        return complieProcess;
    }

    public void setComplieProcess(ComplieProcess complieProcess) {
        this.complieProcess = complieProcess;
    }

    public RunProcess getRunProcess() {
        return runProcess;
    }

    public void setRunProcess(RunProcess runProcess) {
        this.runProcess = runProcess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    public type getTaskType() {
        return taskType;
    }

    public void setTaskType(type taskType) {
        this.taskType = taskType;
    }
}
