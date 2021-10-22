package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Role2User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface Role2UserMapper {
    @Select("select * from t_sys_role2user where user_id = #{userid} and status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property ="status",column = "status"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "role",column = "role_id",
            one = @One(select = "com.sunjob.yudioj_springboot_framemark.mapper.RoleMapper.findRoleById"))
    })
    Set<Role2User> findRole2UserByUserId(String userid);
    @Select("select * from t_sys_role2user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property ="status",column = "status"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
    })
    List<Role2User> findAllRole2UserWithout();

    @Update("update t_sys_role2user set modify_time = #{modifyTime}, status =#{status},role_id =#{roleId},user_id = #{userId} where id = #{id}")
    boolean modifyUserRole(Role2User role2User);

    @Select("select * from t_sys_role2user where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property ="status",column = "status"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
    })
    Role2User findRole2UserById(String id);

    @Select("select * from t_sys_role2user where status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property ="status",column = "status"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
    })
    List<Role2User> findAllRole2User();

    @Update("update t_sys_role2user set status = '冻结',modify_time = #{modifyTime} where id = #{id}")
    boolean freezeRole2User(String id, Date modifyTime);

    @Insert("insert into t_sys_role2user (id,create_time,modify_time,status,user_id,role_id)values(#{id},#{createTime},#{modifyTime},#{status},#{userId},#{roleId})")
    boolean addUserRole(Role2User role2User);
}
