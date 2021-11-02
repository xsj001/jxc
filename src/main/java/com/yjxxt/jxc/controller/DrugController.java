package com.yjxxt.jxc.controller;

import com.yjxxt.jxc.base.BaseController;
import com.yjxxt.jxc.base.ResultInfo;
import com.yjxxt.jxc.bean.Drug;
import com.yjxxt.jxc.query.DrugQuery;
import com.yjxxt.jxc.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DrugController extends BaseController {
    @Autowired
    private DrugService drugService;

    /**
     * 进入药品信息界面
     * @return
     */
    @RequestMapping("basics")
    public String index(){
        return "basics/drug_info_main";
    }

    /**
     * 多条件查询药品数据
     * @param
     * @return
     */
    @RequestMapping("basics/drug")
    @ResponseBody
    public Map<String,Object> queryUserByParams(DrugQuery drugQuery){
        return drugService.queryUserByParams(drugQuery);
    }

    /**
     * 添加药品信息
     * @param drug
     * @return
     */
    @RequestMapping("basics/save")
    @ResponseBody
    public ResultInfo saveDrug(Drug drug){
        System.out.println(drug.toString());
        drugService.saveDrug(drug);
        return success("药品信息添加成功");
    }

    /**
     * 更新药品信息
     * @param drug
     * @return
     */
    @RequestMapping("basics/update")
    @ResponseBody
    public ResultInfo updateDrug(Drug drug){
        drugService.updateDrug(drug);
        return success("药品信息更新成功");
    }

    /**
     * 数据添加与更新页面视图转发
     * @param
     * @return
     */
    @RequestMapping("basics/addOrUpdateUserPage")
    public String addOrUpdateSaleChancePage(Integer id, Model model){
        if (id!=null){
            Drug drug = drugService.selectByPrimaryKey(id);
            model.addAttribute("drug",drug);
        }
        return "basics/add_update";
    }

    /**
     * 删除药品信息
     * @param ids
     * @return
     */
    @RequestMapping("basics/delete")
    @ResponseBody
    public ResultInfo deleteDrug(Integer[] ids){
        drugService.deleteUserByIds(ids);
        return success("药品信息删除成功");
    }
}
