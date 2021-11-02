<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(drug.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">药品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugName"
                   lay-verify="required" name="drugName" id="drugName"
                   value="${(drug.drugName)!}" placeholder="请输入药品名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">药品单价</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugPrice"
                   lay-verify="required" name="drugPrice" id="drugPrice"
                   value="${(drug.drugPrice)!}" placeholder="请输入药品单价">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">药品规格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugSpecification"
                   lay-verify="drugSpecification" name="drugSpecification"
                   value="${(drug.drugSpecification)!}"
                   id="drugSpecification"
                   placeholder="请输入药品规格">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">药品单位</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugUnit"
                   lay-verify="drugUnit" name="drugUnit"
                   value="${(drug.drugUnit)!}" id="drugUnit" placeholder="请输入药品单位">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">药品保质期</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugShelfLife"
                   lay-verify="drugShelfLife" name="drugShelfLife"
                   value="${(drug.drugShelfLife)!}" id="drugShelfLife" placeholder="请输入药品保质期">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">生产日期</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugMFG"
                   lay-verify="drugMfg" name="drugMfg"
                   value="${(drug.drugMfg)!}" id="drugMfg" placeholder="请输入生产日期">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateDrug">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/basics/add.update.js"></script>
</body>
</html>
