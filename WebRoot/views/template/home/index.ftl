<#escape x as x?html>
<#include "/common/header.ftl">
<div id="wrapper">
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
            <ul class="index-ul" id="context">
                <li class="item"><a href="javascript:void(0);">click</a></li>
                <li class="item"><a href="javascript:void(0);">bind</a></li>
            </ul>
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
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <span><i class="fa fa-tasks fa-fw"></i>常用CHM</span>
                            <span class="add_chm" id="add_chm"><i class="fa fa-plus-circle fa-fw"></i></span>
                        </div>
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:void(0);" class="list-group-item">
                                    <i class="fa fa-hand-o-right fa-fw"></i>3 New Followers
                                    <span class="pull-right text-muted small"><em>12分钟前</em></span>
                                </a>
                                <a href="javascript:void(0);" class="list-group-item">
                                    <i class="fa fa-hand-o-right fa-fw"></i>Message Sent
                                    <span class="pull-right text-muted small"><em>27分钟前</em></span>
                                </a>
                                <a href="javascript:void(0);" class="list-group-item">
                                    <i class="fa fa-hand-o-right fa-fw"></i>New Task
                                    <span class="pull-right text-muted small"><em>43分钟前</em></span>
                                </a>
                                <a href="javascript:void(0);" class="list-group-item">
                                    <i class="fa fa-hand-o-right fa-fw"></i>Server Rebooted
                                    <span class="pull-right text-muted small"><em>11:32</em></span>
                                </a>
                                <a href="javascript:void(0);" class="list-group-item">
                                    <i class="fa fa-hand-o-right fa-fw"></i>Server Crashed!
                                    <span class="pull-right text-muted small"><em>11:13</em></span>
                                </a>
                                <a href="javascript:void(0);" class="list-group-item">
                                    <i class="fa fa-hand-o-right fa-fw"></i>Server Not Responding
                                    <span class="pull-right text-muted small"><em>10:57</em></span>
                                </a>
                            </div>
                            <a href="javascript:void(0);" class="btn btn-info btn-block">MORE&nbsp;>></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    
<#-- 初始化设置页面对应的JS文件路径 -->
<script>
	var pageUrl = "/ui-frame/script/index/index";
</script>
<#-- 引入sea.js -->
<#include "/common/ui-frame.ftl">
</#escape>