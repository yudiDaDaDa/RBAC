package com.sunjob.yudioj_springboot_framemark.vo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class User {
    private String id;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private String status;


    private int roleId;
    private String pwd;
    private Set<Role2User> role2UserSet;
    private Set<Auth> tempAuthSet;
    public String getId() {
        return id;
    }
    public Set<Role2User> getRole2UserSet() {
        return role2UserSet;
    }

    public Set<Auth> getTempAuthSet() {
        return tempAuthSet;
    }

    public void setTempAuthSet(Set<Auth> tempAuthSet) {
        this.tempAuthSet = tempAuthSet;
    }
    public void setRole2UserSet(Set<Role2User> role2UserSet) {
        this.role2UserSet = role2UserSet;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
