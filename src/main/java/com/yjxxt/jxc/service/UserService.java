package com.yjxxt.jxc.service;

import com.yjxxt.jxc.model.UserModel;
import com.yjxxt.jxc.base.BaseService;
import com.yjxxt.jxc.bean.User;
import com.yjxxt.jxc.dao.UserMapper;
import com.yjxxt.jxc.utils.AssertUtil;
import com.yjxxt.jxc.utils.Md5Util;
import com.yjxxt.jxc.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;
    /**
     * 用户登录
     * 1. 验证参数
     * 姓名 非空判断
     * 密码 非空判断
     * 2. 根据用户名，查询用户对象
     * 3. 判断用户是否存在
     * 用户对象为空，记录不存在，方法结束
     * 4. 用户对象不为空
     * 用户存在，校验密码
     * 密码不正确，方法结束
     * 5. 密码正确
     * 用户登录成功，返回用户的相关信息 （定义UserModel类，返回用户某些信息）
     */

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel userLogin(String userName,String userPwd){
        // 1. 验证参数checkLoginParams
        checkLoginParams(userName,userPwd);
        // 2. 根据用户名，查询用户对象queryUserByUserName
        User user=userMapper.queryUserByUserName(userName);
        System.out.println(user);
        // 3. 判断用户是否存在 (用户对象为空，记录不存在，方法结束)
        AssertUtil.isTrue(user==null,"用户不存在");
        // 4. 用户对象不为空（用户存在，校验密码checkLoginPwd。密码不正确，方法结束）
        checkLoginPwd(userPwd,user.getUserPwd());
        // 5. 密码正确（用户登录成功，返回用户的相关信息）


        return buildUserInfo(user);
    }
    /**
     * 构建返回的用户信息
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user){
        UserModel userModel=new UserModel();
        // 设置用户信息
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUsername(user.getUserName());
        userModel.setTruename(user.getTrueName());
        return userModel;
    }
    /**
     * 验证登录密码
     * @param userPwd 前台传递的密码
     * @param upwd 数据库中查询到的密码
     * @return
     */
    private void checkLoginPwd(String userPwd, String upwd) {
        // 数据库中的密码是经过加密的，将前台传递的密码先加密，再与数据库中的密码作比较
        userPwd= Md5Util.encode(userPwd);
        // 比较密码
        AssertUtil.isTrue(!userPwd.equals(upwd),"用户密码不正确");
    }
    /**
     * 验证用户登录参数
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        // 判断姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        // 判断密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码不能为空");
    }

    /**
     * 用户密码修改
     * 1. 参数校验
     * userId 非空 用户对象必须存在
     * oldPassword 非空 与数据库中密文密码保持一致
     * newPassword 非空 与原始密码不能相同
     * confirmPassword 非空 与新密码保持一致
     * 2. 设置用户新密码
     * 新密码进行加密处理
     * 3. 执行更新操作
     * 受影响的行数小于1，则表示修改失败
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(Integer userId,String oldPassword,
                                   String newPassword,String confirmPassword){
        // 通过userId获取用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 1. 参数校验
        checkPasswordParams(user,oldPassword,newPassword,confirmPassword);
        // 2. 设置用户新密码
        user.setUserPwd(Md5Util.encode(newPassword));
        // 3. 执行更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"");
    }

    /**
     * 验证用户密码修改参数
     * 用户ID：userId 非空 用户对象必须存在
     * 原始密码：oldPassword 非空 与数据库中密文密码保持一致
     * 新密码：newPassword 非空 与原始密码不能相同
     * 确认密码：confirmPassword 非空 与新密码保持一致
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */

    public void checkPasswordParams(User user,String oldPassword,
                                     String newPassword,String confirmPassword){
        // user对象 非空验证
        AssertUtil.isTrue(user==null,"用户未登录或不存在");
        // 原始密码 非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原密码不能为空");
        // 原始密码要与数据库中的密文密码保持一致
        AssertUtil.isTrue(!(Md5Util.encode(oldPassword).equals(user.getUserPwd())),"原密码不正确");
        // 新密码 非空校验
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        // 新密码与原始密码不能相同
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码不能与原密码相同");
        // 确认密码 非空校验
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确认密码不能为空");
        // 新密码要与确认密码保持一致
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)),"新密码与确认密码不一致");
    }
}
