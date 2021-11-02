package com.yjxxt.jxc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.jxc.base.BaseService;
import com.yjxxt.jxc.bean.Client;
import com.yjxxt.jxc.bean.Drug;
import com.yjxxt.jxc.dao.ClientMapper;
import com.yjxxt.jxc.query.ClientQuery;
import com.yjxxt.jxc.utils.AssertUtil;
import com.yjxxt.jxc.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService extends BaseService<Client,Integer> {
    @Resource
    private ClientMapper clientMapper;

    public Map<String,Object> queryUserByParams(ClientQuery clientQuery){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(clientQuery.getPage(),clientQuery.getLimit());
        PageInfo<Client> pageInfo=new PageInfo<>(clientMapper.selectByParams(clientQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加药品
     * @param client
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveClient(Client client){
        // 1. 参数校验
        checkClient(client.getClientName(),client.getClientPhone());
        // 2. 设置默认参数
        client.setIsValid(1);
        client.setCreateDate(new Date());
        client.setUpdateDate(new Date());
        // 3. 执行添加，判断结果
        AssertUtil.isTrue(clientMapper.insertSelective(client)==null,"客户信息添加失败");
    }

    /**
     * 参数校验
     * @param clientName
     * @param clientPhone
     */
    public void checkClient(String clientName,String clientPhone){
        AssertUtil.isTrue(StringUtils.isBlank(clientName),"请输入客户名");
        AssertUtil.isTrue(StringUtils.isBlank(clientPhone),"请输入手机号");
        AssertUtil.isTrue(!PhoneUtil.isMobile(clientPhone),"手机号格式不正确");
    }

    /**
     * 更新用户信息
     * @param client
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateClient(Client client){
        // 1. 参数校验
        // 通过id查询药品对象
        Client client1 = clientMapper.selectByPrimaryKey(client.getId());
        // 判断对象是否存在
        AssertUtil.isTrue(client1==null,"用户不存在");
        // 验证参数
        checkClient(client.getClientName(), client.getClientPhone());
        // 2. 设置默认参数
        client.setUpdateDate(new Date());
        // 3. 执行更新，判断结果
        AssertUtil.isTrue(clientMapper.updateByPrimaryKeySelective(client)<1,"用户信息更新失败");
    }

    /**
     * 删除用户信息
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteClient(Integer[] ids){
        AssertUtil.isTrue(ids==null || ids.length==0,"删除对象不存在");
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"用户信息更新失败");
    }
}
