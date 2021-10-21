package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from t_sys_user where name = #{name} and pwd = #{pwd}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "pwd",column = "pwd"),
           @Result(property = "role2UserSet",column = "id",
           many = @Many(select = "com.sunjob.yudioj_springboot_framemark.mapper.Role2UserMapper.findRole2UserByUserId"))
    }
    )
    User userLogin(String name,String pwd);

    @Select("select * from t_sys_user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "pwd",column = "pwd")
    })
    List<User> findAllUser();
}
