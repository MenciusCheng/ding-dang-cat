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
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">任务名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="name" value="钉钉加班餐">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startAt" class="col-sm-2 control-label">开始时间</label>
                            <div class="col-sm-8">
                                <input type="time" class="form-control" id="startAt">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="endAt" class="col-sm-2 control-label">结束时间</label>
                            <div class="col-sm-8">
                                <input type="time" class="form-control" id="endAt">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="maxUser" class="col-sm-2 control-label">人数上限</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="maxUser" value="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">重复次数</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="repeat" id="inlineRadio1" value="option1" checked> 一次（今天）
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="repeat" id="inlineRadio2" value="option2"> 工作日
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="repeat" id="inlineRadio3" value="option3"> 每周五
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否启用</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="isOpen" id="isOpen1" value="1" checked> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isOpen" id="isOpen2" value="2"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="manager" class="col-sm-2 control-label">管理人员</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="manager">
                                    <option>威威猫</option>
                                    <option>叽叽喳</option>
                                    <option>猪猪侠</option>
                                    <option>透明人</option>
                                    <option>小黑</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">报名描述</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" id="description" rows="5" placeholder="发送提醒的内容">
晚上需要加班餐的小伙伴开始报名啦~，报名截止时间15:30。
请点击下方链接报名。
Start signing up for overtime meals,finish at 15:30
Please click the link below to apply。</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dingUrl" class="col-sm-2 control-label">钉钉地址</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="dingUrl" placeholder="钉钉机器人 webhook 地址，为空时无法发送提醒">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-sm-offset-2 col-sm-8">
                            <a class="btn btn-success" href="<#if dingTask??>/ding/dingTask/info?id=${dingTask.getId()}<#else>/ding/dingTask/all</#if>" role="button">保存</a>
                            <a class="btn btn-warning" href="<#if dingTask??>/ding/dingTask/info?id=${dingTask.getId()}<#else>/ding/dingTask/all</#if>" role="button">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>