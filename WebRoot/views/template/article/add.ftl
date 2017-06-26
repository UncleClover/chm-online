<#escape x as x?html>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>chm-online</title>
    <link href="/favicon.ico" type="image/x-icon" rel=icon>
	<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon">
    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/font-awesome.css" rel="stylesheet" />
    <link href="/css/basic.css" rel="stylesheet" />
    <link href="/css/custom.css" rel="stylesheet" />
    <link href="/css/index/index.css" rel="stylesheet" />
    <script charset="utf-8" src="/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/editor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/common/kindEditor.js"></script>
	<script>
      KindEditor.ready(function(K) {
			window.editor = K.create('#editor_id', {
				width : '100%',
        		height : "700px",
        		items : ['source', '|', 'undo', 'redo', '|', 'preview', 'code', 'plainpaste', 'wordpaste', '|', 
        				 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'indent', 'outdent', 'lineheight', 
        				 'clearhtml', 'removeformat', 'quickformat', 'fullscreen', 'formatblock', 'fontname', 'fontsize', 
        				 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'subscript', 
        				 'superscript', '/', 'image', 'multiimage','table', 'hr', 'emoticons', 'baidumap', 'anchor', 'link', 'unlink']
	});
});
	</script>
</head>
<body>
	<div id="wrapper">
    	<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        	<div class="navbar-header">
                <a class="navbar-brand" href="/chm.action">CHM-ONLINE</a>
            </div>
            <div class="header-right">
                <a href="javascript:void(0);" class="btn btn-danger" title="退出"><i class="fa fa-dot-circle-o fa-2x"></i></a>
                <a href="javascript:void(0);" class="btn" title="个人中心"><img src="/img/user.png" class="img-thumbnail" width="40px" height="40px"/></i></a>
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
                               	<textarea id="editor_id" name="content"></textarea>
                               	<div style="padding-top:10px;"><a href="javascript:void(0);" class="btn btn-info btn-block">保存CHM</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/script/jquery-1.10.2.js"></script>
    <script src="/script/bootstrap.js"></script>
    <script src="/script/jquery.metisMenu.js"></script>
    <script src="/script/custom.js"></script>
    <script src="/script/index/index.js"></script>
</body>
</html>
</#escape>