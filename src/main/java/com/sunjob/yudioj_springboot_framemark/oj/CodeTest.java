package com.sunjob.yudioj_springboot_framemark.oj;

import com.sunjob.yudioj_springboot_framemark.vo.ComplieProcess;
import com.sunjob.yudioj_springboot_framemark.vo.Question;
import com.sunjob.yudioj_springboot_framemark.vo.RunProcess;
import com.sunjob.yudioj_springboot_framemark.vo.Task;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;


@Component
public class CodeTest {
    private final static Runtime runtime;
    static{
       runtime = Runtime.getRuntime();
    }

     void codeCompile(Task task){
        String path = task.getPath()+"\\"+task.getFilename()+".java";
        try {
            String command = "javac "+path;
            long preTime = System.currentTimeMillis();
           Process process = runtime.exec(command);//编译java文件
            process.waitFor(5,TimeUnit.SECONDS);
            task.getComplieProcess().setTime(System.currentTimeMillis()-preTime);
            if(process.isAlive()){
                process.destroy();
                task.getComplieProcess().setMsg("编译超时");
                task.getComplieProcess().setType(ComplieProcess.result.CompileTimeout);
                return;
            }
            InputStream error = process.getErrorStream();
           String errorMsg = codeRead(error);
           if(errorMsg.trim().length()!=0) {
               task.getComplieProcess().setMsg("编译出错:"+errorMsg);
               task.getComplieProcess().setType(ComplieProcess.result.CompileError);
               return;
           }
            task.getComplieProcess().setMsg("编译成功");
           task.getComplieProcess().setType(ComplieProcess.result.CompileSucess);
        } catch (Exception e) {
            task.getComplieProcess().setMsg("文件找不到");
            task.getComplieProcess().setType(ComplieProcess.result.CompileError);
            return;
        }
    }

    String codeRead(InputStream in){
        int len =-1;
        byte[] temp = new byte[12];
      StringBuffer res = new StringBuffer();
        try {
              while((len = in.read(temp))!=-1){
                String cur = new String(temp,0,len,"GBK");
                res.append(cur);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("文件找不到");
            return "";
        }
        return res.toString();
    }
    boolean codeWrite(Task task){
        String path = task.getPath()+"\\"+task.getFilename()+".java";
        try {
             FileOutputStream fi = new FileOutputStream(path);
            for(int i=0;i<task.getCode().length();i++){
                char cur = task.getCode().charAt(i);
                fi.write(cur);
            }
            fi.flush();
            fi.close();
            return true;
        } catch (Exception e) {
            System.out.println("文件找不到");
            return false;
        }
    }

    void codeRun(Task task){
        String command = "java -classpath "+task.getPath()+" "+task.getFilename();
        try {
            long preTime  = System.currentTimeMillis();
            Process process = runtime.exec(command);
             process.waitFor(5, TimeUnit.SECONDS);//未结束等待5s
            task.getRunProcess().setTime(System.currentTimeMillis()-preTime);
            if(process.isAlive()) {
                process.destroy();
                task.getRunProcess().setErrorMsg("运行超时");;
                task.getRunProcess().setType(RunProcess.result.RunTimeout);
                return;
            }
            InputStream erro = process.getErrorStream();
            String erroMsg = codeRead(erro);
            if(erroMsg.trim().length()!=0) {
                task.getRunProcess().setType(RunProcess.result.RunError);
                task.getRunProcess().setErrorMsg("运行出错:"+erroMsg);
                return;
            }
            InputStream success = process.getInputStream();
            String successMsg = codeRead(success);
           task.getRunProcess().setType(RunProcess.result.RunSuccess);
           task.getRunProcess().setMsg(new String[]{successMsg});
           return;
        } catch (Exception e) {
           task.getRunProcess().setType(RunProcess.result.RunError);
           task.getRunProcess().setErrorMsg("文件找不到");
           return;
        }
    }



    public Task codeSub01(Question question,String code,String id,Task.type taskType){
        Task task = new Task();
       task.setTaskType(taskType);
        task.setCode(code);
        task.setFilename("Solution"+question.getId());
        task.setPath( "D:\\codeTest\\Test\\user"+id);
      codeWrite(task);
      codeCompile(task);
      if(!(task.getComplieProcess().getType() == ComplieProcess.result.CompileSucess)){
          task.setTime(task.getComplieProcess().getTime());
          switch (task.getComplieProcess().getType()){
              case CompileError: task.setrType(Task.resut.CompileError); break;
              case CompileTimeout:task.setrType(Task.resut.CompileTimeout);break;
          }
          return task;
      }
      codeRun(task);
      task.setTime(task.getRunProcess().getTime()+task.getComplieProcess().getTime());
        switch (task.getRunProcess().getType()){
            case RunError: task.setrType(Task.resut.RunError);
            break;
            case RunTimeout:task.setrType(Task.resut.RunTimeout);
            break;
            case RunSuccess: task.setrType(Task.resut.Complete);
            break;
        }
        return task;
    }


}
