<#escape x as x?html>
<#include "/common/header.ftl">
<div id="wrapper">
	<#-- 左侧导航 -->
 	<#include "/common/left-nav.ftl">
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
                            <div class="list-group" id="hot_list"></div>
                            <a href="javascript:void(0);" class="btn btn-info btn-block">MORE&nbsp;>></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#-- 常用列表模板 -->
<#-- 目录模板 -->
<script id="hotContentTemplate" type="type/x-jsrender">
	{{for}}
		<a href="javascript:void(0);" class="list-group-item">
        	<i class="fa fa-hand-o-right fa-fw"></i>{{:title}}<span class="pull-right text-muted small"><em>10:57</em></span>
        </a>
	{{/for}}
</script>
<#-- 初始化设置页面对应的JS文件路径 -->
<script>
	var pageUrl = "/ui-frame/script/index/index";
</script>
<#-- 引入sea.js -->
<#include "/common/ui-frame.ftl">
</#escape>