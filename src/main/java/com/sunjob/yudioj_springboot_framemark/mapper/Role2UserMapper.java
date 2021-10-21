package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Role2User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface Role2UserMapper {
    @Select("select * from t_sys_role2user where user_id = #{userid} and status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "role",column = "role_id",
            one = @One(select = "com.sunjob.yudioj_springboot_framemark.mapper.RoleMapper.findRoleById"))
    })
    Set<Role2User> findRole2UserByUserId(String userid);
}
