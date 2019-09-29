<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>叮当猫 - 欢迎登录</title>

    <link href="../../lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="../../assets/css/jumbotron-narrow.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="../ding/dingTaskList.html">首页</a></li>
                <li role="presentation"><a href="../common/about.html">关于</a></li>
                <li role="presentation" class="active"><a href="#">登录</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">
            <img src="../../assets/img/phone_cat.png" alt="phone_cat" height="32" width="32">
            <span>叮当猫</span>
        </h3>
    </div>
</div>

<div class="container">
    <div id="usernameAlert" class="alert alert-danger hidden" role="alert">账号名称不能为空</div>
    <div id="passwordAlert" class="alert alert-danger hidden" role="alert">账号密码不能为空</div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-info">
                <div class="panel-heading">欢迎登录</div>
                <div class="panel-body">
                    <form id="loginForm" class="form-horizontal" action="/user/login" method="post">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">账号名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="username" name="username">
                            </div>
                        </div>
                        <div class="form-group">
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

<script src="../../lib/jquery-1.12.4-dist/jquery.min.js"></script>
<script src="../../lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="../../lib/md5/md5.min.js"></script>

<script>
$(function () {
    $("#loginButton").click(function () {
        if (validateLoginForm()) {
            var password = $("#password").val();
            $("#password").val(md5(password));
            $("#loginForm").submit();
        }
    })
});

function validateLoginForm() {
    var validated = true;
    if (!$("#username").val()) {
        $("#usernameAlert").removeClass("hidden");
        validated = false;
    }
    if (!$("#password").val()) {
        $("#passwordAlert").removeClass("hidden");
        validated = false;
    }
    return validated;
}
</script>
</body>
</html>