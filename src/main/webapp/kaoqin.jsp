<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>教务管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="<%=Const.ROOT%>lib/layui-v2.5.4/css/layui.css" media="all">
<link rel="stylesheet" href="<%=Const.ROOT%>css/public.css" media="all">
</head>
<body>
	<div class="layuimini-container">
		<div class="layuimini-main">
			<fieldset class="layui-elem-field layuimini-search">
				<legend>搜索信息</legend>
				<div style="margin: 10px 10px 10px 10px">
					<form class="layui-form layui-form-pane" action="">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">学生姓名</label>
								<div class="layui-input-inline">
									<input type="text" name="name" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<a class="layui-btn" lay-submit="" lay-filter="data-search-btn">搜索</a>
							</div>
							<c:if test="${sessionScope.role==2 }">
							<div class="layui-inline">
								<button type="button"
									class="layui-btn layui-btn-normal data-add-btn">
									<i class="layui-icon layui-icon-add-1"></i>考勤打卡
								</button>
							</div>
							
							</c:if>
							<c:if test="${sessionScope.role==1 }">
							<button type="button" class="layui-btn layui-btn-warm data-export-btn">
								<i class="layui-icon layui-icon-download-circle"></i>导出</button>
							</c:if>
						</div>
					</form>
				</div>
			</fieldset>
			<table class="layui-hide" id="currentTableId"
				lay-filter="currentTableFilter"></table>
			<script type="text/html" id="currentTableBar">
            	<a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        	</script>
		</div>
	</div>
	
	<div class="layuimini-container" id="popAdd" style="display: none;">
		<div class="layuimini-main">
			<form class="layui-form" action="" lay-filter="aexample">
				<input type="hidden" name="uid" value="${sessionScope.users.id }"/>
				<div class="layui-form-item">
	                <label class="layui-form-label">课程</label>
	                <div class="layui-input-block">
	                    <select name="kid" lay-verify="required" id="kid1"></select>
	                </div>
            	</div> 
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="add">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	


	<script src="<%=Const.ROOT%>lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
		<script type="text/javascript"
		src="<%=Const.ROOT%>wangEditor/wangEditor.min.js"></script>
	<script>
	
	<%-- var E = window.wangEditor;
	var editor1 = new E('#editor1');
	editor1.customConfig.uploadFileName = 'file';
	editor1.customConfig.uploadImgServer = '<%=Const.ROOT%>upfile';
	editor1.customConfig.onchange = function (html) {
		document.getElementById("content1").value=html;
    }
	editor1.create(); --%>
	 
	 layui.use(['form', 'table','laydate','upload','rate'], function () {
	        var $ = layui.jquery,
	            form = layui.form,
	            table = layui.table,
	        	laydate = layui.laydate,
	        	rate=layui.rate,
	       		upload = layui.upload;
	        
	        $.getJSON("<%=Const.ROOT%>kecheng/jsonlist",function(data){
	         	$("#kid1").html("<option value=''>请选择</option>");
	    			for(i=0;i<data.length;i++){
	    				$("#kid1").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
	    			}
	    			form.render('select');//必须渲染一下
	    		});
	      
	        
	        // 监听添加操作
	        $(".data-add-btn").on("click", function () {
	        	var addIndex=layer.open({
	        		title:"新增",
	        		area: ['700px', '300px'],
	        		type: 1, 
	        		content: $("#popAdd")
	        	});
	        });

	       
	        var exportData;
	        var ins1=table.render({
            elem: '#currentTableId',
            url: '<%=Const.ROOT%>kaoqin/list',
            title:'学生考勤数据表',
            cols: [[
				{field:'id',hide:true,width: 0},
				{width: 100, templet:'#no',title: 'NO', sort: true},
				 {field: 'uname', width: 100, title: '学生'},
				 {field: 'tname', width: 100, title: '教师'},
				 {field: 'kname', width: 250, title: '课程'},
                {field: 'optime', width: 250, title: '考勤时间'},
                {title: '操作', minWidth: 60, templet: '#currentTableBar', fixed: "right", align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            done: function (res, curr, count) {
                exportData = res.data;
            }
        });
        $(".data-export-btn").on("click", function () {
        	table.exportFile(ins1.config.id,exportData,'xls'); //data 为该实例中的任意数量的数据
        });
        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            //var result = JSON.stringify(data.field);
            //console.log(data.field);
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where:data.field
            }, 'data');

            return false;
        });


        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'shenhe') {
            	 layer.open({
                     //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                     type: 1,
                     title: "审核",
                     area: ['700px', 'auto'],
                     content: $("#popShenhe")//引用的弹出层的页面层的方式加载修改界面表单
            });
           //表单初始赋值
            form.val('sexample', data);
            //动态向表传递赋值当然也是异步请求的要数据的修改数据的获取
            setFormValue(obj,data);    
            }else if (obj.event === 'edit') {
                //layer.alert('编辑行：<br>' + JSON.stringify(data))
                layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 1,
                        title: "修改",
                        area: ['700px', 'auto'],
                        content: $("#popUpdate")//引用的弹出层的页面层的方式加载修改界面表单
               });
              //表单初始赋值
               form.val('uexample', data);
               //动态向表传递赋值当然也是异步请求的要数据的修改数据的获取
               setFormValue(obj,data);    
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    //console.log(obj.data);
                    $.getJSON('<%=Const.ROOT%>kaoqin/delete',{'id':obj.data.id},function(msg) {
  						//console.log(msg);
  						if(msg.code=="0"){
  							layer.msg(msg.msg, { icon: 6, time: 800},function(){
  								layer.close(index);
  								obj.del();
  							});
  						}else{
  							layer.msg(msg.msg, {icon: 5});
  						}	
  					}); 
                });
            }
        });
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
            layer.confirm('现在打卡吗？', function (index) {
                //console.log(obj.data);
                $.getJSON('<%=Const.ROOT%>kaoqin/add',data.field,function(msg) {
						//console.log(msg);
						if(msg.code=="0"){
							layer.msg(msg.msg, { icon: 6, time: 800},function(){
								location.reload();
							});
						}else{
							layer.msg(msg.msg, {icon: 5});
						}	
					}); 
            });
            return false;//return false是阻止提交
        });
        //监听弹出框表单提交，massage是修改界面的表单数据
        function setFormValue(obj,data){
            form.on('submit(update)', function(message) {
          	  $.ajax({
                url:'<%=Const.ROOT%>kaoqin/update',
					type : 'POST',
					data : message.field,
					dataType:"JSON",
					success : function(msg) {
						layer.closeAll();
						if(msg.code=="0"){
							layer.msg(msg.msg, { icon: 6, time: 1000},function(){
									//	table.reload('currentTableId');//数据表格重
									layer.closeAll();//关闭弹出层
									location.reload();
								});
							} else {
								layer.msg(msg.msg, {
									icon : 5
								});
							}
						}
					});
					return false;
				})
			}
      
		});
	 
	</script>
	<script type="text/html" id="no">
    {{d.LAY_TABLE_INDEX+1}}
	</script>

</body>
</html>