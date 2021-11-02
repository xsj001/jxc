package com.yjxxt.jxc.query;

import com.yjxxt.jxc.base.BaseQuery;

public class DrugQuery extends BaseQuery {
    private String drugName;
    private String drugPrice;
    private String drugSpecification;

    public DrugQuery() {
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(String drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getDrugSpecification() {
        return drugSpecification;
    }

    public void setDrugSpecification(String drugSpecification) {
        this.drugSpecification = drugSpecification;
    }
}
