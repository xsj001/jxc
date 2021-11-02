package com.yjxxt.jxc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Drug {
    private Integer id;

    private String drugName;

    private Double drugPrice;

    private String drugSpecification;

    private String drugUnit;

    private Integer drugShelfLife;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String drugMfg;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;

    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName == null ? null : drugName.trim();
    }

    public Double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Double drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getDrugSpecification() {
        return drugSpecification;
    }

    public void setDrugSpecification(String drugSpecification) {
        this.drugSpecification = drugSpecification == null ? null : drugSpecification.trim();
    }

    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit == null ? null : drugUnit.trim();
    }

    public Integer getDrugShelfLife() {
        return drugShelfLife;
    }

    public void setDrugShelfLife(Integer drugShelfLife) {
        this.drugShelfLife = drugShelfLife;
    }

    public String getDrugMfg() {
        return drugMfg;
    }

    public void setDrugMfg(String drugMfg) {
        this.drugMfg = drugMfg == null ? null : drugMfg.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String  toString() {
        return "Drug{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", drugPrice=" + drugPrice +
                ", drugSpecification='" + drugSpecification + '\'' +
                ", drugUnit='" + drugUnit + '\'' +
                ", drugShelfLife=" + drugShelfLife +
                ", drugMfg='" + drugMfg + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isValid=" + isValid +
                '}';
    }
}