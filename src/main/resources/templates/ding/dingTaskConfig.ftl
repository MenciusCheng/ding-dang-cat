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
                                <input type="text" class="form-control" id="name" name="name" maxlength="60" value="<#if dingTask??>${dingTask.name}</#if>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startAt" class="col-sm-2 control-label">开始时间</label>
                            <div class="col-sm-8">
                                <input type="time" class="form-control" id="startAt" name="startAt" value="<#if dingTask??>${dingTask.startAt}</#if>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="endAt" class="col-sm-2 control-label">结束时间</label>
                            <div class="col-sm-8">
                                <input type="time" class="form-control" id="endAt" name="endAt" value="<#if dingTask??>${dingTask.endAt}</#if>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="maxCount" class="col-sm-2 control-label">人数上限</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="maxCount" name="maxCount" maxlength="4" value="<#if dingTask??>${dingTask.maxCount}</#if>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">重复次数</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="repeatType" id="repeatType1" value="1" <#if !dingTask?? || dingTask.repeatType == 1>checked</#if>> 一次（今天）
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="repeatType" id="repeatType2" value="2" <#if dingTask?? && dingTask.repeatType == 2>checked</#if>> 工作日
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="repeatType" id="repeatType3" value="3" <#if dingTask?? && dingTask.repeatType == 3>checked</#if>> 每周五
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否启用</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="enabled" id="enabled1" value="1" <#if !dingTask?? || dingTask.enabled == 1>checked</#if>> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="enabled" id="enabled2" value="0" <#if dingTask?? && dingTask.enabled == 0>checked</#if>> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="managerId" class="col-sm-2 control-label">管理人员</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="managerId" name="managerId">
                                    <#list allAccounts as manager>
                                        <option value="${manager.id}" <#if dingTask?? && dingTask.managerId == manager.id>selected</#if>>${manager.username}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">报名描述</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" id="description" name="description" rows="5" placeholder="发送提醒的内容" maxlength="255"><#if dingTask??>${dingTask.description}</#if></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dingWebhook" class="col-sm-2 control-label">钉钉地址</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="dingWebhook" name="dingWebhook" placeholder="钉钉机器人 webhook 地址，为空时无法发送提醒" maxlength="255" value="<#if dingTask??>${dingTask.dingWebhook}</#if>">
                            </div>
                        </div>
                    </form>
                    <div id="errorMessage" class="alert alert-danger hidden" role="alert"></div>
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
        $("#saveButton").click(function () {
            if (validateConfigForm()) {
                $("#configForm").submit();
            }
        });
    });

    function validateConfigForm() {
        var validated = true;
        var message = "";
        if (!$("#name").val().trim()) {
            message += "<li>任务名称不能为空</li>";
            validated = false;
        }
        if (!$("#startAt").val()) {
            message += "<li>开始时间不能为空</li>";
            validated = false;
        } else if ($("#startAt").val() <= "00:00") {
            message += "<li>开始时间必须大于 00:00</li>";
            validated = false;
        }
        if (!$("#endAt").val()) {
            message += "<li>结束时间不能为空</li>";
            validated = false;
        } else if ($("#endAt").val() >= "23:59") {
            message += "<li>结束时间必须小于 23:59</li>";
            validated = false;
        }
        if ($("#startAt").val() && $("#endAt").val() && $("#startAt").val() > $("#endAt").val()) {
            message += "<li>开始时间必须小于等于结束时间</li>";
            validated = false;
        }
        if (!$("#maxCount").val()) {
            message += "<li>人数上限不能为空</li>";
            validated = false;
        } else if (!/^[0-9]+$/.test($("#maxCount").val())) {
            message += "<li>人数上限格式不正确</li>";
            validated = false;
        } else if ($("#maxCount").val() < 1 || $("#maxCount").val() > 10000) {
            message += "<li>人数上限范围不正确</li>";
            validated = false;
        }
        if (!validated) {
            message = "<ul>" + message + "</ul>";
            $("#errorMessage").html(message);
            $("#errorMessage").removeClass("hidden");
        }
        return validated;
    }
</script>

</body>
</html>