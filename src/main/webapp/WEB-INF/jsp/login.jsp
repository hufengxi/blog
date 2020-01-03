<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page import="com.app.appName.util.ToolUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <base id="basePath" href="<%=basePath%>">
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <script src="<%=basePath%>js/jquery.min.js?v=<%=ToolUtil.getFileTime(application.getRealPath("js/jquery.min.js"))%>"></script>
    <link rel="stylesheet"
          href="<%=basePath%>css/common.css?v=<%=ToolUtil.getFileTime(application.getRealPath("css/common.css"))%>">
    <link rel="stylesheet"
          href="<%=basePath%>css/login.css?v=<%=ToolUtil.getFileTime(application.getRealPath("css/login.css"))%>">

</head>
<body id="login">

<div class="login_border" style="width: 350px;margin: 150px auto 0px;padding: 20px;background-color: #fff;text-align: center!important;">
    <div class="">
        <div style="height:65px;width:100%;font-weight: 320;font-size:25px;">
            <span id=""><span class="company_name">boruono&nbsp;</span>用户登录</span>
        </div>
        <div class="form-group">
            <p class="input-group" style="margin-bottom: 20px;">
                <input type="text" class="input userName" id="userName" placeholder="请输入您的手机号/邮箱">
            </p>
            <p class="input-group" style="margin-bottom: 20px;">
                <input type="password" class="input pwd" id="pwd" placeholder="请输入您的密码">
            </p>
        </div>
        <button type="button" class="btn_login" onclick="loginNow()">登录</button>
        <button class="btn_register" onclick="window.location.href='register'">用户注册</button>
    </div>
    <div style="text-align:right;padding-right:45px;margin-top:20px;">
        <a class="cursor" style="color: #4D97FF;font-size: 14px;">忘记密码</a>
    </div>
</div>

</body>
<script>
    var baseDomain = "<%=basePath%>";

    function checkAll(){
        if ($("#userName").val() === "") {
            alert("请输入用户名密码！");
            return false;
        }else if($("#pwd").val() === ""){
            alert("请输入用户名密码！");
            return false;
        }
        return true;
    }

    function loginNow() {
        if(!checkAll()){
            return;
        }

        $.ajax({
            url: baseDomain + "users/login",
            type: "post",
            dataType: "json",
            data:{
                userName: $("#userName").val(),
                pwd: $("#pwd").val()
            },
            success:function(data){
                if(data.status == 0){
                    window.location.href = "register";
                }if(data.status == 1){
                    alert("用户名或密码错误！");
                }
            },
            error:function (err) {
                alert(err);
            }
        });
    }

</script>
</html>
