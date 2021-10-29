package com.sunjob.yudioj_springboot_framemark.vo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class Role {
    private String name;
    private String id;
    private Date createTime;
    private Date modifyTime;
    private String status;
    private Set<Auth2Role> auth2RoleSet;

    public Set<Auth2Role> getAuth2RoleSet() {
        return auth2RoleSet;
    }

    public void setAuth2RoleSet(Set<Auth2Role> auth2RoleSet) {
        this.auth2RoleSet = auth2RoleSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
