package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Menu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    @Select("select * from t_sys_menu where auth_id = #{authId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "authId",column = "auth_id"),
    })
    List<Menu> findMenuByAuthId(String  authId);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "authId",column = "auth_id"),
            @Result(property = "auth",column = "auth_id",
            one = @One(select = "com.sunjob.yudioj_springboot_framemark.mapper.AuthMapper.findAuthById"))
    })
    @Select("select * from t_sys_menu where id = #{id} and status = '正常'")
    Menu findMenuById(String id);


    @Select("select * from t_sys_menu where status = '正常'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "authId",column = "auth_id")
    })
    List<Menu> selectMenuAll();
    @Select("select * from t_sys_menu ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "authId",column = "auth_id")
    })
    List<Menu> selectMenuAllWithout();

    @Update("update  t_sys_menu  set name = #{name} ,auth_id = #{authId} ,parent_id = #{parentId},status = #{status},modify_time=#{modifyTime} , url= #{url} where id = #{id}")
    boolean menuModifySub(Menu menu);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "authId",column = "auth_id"),
            @Result(property = "auth",column = "auth_id",
                    one = @One(select = "com.sunjob.yudioj_springboot_framemark.mapper.AuthMapper.findAuthById"))
    })
    @Select("select * from t_sys_menu where id = #{id} ")
    Menu findMenuByIdWithout(String id);

    @Update("update t_sys_menu set status = '冻结',modify_time = #{modifyTime} where id = #{id}")
    boolean freezeMenu(String id, Date modifyTime);

    @Insert("insert into t_sys_menu (id,parent_id,name,status,create_time,modify_time,auth_id,url)values(#{id},#{parentId},#{name},#{status},#{createTime},#{modifyTime},#{authId},#{url})")
    boolean addMenu(Menu menu);
}
