package top.wangjingxin.dao;

import top.wangjingxin.model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.TimeSimpleFormate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/11/26.
 */
@Repository("userDao")
public class UserDao
{
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    public Map logIn(User user, HttpSession session)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "user.mapping.login";
        user = sqlSession.selectOne(statement,user);
        if(user!=null)
        {
            session.setAttribute("user_id",user.getUser_id());
            session.setAttribute("set",user.getIf_set());
            user.setUser_password(null);
            Map map = new HashMap();
            map.put("mess",user);
            sqlSession.close();
            return map;
        }
        sqlSession.close();
        Map map = new HashMap();
        map.put("mess",0);
        return map;
    }
    public void setTask(List<Task> task, HttpSession session)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "user.mapping.setTask";
        int i =1;
        for(Task t:task)
        {
            t.setUser_id(session.getAttribute("user_id")+"");
            t.setTask_id(session.getAttribute("user_id")+""+i++);
            sqlSession.insert(statement,t);
        }
        statement = "user.mapping.updateSet";
        sqlSession.update(statement,session.getAttribute("user_id"));
        session.setAttribute("set","1");
        sqlSession.close();
    }
    public List<Task> getTask(Object user_id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "user.mapping.getTask";
        List<Task> tasks = sqlSession.selectList(statement,user_id);
        statement = "user.mapping.getTaskFinish";
        GetTask getTask = new GetTask();
        getTask.setUser_id(user_id+"");
        getTask.setDate(TimeSimpleFormate.simpleData(new Date()));
        for(Task task:tasks)
        {
            getTask.setTask_id(task.getTask_id());
            task.setRichang(sqlSession.selectOne(statement,getTask));
            if(task.getRichang()!=null)task.setIf_over(1);
        }
        sqlSession.close();
        return tasks;
    }
    public void upload(Richang richang, HttpServletResponse response, String name)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int result = sqlSession.insert("user.mapping.upload",richang);
        Map map = new HashMap();
        map.put("mess",result);
        map.put("url",richang.getImg());
        sqlSession.close();
      //  return map;
        try
        {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("<script type='text/javascript' type='language'>parent.window.getImg('"+name+"');</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map change(Change change)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "user.mapping.change";
        int result = sqlSession.update(statement,change);
        System.out.println(result);
        sqlSession.close();
        Map map = new HashMap();
        map.put("mess",result);
        return map;
    }
}
