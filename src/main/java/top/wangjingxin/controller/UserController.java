package top.wangjingxin.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.web.multipart.MultipartFile;
import top.wangjingxin.model.Change;
import top.wangjingxin.model.Task;
import top.wangjingxin.model.TaskList;
import top.wangjingxin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wangjingxin.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/11/26.
 */
@Controller
public class UserController
{
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map logIn(User user, HttpSession session)
    {
        return userService.logIn(user,session);
    }
    @RequestMapping(value = "/setTask",method = RequestMethod.POST)
    public String setTask(TaskList task, HttpSession session)
    {
        if(Integer.parseInt(session.getAttribute("set")+"")==0)
        userService.setTask(task.getTask(),session);
        return "redirect:/task.html";
    }
    @RequestMapping(value = "/getTask",method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getTask(HttpSession session)
    {
        return userService.gertTask(session);
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public void upload(HttpSession session, MultipartFile file, int which, HttpServletResponse response)
    {
        userService.upload(file,which,session,response);
    }
    @RequestMapping(value = "/change",method = RequestMethod.POST)
    @ResponseBody
    public Map change(Change change, HttpSession session)
    {
        change.setUser_id(session.getAttribute("user_id")+"");
        return userService.change(change);
    }
}
