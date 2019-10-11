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
                <div class="panel-heading">修改密码</div>
                <div class="panel-body">
                    <form id="passwordForm" class="form-horizontal" action="/user/updatePassword" method="post">
                        <div id="newPasswordGroup" class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">新的密码</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="newPassword" name="newPassword" maxlength="60">
                            </div>
                        </div>
                        <div id="confirmPasswordGroup" class="form-group">
                            <label for="confirmPassword" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="confirmPassword" maxlength="60">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-sm-offset-2 col-sm-8">
                            <a id="confirmButton" class="btn btn-success" href="javascript:void(0)" role="button">提交</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#confirmPassword").on('input propertychange', function () {
            if ($("#newPassword").val() === $("#confirmPassword").val()) {
                $("#confirmPasswordGroup").removeClass("has-error");
            } else {
                $("#confirmPasswordGroup").addClass("has-error");
            }
        });
        $("#confirmButton").click(function () {
            confirm();
        });
    });

    function confirm() {
        if (validate()) {
            var password = $("#newPassword").val();
            $("#newPassword").val(md5(password));
            $("#passwordForm").submit();
        }
    }

    function validate() {
        var validated = true;
        if ($("#newPassword").val() !== $("#confirmPassword").val()) {
            validated = false;
        }
        return validated;
    }
</script>
</body>
</html>