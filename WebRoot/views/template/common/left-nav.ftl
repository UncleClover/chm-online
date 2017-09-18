<#escape x as x?html>
<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <a class="navbar-brand" href="/chm.action">CHM-ONLINE</a>
    </div>
    <div class="header-right">
        <a href="javascript:void(0);" class="btn btn-danger" title="退出"><i class="fa fa-dot-circle-o fa-2x"></i></a>
        <a href="javascript:void(0);" class="btn" title="个人中心"><img src="/ui-frame/img/user.png" class="img-thumbnail" width="40px" height="40px"/></i></a>
    </div>
</nav>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li><a class="current" href="javascript:void(0);" id="context_tab">目录</a></li>
            <li><a href="javascript:void(0);" id="index_tab">索引</a></li>
            <li><a href="javascript:void(0);" id="search_tab">搜索</a></li>
        </ul>
        <ul class="index-ul" id="context"></ul>
        <ul class="index-ul none" id="index">
            <li><input id="index-input" class="index-input" type="txt" /></li>
            <li class="item"><a href="javascript:void(0);">not</a></li>
            <li class="item"><a href="javascript:void(0);">function</a></li>
        </ul>
        <ul class="index-ul none" id="search">
            <li><input id="search-input" class="index-input" type="txt" /></li>
            <li class="item"><a href="javascript:void(0);">hover</a></li>
            <li class="item"><a href="javascript:void(0);">toggle</a></li>
            <li class="item"><a href="javascript:void(0);">hover</a></li>
            <li class="item"><a href="javascript:void(0);">toggle</a></li>
            <li class="item"><a href="javascript:void(0);">hover</a></li>
            <li class="item"><a href="javascript:void(0);">toggle</a></li>
            <li class="item"><a href="javascript:void(0);">hover</a></li>
            <li class="item"><a href="javascript:void(0);">toggle</a></li>
        </ul>
    </div>
</nav>

<#-- 目录模板 -->
<script id="contentTemplate" type="type/x-jsrender">
	{{for}}<li class="item"><a href="/article/detail?articleId={{:id}}" target="_blank">{{:title}}</a></li>{{/for}}
</script>
</#escape>