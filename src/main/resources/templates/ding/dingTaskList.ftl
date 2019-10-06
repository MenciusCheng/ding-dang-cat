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
                    <span>钉钉报名任务</span>
                    <a class="pull-right" href="/ding/dingTask/config" role="button">发布任务</a>
                </div>
                <div class="list-group">
                    <#list dingTaskList as item>
                        <a class="list-group-item" href="/ding/dingTask/info?dingTaskId=${item.id}">
                            <h4 class="list-group-item-heading">
                                <span>${item.name}</span>
                                <span class="label label-success"><#if item.enabled == 0>未启用<#else>已启用</#if></span>
                                <span class="label label-success"><#if item.applyStatus == 1>未开始<#elseif item.applyStatus == 2>进行中<#elseif item.applyStatus == 3>已结束<#elseif item.applyStatus == 4>已停止</#if></span>
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

                    <a class="list-group-item" href="/ding/dingTask/info?dingTaskId=1">
                        <h4 class="list-group-item-heading">钉钉加班餐 <span class="label label-success">已启用</span> <span
                                class="label label-success">进行中</span></h4>
                        <p class="list-group-item-text">
                            <span>时间：<span>12:00 ~ 16:00</span></span>
                            <span>|</span>
                            <span>重复：<span>工作日</span></span>
                        </p>
                    </a>
                    <a class="list-group-item" href="/ding/dingTask/info?dingTaskId=2">
                        <h4 class="list-group-item-heading">水果下午茶 <span class="label label-default">未启用</span> <span
                                class="label label-default">已停止</span></h4>
                        <p class="list-group-item-text">
                            <span>时间：<span>12:00 ~ 16:00</span></span>
                            <span>|</span>
                            重复：<span>一次</span>
                        </p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>