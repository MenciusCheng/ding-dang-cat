<!DOCTYPE html>
<html lang="zh-CN">
<#include "../common/head.ftl">
<body>
<#include "../common/nav.ftl">

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-info">
                <div class="panel-heading">个人中心</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">账号名称</label>
                            <div class="col-sm-8">
                                <p class="form-control-static">${account.username}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">手机号码</label>
                            <div class="col-sm-8">
                                <p class="form-control-static"><#if account.phone??>${account.phone}</#if></p>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-sm-offset-2 col-sm-8">
                            <a id="logoutButton" class="btn btn-warning" href="javascript:void(0)" role="button">退出登录</a>
                            <a class="btn btn-danger" href="/user/updatePassword" role="button">修改密码</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<form id="logoutForm" class="hidden" action="/user/logout" method="post"></form>

<script>
    $(function () {
        $("#logoutButton").click(function () {
            $("#logoutForm").submit();
        })
    });
</script>
</body>
</html>