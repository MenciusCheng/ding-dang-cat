<!DOCTYPE html>
<html lang="zh-CN">
<#include "../common/head.ftl">
<script src="../../lib/md5/md5.min.js"></script>
<body>
<#include "../common/nav.ftl">

<div class="container">
    <#if errorMessage??>
        <div id="passwordAlert" class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-info">
                <div class="panel-heading">欢迎登录</div>
                <div class="panel-body">
                    <form id="loginForm" class="form-horizontal" action="/user/login" method="post">
                        <div id="usernameGroup" class="form-group">
                            <label for="username" class="col-sm-2 control-label">账号名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="username" name="username">
                            </div>
                        </div>
                        <div id="passwordGroup" class="form-group">
                            <label for="password" class="col-sm-2 control-label">账号密码</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="password" name="password">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-sm-offset-2 col-sm-8">
                            <a id="loginButton" class="btn btn-success" href="#" role="button">登录</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#username").on('input propertychange', function () {
            if ($("#username").val()) {
                $("#usernameGroup").removeClass("has-error");
            } else {
                $("#usernameGroup").addClass("has-error");
            }
        });
        $("#password").on('input propertychange', function () {
            if ($("#password").val()) {
                $("#passwordGroup").removeClass("has-error");
            } else {
                $("#passwordGroup").addClass("has-error");
            }
        });
        $("#loginButton").click(function () {
            login();
        });
        $("#loginForm").keydown(function (event) {
            if (event.key === "Enter") {
                login();
            }
        })
    });

    function login() {
        if (validateLoginForm()) {
            var password = $("#password").val();
            $("#password").val(md5(password));
            $("#loginForm").submit();
        }
    }

    function validateLoginForm() {
        var validated = true;
        if (!$("#username").val()) {
            validated = false;
        }
        if (!$("#password").val()) {
            validated = false;
        }
        return validated;
    }
</script>
</body>
</html>