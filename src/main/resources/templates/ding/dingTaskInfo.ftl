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
                    <h4>钉钉加班餐 <span class="label label-success">已启用</span> <span class="label label-success">进行中</span></h4>
                    <p><strong>报名时间：</strong>12:00 ~ 16:00</p>
                    <p><strong>人数上限：</strong>30</p>
                    <p><strong>重复次数：</strong>一次（今天）</p>
                    <p><strong>管理人员：</strong>威威猫</p>
                    <pre>晚上需要加班餐的小伙伴开始报名啦~，报名截止时间15:30。
请点击下方链接报名。
Start signing up for overtime meals,finish at 15:30
Please click the link below to apply。</pre>
                    <!--<dl class="dl-horizontal">-->
                        <!--<dt>报名时间</dt><dd>12:00 ~ 16:00</dd>-->
                        <!--<dt>人数上限</dt><dd>30</dd>-->
                        <!--<dt>有效日期</dt><dd>一次（今天）</dd>-->
                        <!--<dt>管理人员</dt><dd>威威猫</dd>-->
                        <!--<dt></dt><dd>晚上需要加班餐的小伙伴开始报名啦~，报名截止时间15:30。-->
                        <!--请点击下方链接报名。-->
                        <!--Start signing up for overtime meals,finish at 15:30-->
                        <!--Please click the link below to apply。</dd>-->
                    <!--</dl>-->
                </div>
                <div class="panel-footer">
                    <!--<a class="btn btn-success" href="#" role="button">立即报名</a>-->
                    <a class="btn btn-success" href="#" role="button">更新备注</a>
                    <a class="btn btn-warning" href="#" role="button">取消报名</a>
                    <a class="btn btn-info pull-right" href="/ding/dingTask/config?id=1" role="button">配置</a>
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