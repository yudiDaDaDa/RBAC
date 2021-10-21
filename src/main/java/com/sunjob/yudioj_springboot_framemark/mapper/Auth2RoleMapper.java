package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import com.sunjob.yudioj_springboot_framemark.vo.Auth2Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface Auth2RoleMapper {
    @Select("select * from t_sys_auth2role where role_id = #{roleId} and status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "authId",column = "auth_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "auth",column = "auth_id",
            one = @One(select = "com.sunjob.yudioj_springboot_framemark.mapper.AuthMapper.findAuthById"))
    })
    Set<Auth2Role> findAuthListByRole(String roleId);
    @Select("select * from t_sys_auth2role where  status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "authId",column = "auth_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time")
    })
    List<Auth2Role> findAllAuth2Role();


    @Select("select * from t_sys_auth2role ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "authId",column = "auth_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
    })
    List<Auth2Role> findAllAuth2RoleWithout();
}
