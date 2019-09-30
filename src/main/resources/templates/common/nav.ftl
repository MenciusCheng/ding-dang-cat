<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="<#if page?? && page == "home">active</#if>"><a href="/ding/dingTaskList">首页</a></li>
                <li role="presentation" class="<#if page?? && page == "about">active</#if>"><a href="/common/about">关于</a></li>
                <li role="presentation" class="<#if page?? && page == "login">active</#if>">
                    <#if account??>
                        <a href="/user/userCenter">${account.username}</a>
                    <#else>
                        <a href="/user/login">登录</a>
                    </#if>
                </li>
            </ul>
        </nav>
        <h3 class="text-muted">
            <img src="../../assets/img/phone_cat.png" alt="phone_cat" height="32" width="32">
            <span>叮当猫</span>
        </h3>
    </div>
</div>