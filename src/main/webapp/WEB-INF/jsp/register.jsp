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
    <title>注册博主</title>
    <base id="basePath" href="<%=basePath%>">
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <script src="<%=basePath%>js/jquery.min.js?v=<%=ToolUtil.getFileTime(application.getRealPath("js/jquery.min.js"))%>"></script>
</head>
<body>
<div class="header">
    <span>boruonuo</span>
    <span>boruonuo</span>
    <span>boruonuo</span>
</div>

<!--//主题部分-->
<div class="main">
    <div class="reg-title">
        <p class="title">
            企业注册
        </p>
    </div>
    <div class="left-main">
        <div class="user-info">
            <div class="left-header">
                <span class="title">登录信息</span>
            </div>
            <div class="form-group">
                <p class="input-group">
                    <label for="nickname">登录用户名</label>
                    <input type="text" class="input" name="nickname" id="nickname" placeholder="登录用户名">
                    <i class="fa fa-check-circle" id="nickname-i"></i>
                </p>
                <p class="input-group">
                    <label for="password">设置密码</label>
                    <input type="password" class="input" name="password" id="password" placeholder="密码">
                    <i class="fa fa-check-circle" id="password-i"></i>
                </p>
                <p class="input-group">
                    <label for="confirmPwd">确认密码</label>
                    <input type="password" class="input" name="confirmPwd" id="confirmPwd" placeholder="确认密码">
                    <i class="fa fa-check-circle" id="confirmPwd-i"></i>
                    <span class="danger-tip">两次密码输入不一致</span>
                </p>
            </div>
        </div>
        <div class="company-basic">
            <div class="left-header">
                <span class="title">企业基本信息</span>
            </div>
            <div class="form-group">
                <p class="input-group">
                    <label for="company">单位名称</label>
                    <input type="text" class="input" name="company" id="company" placeholder="请输入您的单位名称">
                    <i class="fa fa-check-circle" id="company-i"></i>
                </p>
                <p class="input-group">
                    <label for="companyID">组织机构代码</label>
                    <input type="text" class="input" name="companyID" id="companyID" placeholder="组织机构代码或者统一社会信用代码">
                    <i class="fa fa-check-circle" id="companyID-i"></i>
                </p>
                <p class="input-group">
                    <label for="area">所属街道/园区</label>
                    <select class="data-msg" id="area"
                            style="width: 250px;height: 40px;border: 1px solid lightgrey;border-radius: 5px;padding-left: 5px;font-size: 13px;" onchange="incubatorChange()">
                        <option value="0">直属</option>
                        <c:forEach items="${areas}" var="areas">
                            <option value="${areas.id}">${areas.value}</option>
                        </c:forEach>
                    </select>
                    <i class="fa fa-check-circle" id="area-i"></i>
                </p>
                <%--孵化器：胡峰熙后期修改--%>
                <p class="input-group input-group1" style="display:none; " id="incubatorManage">
                    <label for="area">所属孵化器</label>
                    <select class="data-msg" id="incubator"
                            style="width: 250px;height: 40px;border: 1px solid lightgrey;border-radius: 5px;padding-left: 5px;font-size: 13px;">
                    </select>
                </p>
                <p class="input-group">
                    <label for="username">联系人姓名</label>
                    <input type="text" class="input" name="username" id="username" placeholder="请输入联系人的姓名">
                    <i class="fa fa-check-circle" id="username-i"></i>
                </p>
                <p class="input-group">
                    <label for="telephone">联系人手机</label>
                    <input type="text" class="input" name="telephone" id="telephone" placeholder="建议使用常用手机">
                    <i class="fa fa-check-circle" id="telephone-i"></i>
                    <span class="danger-tip"></span>
                    <button type="button" onclick="sendTelCode()" id="sendcode"
                            style="width:80px;margin-left: 5px;height:30px;">发送验证码
                    </button>
                </p>

                <p class="input-group">
                    <label for="telcode">短信验证码</label>
                    <input type="text" class="input" name="telcode" id="telcode" placeholder="短信验证码">
                    <i class="fa fa-check-circle" id="telcode-i"></i>
                    <span class="danger-tip"></span>
                </p>
                <span class="danger-tip"></span>
                <p class="input-group">
                    <label for="email">联系人邮箱</label>
                    <input type="text" class="input" name="eamil" id="email" placeholder="建议使用常用邮箱">
                    <i class="fa fa-check-circle" id="email-i"></i>
                    <span class="danger-tip"></span>
                </p>
                <p class="input-group">
                    <label for="leaderName">法人姓名</label>
                    <input type="text" class="input" name="leaderName" id="leaderName" placeholder="请输入公司的法人姓名">
                    <i class="fa fa-check-circle" id="leaderName-i"></i>
                </p>
                <p class="input-group">
                    <label for="leaderTele">法人手机</label>
                    <input type="text" class="input" name="leaderTele" id="leaderTele" placeholder="建议使用常用手机">
                    <i class="fa fa-check-circle" id="leaderTele-i"></i>
                    <span class="danger-tip"></span>
                </p>
            </div>
        </div>
        <%--<p>--%>
        <%--<input type="checkbox" name="state" id="checkbox"><span>阅读并同意<a href="#">《拱墅区科技综合服务平台企业用户注册协议》</a></span>--%>
        <%--</p>--%>
        <button type="button" class="reg" onclick="registerjudge()">注册</button>
    </div>
    <div class="right-main">
        <div class="right-header">
            <span class="title">填写须知</span>
        </div>
        <div class="tips">
            <ol>
                <li>为了尽快并有效的完成您的提交业务，请填写真实信息</li>
                <li>带（<span>*</span>）为必选项目</li>
                <li>企业只能注册一个企业用户，请谨慎填写</li>
                <li>注册成功后，请前往完成企业信息维护</li>
            </ol>
        </div>
    </div>
</div>
</body>
<script>
    var basepath = "<%=basePath%>";
    incubatorChange();
    function incubatorChange(){
        var area=$("#area").find("option:selected").val();
        $.ajax({
            url: basepath + "incubator",
            type: "get",
            dataType: "json",
            data: {
                parentId: area
            },
            success: function (data) {
                var html = '<option value="0">请选择所属孵化器，没有可不选</option>';
                if (data.total == 0) {
                    $("#incubatorManage").show();

                } else {
                    $("#incubatorManage").show();
                    for(var i=0;i<data.total;i++){
                        html=html+'<option value="'+data.rows[i].id+'">'+data.rows[i].value+'</option>'
                    }
                }
                $("#incubator").html(html);
            }, error: function (err) {
                $("#incubatorManage").show();
                var html='<option value="0" ></option>';
                $("#incubator").html(html);
            }
        });
    }
</script>
</html>
