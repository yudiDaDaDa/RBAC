package com.sunjob.yudioj_springboot_framemark.vo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Question {
    private String id;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private String status;
    private String ansCode;
   private String ansPass;
    public String getItd() {
        return itd;
    }

    public String getAnsPass() {
        return ansPass;
    }

    public void setAnsPass(String ansPass) {
        this.ansPass = ansPass;
    }

    public void setItd(String itd) {
        this.itd = itd;
    }

    private String ansTest;
    private String ansJudge;
    private String itd;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnsCode() {
        return ansCode;
    }

    public void setAnsCode(String ansCode) {
        this.ansCode = ansCode;
    }

    public String getAnsTest() {
        return ansTest;
    }

    public void setAnsTest(String ansTest) {
        this.ansTest = ansTest;
    }

    public String getAnsJudge() {
        return ansJudge;
    }

    public void setAnsJudge(String ansJudge) {
        this.ansJudge = ansJudge;
    }
}
