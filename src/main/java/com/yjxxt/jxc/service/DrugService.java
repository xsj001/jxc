package com.yjxxt.jxc.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.jxc.base.BaseService;
import com.yjxxt.jxc.bean.Drug;
import com.yjxxt.jxc.dao.DrugMapper;
import com.yjxxt.jxc.query.DrugQuery;
import com.yjxxt.jxc.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DrugService extends BaseService<Drug,Integer> {
    @Resource
    private DrugMapper drugMapper;

    public Map<String,Object> queryUserByParams(DrugQuery drugQuery){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(drugQuery.getPage(),drugQuery.getLimit());
        PageInfo<Drug> pageInfo=new PageInfo<>(drugMapper.selectByParams(drugQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加药品
     * @param drug
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveDrug(Drug drug){
        // 1. 参数校验
        checkDrug(drug.getDrugName(),drug.getDrugUnit());
        // 2. 设置默认参数
        drug.setIsValid(1);
        drug.setCreateDate(new Date());
        drug.setUpdateDate(new Date());
        // 3. 执行添加，判断结果
        AssertUtil.isTrue(drugMapper.insertSelective(drug)==null,"药品信息添加失败");
    }
    /**
     * 参数校验
     * @param drugName
     */
    private void checkDrug(String drugName,String drugUnit){
        //药品名不能为空
        AssertUtil.isTrue(StringUtils.isBlank(drugName),"药品名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(drugUnit),"药品单位不能为空");
    }

    /**
     * 更新药品信息
     * @param drug
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDrug(Drug drug){
        // 1. 参数校验
        // 通过id查询药品对象
        Drug drug1 = drugMapper.selectByPrimaryKey(drug.getId());
        // 判断对象是否存在
        AssertUtil.isTrue(drug1==null,"药品对象不存在");
        // 验证参数
        checkDrug(drug.getDrugName(), drug.getDrugUnit());
        // 2. 设置默认参数
        drug1.setUpdateDate(new Date());
        // 3. 执行更新，判断结果
        AssertUtil.isTrue(drugMapper.updateByPrimaryKeySelective(drug)<1,"药品信息更新失败");
    }

    /**
     * 删除药品信息
     * @param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserByIds(Integer[] ids){
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择待删除的药品信息");
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"药品信息删除失败");
    }
}
