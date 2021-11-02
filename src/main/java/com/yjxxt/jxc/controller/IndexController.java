package com.yjxxt.jxc.controller;

import com.yjxxt.jxc.service.UserService;
import com.yjxxt.jxc.base.BaseController;
import com.yjxxt.jxc.bean.User;
import com.yjxxt.jxc.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;
    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 后端管理主页面
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest req){
        // 通过工具类，从cookie中获取userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        // 调用对应Service层的方法，通过userId主键查询用户对象
        User user =userService.selectByPrimaryKey(userId);
        // 将用户对象设置到request作用域中
        req.setAttribute("user",user);
        return "main";
    }
}
