layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    /**
     * 用户登录 表单提交
     */
    form.on("submit(login)", function(data){
        // 获取表单元素的值 （用户名 + 密码）
        var fieldData = data.field;
        // 判断参数是否为空
        if (fieldData.username == "undefined" || fieldData.username.trim() =='') {
            layer.msg("用户名称不能为空！");
            return ;
        }
        if (fieldData.password == "undefined" || fieldData.password.trim() =='') {
            layer.msg("用户密码不能为空！");
            return ;
        }
        // 发送 ajax 请求，请求用户登录
        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data:{
                userName:fieldData.username,
                userPwd:fieldData.password
            },
            dataType:"json",
            success:function (data) {
                // 判断是否登录成功
                if (data.code==200){
                    layer.msg("登陆成功",function (){
                        // 将用户信息存到cookie中
                        $.cookie("userIdStr",data.result.userIdStr);
                        $.cookie("userName",data.result.userName);
                        $.cookie("trueName",data.result.trueName);
                        // 登录成功后，跳转到首页
                        window.location.href=ctx+"/main";
                    });
                }else{
                    // 提示信息
                    layer.msg(data.msg);
                }
            }
        });
        return false;
    })
});