package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.QuestionMapper;
import com.sunjob.yudioj_springboot_framemark.oj.CodeTest;
import com.sunjob.yudioj_springboot_framemark.vo.Question;
import com.sunjob.yudioj_springboot_framemark.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    CodeTest codetest;
    @Override
    public List<Question> selectAll() {
        return questionMapper.selectAll();
    }

    @Override
    public Question selectById(String id) {
        return questionMapper.selectById(id);
    }

    @Override
    public String codeTest(Question question, String code,String userid,Task.type taskType) {
        Task task = codetest.codeSub01(question,code,userid,taskType);
        System.out.println(task.getTime());
        switch (task.getrType()){
            case CompileError:
            case CompileTimeout: return task.getComplieProcess().getMsg()+"\n"+"任务执行时间:"+task.getTime();
            case RunTimeout:
            case RunError: return task.getRunProcess().getErrorMsg()+"\n"+"任务执行时间:"+task.getTime();
            default: return task.getRunProcess().getMsg()[0]+"\n"+"任务执行时间:"+task.getTime();
        }
    }




}
