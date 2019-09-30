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
                        ${dingTask.name}
                        <span class="label label-success">
                            <#if dingTask.enabled == 0>未启用<#else>已启用</#if>
                        </span>
                        <span class="label label-success">
                            <#if dingTask.applyStatus == 1>
                                未开始
                            <#elseif dingTask.applyStatus == 2>
                                进行中
                            <#elseif dingTask.applyStatus == 3>
                                已结束
                            <#elseif dingTask.applyStatus == 4>
                                已停止
                            </#if>
                        </span>
                    </h4>
                    <p><strong>报名时间：</strong>${dingTask.startAt} ~ ${dingTask.endAt}</p>
                    <p><strong>人数上限：</strong>${dingTask.maxCount}</p>
                    <p>
                        <strong>重复次数：</strong>
                        <#if dingTask.repeatType == 1>
                            一次
                        <#elseif dingTask.repeatType == 2>
                            工作日
                        <#elseif dingTask.repeatType == 3>
                            每周五
                        </#if>
                    </p>
                    <p><strong>管理人员：</strong>${dingTask.managerId}</p>
                    <pre>${dingTask.description}</pre>
                </div>
                <div class="panel-footer">
                    <!--<a class="btn btn-success" href="#" role="button">立即报名</a>-->
                    <a class="btn btn-success" href="#" role="button">更新备注</a>
                    <a class="btn btn-warning" href="#" role="button">取消报名</a>
                    <a class="btn btn-info pull-right" href="/ding/dingTask/config?dingTaskId=${dingTask.getId()}" role="button">配置</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <form>
                <div class="form-group">
                    <input type="text" class="form-control" id="remark" placeholder="填写备注">
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <div class="panel panel-warning">
                <div class="panel-heading">报名人员</div>
                <div class="list-group">
                    <#list applyStaffList as item>
                        <li class="list-group-item"><span>1.</span><span>${item.getId()}（${item.getRemark()}）</span></li>
                    </#list>
                    <li class="list-group-item"><span>1.</span><span>威威猫</span></li>
                    <li class="list-group-item"><span>2.</span><span>叽叽喳</span></li>
                    <li class="list-group-item"><span>3.</span><span>猪猪侠</span></li>
                    <li class="list-group-item"><span>4.</span><span>透明人</span></li>
                    <li class="list-group-item"><span>5.</span><span>小黑</span></li>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>