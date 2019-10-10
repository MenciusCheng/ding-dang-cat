<!DOCTYPE html>
<html lang="zh-CN">
<#include "../common/head.ftl">
<body>
<#include "../common/nav.ftl">

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">钉钉报名任务</div>
                <div class="panel-body">
                    <h4>
                        <span>${dingTask.name}</span>
                        <#if dingTask.enabled == 0><span class="label label-default">未启用</span><#else><span class="label label-success">已启用</span></#if>
                        <#if dingTask.applyStatus == 1><span class="label label-default">未开始</span><#elseif dingTask.applyStatus == 2><span class="label label-success">进行中</span><#elseif dingTask.applyStatus == 3><span class="label label-default">已结束</span><#elseif dingTask.applyStatus == 4><span class="label label-default">已停止</span></#if>
                    </h4>
                    <p><strong>报名时间：</strong>${dingTask.startAt} ~ ${dingTask.endAt}</p>
                    <p><strong>人数上限：</strong>${dingTask.maxCount}</p>
                    <p>
                        <strong>重复次数：</strong>
                        <span><#if dingTask.repeatType == 1>一次<#elseif dingTask.repeatType == 2>工作日<#elseif dingTask.repeatType == 3>每周五</#if></span>
                    </p>
                    <p><strong>管理人员：</strong>${dingTask.managerName}</p>
                    <p><strong>发布人员：</strong>${dingTask.createdByName}</p>
                    <pre>${dingTask.description}</pre>
                </div>
                <div class="panel-footer">
                    <#if account??>
                        <a <#if dingTask.applyStatus == 2>id="applyButton"</#if> class="btn btn-success" href="javascript:void(0)" role="button" <#if dingTask.applyStatus != 2>disabled="disabled"</#if>><#if myApplyInfo??>更新备注<#else>立即报名</#if></a>
                        <#if myApplyInfo??>
                            <a <#if dingTask.applyStatus == 2>id="cancelApplyButton"</#if> class="btn btn-warning" href="javascript:void(0)" role="button" <#if dingTask.applyStatus != 2>disabled="disabled"</#if>>取消报名</a>
                        </#if>
                    <#else>
                        <a class="btn btn-info" href="/user/login" role="button">登录报名</a>
                    </#if>
                    <#if account?? && (account.roles?seq_contains("管理员") || account.permissions?seq_contains("配置所有任务") || (account.permissions?seq_contains("配置任务") && (account.id == dingTask.managerId || account.id == dingTask.createdBy)))>
                        <a class="btn btn-info pull-right" href="/ding/dingTask/config?dingTaskId=${dingTask.getId()}" role="button">配置</a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <form id="applyForm" action="/ding/dingTask/apply" method="post">
                <input type="hidden" name="dingTaskId" value="${dingTask.id}">
                <div class="form-group">
                    <input type="text" class="form-control" id="remark" name="remark" placeholder="填写备注" value="<#if myApplyInfo??>${myApplyInfo.remark}</#if>">
                </div>
            </form>
            <form id="cancelApplyForm" action="/ding/dingTask/cancelApply" method="post" class="hidden">
                <input type="hidden" name="dingTaskId" value="${dingTask.id}">
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <#if applyStaffList?size == 0>
                <div class="panel-body">
                    <p>欢迎报名 ━(*｀∀´*)ノ亻!</p>
                </div>
            <#else>
                <div class="panel panel-warning">
                    <div class="panel-heading">报名人员</div>
                    <div class="list-group">
                        <#list applyStaffList as item>
                            <li class="list-group-item"><span>${item_index + 1}.</span><span class="<#if myApplyInfo?? && item.staffId == myApplyInfo.staffId>bold</#if>">${item.staffName}</span><#if item.remark?length != 0 > <span>备注：${item.remark}</span></#if></li>
                        </#list>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#applyButton").click(function () {
            $("#applyForm").submit();
        });
        <#if myApplyInfo??>
        $("#cancelApplyButton").click(function () {
            $("#cancelApplyForm").submit();
        });
        </#if>
    });
</script>

</body>
</html>