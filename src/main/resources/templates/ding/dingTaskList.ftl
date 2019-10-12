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
                    <span>报名列表</span>
                    <#if account?? && (account.roles?seq_contains("管理员") || account.permissions?seq_contains("发布任务"))>
                        <a class="pull-right" href="/ding/dingTask/config" role="button">发布</a>
                    </#if>
                </div>
                <div class="list-group">
                    <#list dingTaskList as item>
                        <a class="list-group-item" href="/ding/dingTask/info?dingTaskId=${item.id}">
                            <h4 class="list-group-item-heading">
                                <span>${item.name}</span>
                                <#if item.enabled == 0><span class="label label-default">未启用</span><#else><span class="label label-success">已启用</span></#if>
                                <#if item.applyStatus == 1><span class="label label-default">未开始</span><#elseif item.applyStatus == 2><span class="label label-success">进行中</span><#elseif item.applyStatus == 3><span class="label label-default">已结束</span><#elseif item.applyStatus == 4><span class="label label-default">已停止</span></#if>
                            </h4>
                            <p class="list-group-item-text">
                                <span>
                                    <span>时间：</span>
                                    <span>${item.startAt} ~ ${item.endAt}</span>
                                </span>
                                <span>|</span>
                                <span>
                                    <span>重复：</span>
                                    <span><#if item.repeatType == 1>一次<#elseif item.repeatType == 2>工作日<#elseif item.repeatType == 3>每周五</#if></span>
                                </span>
                            </p>
                        </a>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>