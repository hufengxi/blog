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
    <title>用户注册</title>
    <base id="basePath" href="<%=basePath%>">
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <script src="<%=basePath%>js/jquery.min.js?v=<%=ToolUtil.getFileTime(application.getRealPath("js/jquery.min.js"))%>"></script>
    <link rel="stylesheet"
          href="<%=basePath%>css/reg.css?v=<%=ToolUtil.getFileTime(application.getRealPath("css/reg.css"))%>">
    <link rel="stylesheet"
          href="<%=basePath%>css/common.css?v=<%=ToolUtil.getFileTime(application.getRealPath("css/common.css"))%>">
    <link rel="stylesheet"
          href="<%=basePath%>css/register.css?v=<%=ToolUtil.getFileTime(application.getRealPath("css/register.css"))%>">
    <link rel="stylesheet"
          href="<%=basePath%>css/bootstrap.min.css?v=<%=ToolUtil.getFileTime(application.getRealPath("css/bootstrap.css"))%>">

</head>
<body id="register">
<div class="nav-box">
    <div style="height: 54px;line-height: 54px;width: 1100px;margin: 0px auto;">
        <span class="company_name" style="font-size: 30px;">boruonuo</span>
        <span class="user cursor" style="display:inline-block;float:right;padding-left: 25px;background-position: 0px 18px;" onclick="window.location.href='login'">登录</span>
    </div>
</div>

<div class="register_border" style="width: 500px;margin: 50px auto 0px;padding: 20px;">
    <div class="user-info">
        <div style="height:70px;width:100%;">
            <span id="title">注册新用户</span>
        </div>
        <div class="form-group">
            <p class="input-group">
                <label for="nickname">昵称</label>
                <input type="text" class="input" name="nickname" id="nickname" placeholder="昵称">
                <span class="danger-tip"></span>
            </p>
            <p class="input-group">
                <label for="mobile">手机号</label>
                <input type="text" class="input" name="mobile" id="mobile" placeholder="手机号">
                <span class="danger-tip"></span>
            </p>
            <p class="input-group">
                <label for="email">邮箱</label>
                <input type="text" class="input" name="email" id="email" placeholder="邮箱">
                <span class="danger-tip"></span>
            </p>
            <p class="input-group">
                <label for="password">设置密码</label>
                <input type="password" class="input" name="password" id="password" placeholder="密码">
                <span class="danger-tip"></span>
            </p>
            <p class="input-group">
                <label for="confirmPwd">确认密码</label>
                <input type="password" class="input" name="confirmPwd" id="confirmPwd" placeholder="确认密码">
                <span class="danger-tip"></span>
            </p>
        </div>
        <button type="button" class="btn_register" onclick="registerNow()">注册</button>
        <button class="btn_login" onclick="window.location.href='login'">立即登录</button>
    </div>
</div>

</body>
<script>
    var baseDomain = "<%=basePath%>";

    function checkAll(){
        if ($("#nickname").val() === "") {
            $("#nickname").parent().children("span").html("请输入昵称");
            $("#nickname").parent().children("span").css({"float": "right", "display": "inline"});
            return false;
        }else if($("#mobile").val().length != 11 || isNaN($("#mobile").val())){
            $("#mobile").parent().children("span").html("请填写规范手机号");
            $("#mobile").parent().children("span").css({"float":"right","display":"inline"});
            return false;
        }else if(checkEmail($("#email").val()) == ""){
            $("#email").parent().children("span").html("请填写规范邮箱");
            $("#email").parent().children("span").css({"float":"right","display":"inline"});
            return false;
        }else if($("#password").val() === ""){
            $("#password").parent().children("span").html("请填写密码");
            $("#password").parent().children("span").css({"float":"right","display":"inline"});
            return false;
        }else if($("#confirmPwd").val() === ""){
            $("#confirmPwd").parent().children("span").html("请确认密码");
            $("#confirmPwd").parent().children("span").css({"float":"right","display":"inline"});
            return false;
        }else if($("#password").val() != $("#confirmPwd").val()) {
            $("#confirmPwd").parent().children("span").html("两次密码输入不一致");
            $("#confirmPwd").parent().children("span").css({"float":"right","display":"inline"});
            return false;
        }
        return true;
    }

    function checkEmail(str) {
        var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if(re.test(str)){
            return true;
        }else{
            return false;
        }
    }

    function registerNow() {
        if(!checkAll()){
            return;
        }

        $.ajax({
            url: baseDomain + "users/register",
            type: "post",
            dataType: "json",
            data:{
                nickName:$("#nickname").val(),
                mobile:$("#mobile").val(),
                email:$("#email").val(),
                pwd:$("#password").val()
            },success:function(data){
                if(data.status == 0){
                    alert("注册成功");
                    window.location.href = "login";
                }if(data.status == 1){
                    alert(data.msg);
                }
            },error:function (err) {
                alert(err);
            }
        });
    }

</script>
</html>
