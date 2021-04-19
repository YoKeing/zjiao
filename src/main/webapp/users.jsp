<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户管理</title>
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
								<label class="layui-form-label">学号</label>
								<div class="layui-input-inline">
									<input type="text" name="no" autocomplete="off"
										class="layui-input">
								</div>
								<label class="layui-form-label">姓名</label>
								<div class="layui-input-inline">
									<input type="text" name="name" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<a class="layui-btn" lay-submit="" lay-filter="data-search-btn">搜索</a>
							</div>
							<div class="layui-inline">
								<button type="button"
									class="layui-btn layui-btn-normal data-add-btn">
									<i class="layui-icon layui-icon-add-1"></i>添加
								</button>
							</div>
						</div>
					</form>
				</div>
			</fieldset>
			<table class="layui-hide" id="currentTableId"
				lay-filter="currentTableFilter"></table>
			<script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>
		</div>
	</div>

<div class="layuimini-container" id="popAdd" style="display: none;">
    <div class="layuimini-main">
        <form class="layui-form" action="" lay-filter="aexample">
        	<input type="hidden" name="role" value="2"/>
			<div class="layui-form-item">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-block">
                    <input type="text" name="no" lay-verify="required|no" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
            </div>        	
        	<div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="必填项，不能为空" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" id="password1" lay-verify="required|password" lay-reqtext="必填项，不能为空" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>    
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="repass" lay-verify="required|repass1" autocomplete="off" placeholder="请输入确认密码" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
           
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="tel" name="phone" lay-verify="required|phone" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男" checked/>
                    <input type="radio" name="sex" value="女" title="女"/>
                </div>
            </div>
           
            <div class="layui-form-item">
                <label class="layui-form-label">专业</label>
                <div class="layui-input-block">
                    <input type="text" name="zhuanye" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <input type="text" name="banji" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
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

<div class="layuimini-container" id="popUpdate" style="display: none;">
    <div class="layuimini-main">
        <form class="layui-form" action="" lay-filter="uexample">
        	<input type="hidden" name="id"/> 
        	<div class="layui-form-item">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-block">
                    <input type="text" name="no" disabled lay-verify="required|no" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
        	<div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" disabled lay-verify="required" lay-reqtext="必填项，不能为空" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" id="password2" lay-verify="required|password" lay-reqtext="必填项，不能为空" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>    
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="repass" lay-verify="required|repass2" autocomplete="off" placeholder="请输入确认密码" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
           
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="tel" name="phone" lay-verify="required|phone" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男" checked/>
                    <input type="radio" name="sex" value="女" title="女"/>
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">专业</label>
                <div class="layui-input-block">
                    <input type="text" name="zhuanye" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <input type="text" name="banji" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            
           
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="update">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form> 
</div>
</div>
	<script src="<%=Const.ROOT%>lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
	<script>
	layui.use(['form', 'table','laydate','upload'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        	laydate = layui.laydate;
       		upload = layui.upload;
       		
       		laydate.render({
       			elem:"#optime1"
       		});
       		laydate.render({
       			elem:"#optime2"
       		});
       		
        /*    $.getJSON("<%=Const.ROOT%>dept/jsonlist",function(data){
            	$("#did1").html("<option value=''>请选择</option>");
            	$("#did2").html("<option value=''>请选择</option>");
    			for(i=0;i<data.length;i++){
    				$("#did1").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    				$("#did2").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    			}
    			form.render('select');//必须渲染一下
    		});
           */ 

        table.render({
            elem: '#currentTableId',
            url: '<%=Const.ROOT%>users/list?role=2',
            cols: [[
				{field:'id',hide:true,width: 0},
				{width: 100, templet:'#no',title: 'NO', sort: true},
                {field: 'no', width: 150, title: '学号'},
                {field: 'username', width: 150, title: '用户名'},
                {field: 'name', width: 150, title: '姓名'},
                {field: 'sex', width: 100, title: '性别'},
                {field: 'phone', width: 150, title: '电话'},
                {field: 'zhuanye', width: 150, title: '专业'},
                {field: 'banji', width: 150, title: '班级'},
                {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true
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

        // 监听添加操作
        $(".data-add-btn").on("click", function () {
        	var addIndex=layer.open({
        		title:"新增",
        		area: ['700px', 'auto'],
        		type: 1, 
        		content: $("#popAdd")
        	});
        });
	
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
            $.post("<%=Const.ROOT%>users/add",data.field,function(data){
            	if(data.code=="0"){
					layer.msg(data.msg, { icon: 6, time: 800},function(){
						//table.reload('currentTableId');//数据表格重
						location.reload();
						layer.closeAll();//关闭弹出层
					});
				}else{
					layer.msg(data.msg, {icon: 5});
				}	
            });
            return false;//return false是阻止提交
        });
        
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
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
               form.val('uexample', {"repass":data.password});
               //动态向表传递赋值当然也是异步请求的要数据的修改数据的获取
               setFormValue(obj,data);    
            }else if (obj.event === 'shenhe') {
            	layer.confirm('真的审核通过吗？', function (index) {
                    $.getJSON('<%=Const.ROOT%>users/update',{'id':obj.data.id,'status':'已审核'},function(msg) {
  						if(msg.code=="0"){
  							layer.msg(msg.msg, { icon: 6, time: 800},function(){
  								location.reload();
  							});
  						}else{
  							layer.msg(msg.msg, {icon: 5});
  						}	
  					}); 
                });   
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么？', function (index) {
                    $.getJSON('<%=Const.ROOT%>users/delete',{'id':obj.data.id},function(msg) {
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
        
        //验证
      //自定义验证规则
        form.verify({
        	no: function (value) {
                if (value.length<6 ) {
                    return '学号长度必须大于等于6位';
                }
            },
            username: function (value) {
                if (value.length < 2) {
                    return '用户名长度必须大于等于2';
                }
            }
       		, password: [
                 /^[\S]{6,12}$/
                 , '密码必须6到12位'
             ]
             ,repass1: function(value) {
               	//获取密码
               	var pass = $("#password1").val();
              	if(pass!=value) {
              		return '两次输入的密码不一致';
              	}
              }
       		,repass2: function(value) {
               	//获取密码
               	var pass = $("#password2").val();
              	if(pass!=value) {
              		return '两次输入的密码不一致';
              	}
              }
            , phone: [
                 /^[\d]{11}$/
                 , '电话必须11位'
             ]
        });
        
        //监听弹出框表单提交，massage是修改界面的表单数据
          function setFormValue(obj,data){
              form.on('submit(update)', function(message) {
            	  //console.log(message);
            	  $.ajax({
                  url:'<%=Const.ROOT%>users/update',
					type : 'POST',
					data : message.field,
					dataType:"JSON",
					success : function(msg) {
						console.log(msg);
						layer.closeAll();
						if(msg.code=="0"){
							layer.msg(msg.msg, { icon: 6, time: 800},function(){
								table.reload('currentTableId');//数据表格重
								layer.closeAll();//关闭弹出层
							});
						}else{
							layer.msg(msg.msg, {icon: 5});
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