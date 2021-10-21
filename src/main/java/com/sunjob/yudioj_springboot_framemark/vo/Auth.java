package com.sunjob.yudioj_springboot_framemark.vo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class Auth {
    private String id;
    private String name;
    private String status;



    private Date createTime;
    private Date  modifyTime;
    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return id.equals(auth.id) && name.equals(auth.name) && status.equals(auth.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}
