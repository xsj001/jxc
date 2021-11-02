package com.yjxxt.jxc.dao;

import com.yjxxt.jxc.base.BaseMapper;
import com.yjxxt.jxc.bean.Drug;

public interface DrugMapper extends BaseMapper<Drug,Integer> {

    Drug queryUserByUserUnit(String drugUnit);
}