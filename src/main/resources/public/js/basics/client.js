layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx+'/basics/query',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'clientName', title: '用户名', minWidth:50, align:"center"},
            {field: 'clientPhone', title: '用户电话', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '修改时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150,
                templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });


    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").on("click",function(){
        table.reload("userListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                clientName: $("input[name='clientName']").val(), //用户名
                clientPhone: $("input[name='clientPhone']").val(), //用户电话
            }
        })
    });


    /**
     * 头部工具栏 监听事件
     */
    table.on('toolbar(client)',function (obj){
        var checkStatus=table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                // 点击添加按钮，打开更新用户的对话框
                openAddOrUpdateUserDialog();
                break;
            case 'del':
                // 点击删除按钮，将对应选中的记录删除
                deleteClient(checkStatus.data);
                break;
        };
    });

    /**
     * 表格行 监听事件
     * client为table标签的lay-filter 属性值
     */
    table.on('tool(client)', function(obj){
        var layEvent = obj.event;
        // 监听编辑事件
        if(layEvent === "edit") {
            openAddOrUpdateUserDialog(obj.data.id);
        } else if (layEvent==="del"){
            // 监听删除事件
            layer.confirm('确定删除当前用户信息？', {icon: 3, title: "用户管理"}, function (index) {
                $.post(ctx + "/client/delete",{ids:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            });
        }
    });

    /**
     * 打开用户添加或更新对话框
     */
    function  openAddOrUpdateUserDialog(clientId){
        var title="<h2>用户管理 - 用户添加</h2>";
        var url=ctx+"/basics/addOrUpdateClient";
        // 通过id判断是添加操作还是修改操作
        if (clientId) {
            // 如果id不为空，则为修改操作
            title = "<h2>用户管理 - 用户更新</h2>";
            url = url + "?id=" + clientId;
        }
        layui.layer.open({
            title:title,
            type: 2,
            content: url,
            area:["650px","400px"],
            maxmin:true
        });
    }

    /**
     * 批量删除用户
     * @param datas
     */
    function deleteClient(datas) {
        if (datas.length == 0) {
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }
        layer.confirm('确定删除选中的用户记录？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            layer.close(index);
            var ids = "ids=";
            for (var i = 0; i < datas.length; i++) {
                if (i < datas.length - 1) {
                    ids = ids + datas[i].id + "&ids=";
                } else {
                    ids = ids + datas[i].id
                }
            }
            $.ajax({
                type: "post",
                url: ctx + "/client/delete",
                data: ids,
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5});
                    }
                }
            })
        });
    }
    /**
     * 行监听事件
     */

});