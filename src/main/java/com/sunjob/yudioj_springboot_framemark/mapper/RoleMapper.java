package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    @Select("select * from t_sys_role where id = #{role_id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "auth2RoleSet",column = "id",
            many =  @Many(select = "com.sunjob.yudioj_springboot_framemark.mapper.Auth2RoleMapper.findAuthListByRole")
            )
    })
     Role findRoleById(String role_id);
    @Select("select * from t_sys_role where status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
    })
     List<Role> findAllRole();
    @Select("select * from t_sys_role")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
    })
     List<Role> findAllRoleWithout();
    @Update("update t_sys_role set name = #{name},status = #{status},modify_time = #{modifyTime} where id = #{id}")
    boolean modifyRole(Role role);

    @Update("update t_sys_role set status = '冻结' where id = #{id}")
    boolean roleFreeze(String id);
    @Insert("insert into t_sys_role (id,name,create_time,modify_time,status)values(#{id},#{name},#{createTime},#{modifyTime},#{status})")
    boolean addRole(Role role);
}
