package com.sunjob.yudioj_springboot_framemark.service;


import com.sunjob.yudioj_springboot_framemark.vo.Question;
import com.sunjob.yudioj_springboot_framemark.vo.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<Question> selectAll();
    Question selectById(String id);
    String codeTest(Question question, String code, String uesrid,Task.type taskType);

}
