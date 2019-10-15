<!DOCTYPE html>
<html lang="zh-CN">
<#include "../common/head.ftl">
<body>
<#include "../common/nav.ftl">

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <span>报名信息</span>
                </div>
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
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <span>历史报名</span>
                        <a class="pull-right" href="/ding/dingTask/info?dingTaskId=${dingTask.id}" role="button">查看今天</a>
                    </div>
                    <div class="list-group">
                        <#list dingTaskApplies as item>
                            <#if todayDate != item.applyDate>
                                <li class="list-group-item"><a href="/ding/dingTask/history/info?dingTaskId=${dingTask.id}&applyDate=${item.applyDate}">${item.applyDate}</a></li>
                            </#if>
                        </#list>
                        <#if dingTaskApplies?size == 0>
                            <li class="list-group-item">
                                <a href="/ding/dingTask/info?dingTaskId=${dingTask.id}">${todayDate}</a>
                            </li>
                        <#else>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>

<script>
    $(function () {
    });
</script>

</body>
</html>