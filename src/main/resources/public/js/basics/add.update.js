layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
        // 引入 formSelects 模块
         formSelects=layui.formSelects;

    /**
     * 添加或更新用户
     */
   form.on("submit(addOrUpdateDrug)",function (data){
       // 弹出loading层
       var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false,
           shade: 0.8});
        //判断添加还是修改
       var url=ctx+"/basics/save";
       if ($("input[name='id']").val()){
           url=ctx+"/basics/update";
       }
        /*发送ajax添加*/


       $.post(url,data.field,function (result) {

           if (result.code==200){
               // 关闭弹出层（返回值为index的弹出层）
               setTimeout(function (){
                    top.layer.close(index);
                    top.layer.msg("操作成功了！");
                   // 关闭所有ifream层
                   layer.closeAll("iframe");
                   // 刷新父页面
                   parent.location.reload();
               },500);
           }else {
               layer.msg(result.msg, {icon: 5});
           }
       });
       return false;
   });
    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
});