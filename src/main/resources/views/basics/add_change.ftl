<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(client.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">客户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugName"
                   lay-verify="required" name="clientName" id="clientName"
                   value="${(client.clientName)!}" placeholder="请输入客户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input drugPrice"
                   lay-verify="required" name="clientPhone" id="clientPhone"
                   value="${(client.clientPhone)!}" placeholder="请输入手机号">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateClient">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/basics/add.change.js"></script>
</body>
</html>
