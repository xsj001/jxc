package com.yjxxt.jxc.controller;

import com.yjxxt.jxc.dao.DrugMapper;
import com.yjxxt.jxc.model.UserModel;
import com.yjxxt.jxc.query.DrugQuery;
import com.yjxxt.jxc.service.DrugService;
import com.yjxxt.jxc.service.UserService;
import com.yjxxt.jxc.base.BaseController;
import com.yjxxt.jxc.base.ResultInfo;
import com.yjxxt.jxc.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    @RequestMapping("user/login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd){
        ResultInfo resultInfo=new ResultInfo();
        // 调用Service层的登录方法，得到返回的用户对象
        UserModel userModel = userService.userLogin(userName, userPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    /**
     * 用户密码修改
     * @param req
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("user/updatePwd")
    @ResponseBody
    public ResultInfo updatePwd(HttpServletRequest req,String oldPassword,
                                String newPassword,String confirmPassword){
        ResultInfo resultInfo=new ResultInfo();
        // 获取userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        // 调用Service层的密码修改方法
        userService.updateUserPassword(userId,oldPassword,newPassword,confirmPassword);
        return resultInfo;
    }

    @RequestMapping("user/toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }






}
