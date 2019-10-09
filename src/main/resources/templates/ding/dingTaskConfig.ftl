<!DOCTYPE html>
<html lang="zh-CN">
<#include "../common/head.ftl">
<body>
<#include "../common/nav.ftl">

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-info">
                <div class="panel-heading">配置</div>
                <div class="panel-body">
                    <form id="configForm" class="form-horizontal" action="/ding/dingTask/save" method="post">
                        <#if dingTask??>
                            <input type="hidden" name="id" value="${dingTask.id}">
                        </#if>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">任务名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="name" name="name" value="${dingTask.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startAt" class="col-sm-2 control-label">开始时间</label>
                            <div class="col-sm-8">
                                <input type="time" class="form-control" id="startAt" name="startAt" value="${dingTask.startAt}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="endAt" class="col-sm-2 control-label">结束时间</label>
                            <div class="col-sm-8">
                                <input type="time" class="form-control" id="endAt" name="endAt" value="${dingTask.endAt}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="maxCount" class="col-sm-2 control-label">人数上限</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="maxCount" name="maxCount" value="${dingTask.maxCount}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">重复次数</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="repeatType" id="repeatType1" value="1" checked> 一次（今天）
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="repeatType" id="repeatType2" value="2"> 工作日
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="repeatType" id="repeatType3" value="3"> 每周五
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否启用</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="enabled" id="enabled1" value="1" checked> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="enabled" id="enabled2" value="2"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="managerId" class="col-sm-2 control-label">管理人员</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="managerId" name="managerId">
                                    <option value="1">威威猫</option>
                                    <option value="2">叽叽喳</option>
                                    <option value="3">猪猪侠</option>
                                    <option value="4">透明人</option>
                                    <option value="5">小黑</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">报名描述</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" id="description" name="description" rows="5" placeholder="发送提醒的内容">${dingTask.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dingWebhook" class="col-sm-2 control-label">钉钉地址</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="dingWebhook" name="dingWebhook" placeholder="钉钉机器人 webhook 地址，为空时无法发送提醒" value="${dingTask.dingWebhook}">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-sm-offset-2 col-sm-8">
                            <a id="saveButton" class="btn btn-success" href="javascript:void(0)" role="button">保存</a>
                            <a class="btn btn-warning" href="<#if dingTask??>/ding/dingTask/info?dingTaskId=${dingTask.getId()}<#else>/ding/dingTask/all</#if>" role="button">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        // $("#username").on('input propertychange', function () {
        //     if ($("#username").val()) {
        //         $("#usernameGroup").removeClass("has-error");
        //     } else {
        //         $("#usernameGroup").addClass("has-error");
        //     }
        // });
        // $("#password").on('input propertychange', function () {
        //     if ($("#password").val()) {
        //         $("#passwordGroup").removeClass("has-error");
        //     } else {
        //         $("#passwordGroup").addClass("has-error");
        //     }
        // });
        $("#saveButton").click(function () {
            if (validateConfigForm()) {
                $("#configForm").submit();
            }
        });
    });

    function validateConfigForm() {
        var validated = true;
        if (!$("#name").val()) {
            validated = false;
        }
        if (!$("#startAt").val()) {
            validated = false;
        }
        if (!$("#endAt").val()) {
            validated = false;
        }
        if (!$("#maxCount").val()) {
            validated = false;
        }
        return validated;
    }
</script>

</body>
</html>