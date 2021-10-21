package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
    public Role findRoleById(String role_id);
}
