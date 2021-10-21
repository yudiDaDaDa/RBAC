package com.sunjob.yudioj_springboot_framemark.mapper;

import com.sunjob.yudioj_springboot_framemark.vo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Select("select * from t_sys_question")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "ansCode",column = "ans_code"),
            @Result(property = "ansTest",column = "ans_test"),
            @Result(property = "itd",column = "itd"),
            @Result(property = "ansJudge",column = "ans_judge"),
            @Result(property = "ansPass",column = "ans_pass")
    })
    List<Question> selectAll();

    @Select("select * from t_sys_question where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "modifyTime",column = "modify_time"),
            @Result(property = "status",column = "status"),
            @Result(property = "ansCode",column = "ans_code"),
            @Result(property = "ansTest",column = "ans_test"),
            @Result(property = "itd",column = "itd"),
            @Result(property = "ansJudge",column = "ans_judge"),
            @Result(property = "ansPass",column = "ans_pass")
    })
    Question selectById(String id);
}
