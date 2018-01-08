<#escape x as x?html>
<#include "/common/header.ftl">
<script src="/ui-frame/editor/kindeditor.js"></script>
<script src="/ui-frame/editor/lang/zh_CN.js"></script>
<div id="wrapper">
    <#-- 左侧导航 -->
    <#include "/common/left-nav.ftl">
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
                            	<input class="index-input" style="width:80%;" type="txt" name="title" id="title" value="<#if article.title?exists>${article.title}</#if>"/>
                            </div>
                            <textarea id="kindeditor" name="content"><#if article.content?exists>${article.content}</#if></textarea>
                            <div style="padding-top:10px;"><a href="javascript:void(0);" class="btn btn-info btn-block" id="save">保存CHM</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#if article?exists>
	<input type="hidden" id="articleId" name="articleId" value="${article.id}">
	<input type="hidden" id="update_times" name="update_times" value="${article.update_times}">
</#if>
<#-- 初始化设置页面对应的JS文件路径 -->
<script>
	var pageUrl = "/ui-frame/script/article/add";
</script>
<#-- 引入sea.js -->
<#include "/common/ui-frame.ftl">
</#escape>