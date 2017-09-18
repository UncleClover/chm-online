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
                            <span><i class="fa fa-tasks fa-fw"></i>${article.title}</span>
                            <a href="/article/update?articleId=${article.id}"><span class="add_chm" id="update_chm"><i class="fa fa-edit fa-fw" title="修改"></i></span></a>
                        </div>
                        <div class="panel-body">
                        	${article.content}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#-- 初始化设置页面对应的JS文件路径 -->
<script>
	var pageUrl = "/ui-frame/script/article/detail";
</script>
<#-- 引入sea.js -->
<#include "/common/ui-frame.ftl">