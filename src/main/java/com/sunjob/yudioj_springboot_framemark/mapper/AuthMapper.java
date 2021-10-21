package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AuthMapper {

    @Select("select * from t_sys_auth where id = #{id} and status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time")
    })
     Auth findAuthById(String id);

    @Select("select * from t_sys_auth where id = #{id} ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time")
    })
    Auth findAuthByIdWithout(String id);

    @Select("select * from t_sys_auth where status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time")
    })
    List<Auth> findALlAuth();
    @Select("select * from t_sys_auth ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time")
    })
    List<Auth> findAllAuthWithout();
    @Update("update t_sys_auth set name = #{name},status = #{status},modify_time = #{modifyTime} where id = #{id}")
    boolean modifyAuthSub(Auth auth);
    @Update("update t_sys_auth set status = '冻结',modify_time = #{date} where id = #{id}")
    boolean authFreeze(String id, Date date);

    @Insert("insert into t_sys_auth (id,name,status,create_time,modify_time)values(#{id},#{name},#{status},#{createTime},#{modifyTime})")
    boolean addAuth(Auth auth);
}
