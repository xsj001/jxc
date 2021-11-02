package com.yjxxt.jxc.dao;

import com.yjxxt.jxc.base.BaseMapper;
import com.yjxxt.jxc.bean.User;

public interface UserMapper extends BaseMapper<User,Integer> {
    // 根据用户名查询用户对象queryUserByUserName
    User queryUserByUserName(String userName);



}