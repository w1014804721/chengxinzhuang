package top.wangjingxin.service;

import org.springframework.web.multipart.MultipartFile;
import top.wangjingxin.dao.UserDao;
import top.wangjingxin.model.Change;
import top.wangjingxin.model.Richang;
import top.wangjingxin.model.Task;
import top.wangjingxin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.TimeSimpleFormate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/11/26.
 */
@Service("userService")
public class UserService
{
    @Autowired
    UserDao userDao;
    public Map logIn(User user, HttpSession session)
    {
        return userDao.logIn(user,session);
    }

    public void setTask(List<Task> task, HttpSession session)
    {
        if((session.getAttribute("set")+"").equals("0"))//如果是0则去设置。
        userDao.setTask(task,session);
    }
    public List<Task> gertTask(HttpSession session)
    {
        return userDao.getTask(session.getAttribute("user_id"));
    }
    public void upload(MultipartFile file, int which, HttpSession session, HttpServletResponse response)
    {
        try
        {
            String name = session.getAttribute("user_id")+""+System.currentTimeMillis()+which;
            String path = session.getServletContext().getRealPath(System.getProperty("file.separator"))+System.getProperty("file.separator")+"img"+System.getProperty("file.separator")+name+".jpg";
            file.transferTo(new File(path));
            Richang richang = new Richang();
            richang.setDate(TimeSimpleFormate.simpleData(new Date()));
            richang.setConcreate_time(TimeSimpleFormate.dateFormat(new Date()));
            richang.setImg(path);
            richang.setUser_id(session.getAttribute("user_id")+"");
            richang.setTask_id(session.getAttribute("user_id")+""+which);
            //return
             userDao.upload(richang,response,name);
        } catch (IOException e)
        {
            Map map = new HashMap();
            map.put("mess",0);
            e.printStackTrace();
            //return map;
        }
    }
    public Map change(Change change)
    {
        return userDao.change(change);
    }
}
