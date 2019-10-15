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
                        <span>报名人员（${applyDate}）</span>
                        <a class="pull-right" href="/ding/dingTask/history/list?dingTaskId=${dingTask.id}" role="button">查看历史</a>
                    </div>
                    <div class="list-group">
                        <#list applyStaffList as item>
                            <li class="list-group-item"><span>${item_index + 1}.</span><span class="<#if myApplyInfo?? && item.staffId == myApplyInfo.staffId>bold</#if>">${item.staffName}</span><#if item.remark?length != 0 > <span>备注：${item.remark}</span></#if></li>
                        </#list>
                        <#if applyStaffList?size == 0>
                            <li class="list-group-item"><span>无人报名</span></li>
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