package com.yjxxt.jxc.controller;

import com.yjxxt.jxc.base.BaseController;
import com.yjxxt.jxc.base.ResultInfo;
import com.yjxxt.jxc.bean.Client;
import com.yjxxt.jxc.query.ClientQuery;
import com.yjxxt.jxc.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class ClientController extends BaseController {
    @Resource
    private ClientService clientService;

    /**
     * 进入客户信息界面
     * @return
     */
    @RequestMapping("client")
    public String index(){
        return "basics/client";
    }

    /**
     * 多条件查询药品信息
     * @param
     * @return
     */
    @RequestMapping("basics/query")
    @ResponseBody
    public Map<String,Object> queryUserByParams(ClientQuery clientQuery){
        return  clientService.queryUserByParams(clientQuery);
    }

    /**
     * 添加用户数据
     * @param
     * @return
     */
    @RequestMapping("client/add")
    @ResponseBody
    public ResultInfo saveClient(Client client){
        clientService.saveClient(client);
        return success("用户数据添加成功");
    }
    /**
     * 更新用户数据
     * @param
     * @return
     */
    @RequestMapping("client/change")
    @ResponseBody
    public ResultInfo updateClient(Client client){
        clientService.updateClient(client);
        return success("用户数据更新成功");
    }

    /**
     * 数据添加与更新页面视图转发
     * @param
     * @return
     */
    @RequestMapping("basics/addOrUpdateClient")
    public String addOrUpdateSaleChancePage(Integer id, Model model){
        if (id!=null){
            Client client = clientService.selectByPrimaryKey(id);
            model.addAttribute("client",client);
        }
        return "basics/add_change";
    }

    /**
     * 删除用户数据
     * @param
     * @return
     */
    @RequestMapping("client/delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids){
        clientService.deleteClient(ids);
        return success("用户数据删除成功");
    }
}
