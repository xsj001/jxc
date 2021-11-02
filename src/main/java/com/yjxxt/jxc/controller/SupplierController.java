package com.yjxxt.jxc.controller;

import com.yjxxt.jxc.base.BaseController;
import com.yjxxt.jxc.dao.SupplierMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class SupplierController extends BaseController {
    @Resource
    private SupplierMapper supplierMapper;

    @RequestMapping("supplier")
    public String index(){
        return "basics/supplier";
    }
}
