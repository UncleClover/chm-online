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
                <li style="width:258px;" ><a class="current" href="javascript:void(0);" id="context_tab">CHM列表</a></li>
            </ul>
            <ul class="index-ul" id="context">
                <li class="item"><a href="javascript:void(0);">click</a></li>
                <li class="item"><a href="javascript:void(0);">bind</a></li>
            </ul>
        </div>
    </nav>
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <span><i class="fa fa-tasks fa-fw"></i>添加新的CHM文件</span>
                        </div>
                        <div class="panel-body">
                            <div style="padding:0 5px 10px 0px;">
                            	<span style="font-weight:bold;font-size:10px;">CHM标题：</span>
                            	<input class="index-input" style="width:80%;" type="txt" name="title" id="title"/>
                            </div>
                               <textarea id="kindeditor" name="content"></textarea>
                               <div style="padding-top:10px;"><a href="javascript:void(0);" class="btn btn-info btn-block" id="save">保存CHM</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#-- 初始化设置页面对应的JS文件路径 -->
<script>
	var pageUrl = "/ui-frame/script/article/add";
</script>
<#-- 引入sea.js -->
<#include "/common/ui-frame.ftl">
</#escape>