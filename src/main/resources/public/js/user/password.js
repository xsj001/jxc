layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on("submit(saveBtn)", function(data) {
        var dataFile = data.field;

        $.ajax({
            type:"post",
            url:ctx+"/user/updatePwd",
            data:{
                oldPassword:dataFile.old_password,
                newPassword:dataFile.new_password,
                confirmPassword:dataFile.again_password
            },
            dataType:"json",
            success:function (result) {
                if (result.code==200){
                    layer.msg("修改密码成功,系统马上要退出了",function (){
                        // 退出系统后，删除对应的cookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/jxc"});
                        $.removeCookie("userName",{domain:"localhost",path:"/jxc"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/jxc"});
                        // 跳转到登录页面 (父窗口跳转)
                        window.parent.location.href=ctx+"/index";
                    });
                }else {
                    //登录失败的提示
                    layer.msg(result.msg);
                }
            }
        });
    });
});