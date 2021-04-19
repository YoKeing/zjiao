<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=Const.ROOT %>lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=Const.ROOT %>css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>基本资料</legend>
        </fieldset>

        <form class="layui-form" action="" lay-filter="example">  
        <input type="hidden" name="id"/>
        
           <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" disabled lay-verify="required" lay-reqtext="必填项，不能为空"  placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
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
            <c:if test="${sessionScope.role!=0 }">
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
             <c:if test="${sessionScope.role!=1 }">
             <div class="layui-form-item">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <input type="text" name="banji" lay-verify="required" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
             </c:if>
            </c:if>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="update">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
            
        </form> 
</div>
</div>

<script src="<%=Const.ROOT %>lib/layui-v2.5.4/layui.js?v=1.0.4" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
    	var $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //自定义验证规则
        form.verify({
        	phone: [
                 /^[\d]{11}$/
                 , '电话必须11位'
             ]
        });
       
        //监听提交
        form.on('submit(update)', function (data) {
            $.post("<%=Const.ROOT%>users/update",data.field,function(data){
            	layer.msg(data.msg, { icon: 6, time: 800},function(){
            			location.reload();
				});
            });
            return false;
        });
        
        $.getJSON("<%=Const.ROOT%>users/getById",{"id":'${sessionScope.users.id}'},function(data){
        	form.val('example',data);
        	form.val('example',{"did":data.did});
        });
        //表单初始赋值
    });
</script>

</body>
</html>