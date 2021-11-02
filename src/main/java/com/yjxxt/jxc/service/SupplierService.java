package com.yjxxt.jxc.service;

import com.yjxxt.jxc.base.BaseService;
import com.yjxxt.jxc.bean.Supplier;
import com.yjxxt.jxc.dao.SupplierMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SupplierService extends BaseService<Supplier,Integer> {
    @Resource
    private SupplierMapper supplierMapper;

}
