<#escape x as x?html>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>chm-online</title>
    <link href="/favicon.ico" type="image/x-icon" rel=icon>
	<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon">
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet" />
    <link href="css/basic.css" rel="stylesheet" />
    <link href="css/custom.css" rel="stylesheet" />
</head>
<body>
	<div id="wrapper">
    	<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        	<div class="navbar-header">
                <a class="navbar-brand" href="/chm.action">CHM-ONLINE</a>
            </div>
            <div class="header-right">
                <a href="message-task.html" class="btn btn-info" title="未读消息"><b>10&nbsp;</b><i class="fa fa-envelope-o fa-2x"></i></a>
                <a href="message-task.html" class="btn btn-primary" title="CHM列表"><b>4000&nbsp;</b><i class="fa fa-bars fa-2x"></i></a>
                <a href="login.html" class="btn btn-danger" title="退出"><i class="fa fa-exclamation-circle fa-2x"></i></a>
                <a href="login.html" class="btn" title="个人中心"><img src="img/user.png" class="img-thumbnail" width="40px" height="40px"/></i></a>
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
                        <h1 class="page-head-line">小黑板儿</h1>
                        <h1 class="page-subhead-line">窈窕淑女，君子好逑。 </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="main-box mb-red">
                            <a href="javascript:void(0);"><i class="fa fa-bolt fa-5x"></i><h5>待处理:0</h5></a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="main-box mb-dull">
                            <a href="javascript:void(0);"><i class="fa fa-plug fa-5x"></i><h5>待完成:200</h5></a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="main-box mb-pink">
                            <a href="javascript:void(0);"><i class="fa fa-dollar fa-5x"></i><h5>已完成:500</h5></a>
                        </div>
                    </div>
                </div>
                <hr />
                <div class="row">
                	<div class="col-md-8">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead><tr><th>#</th><th>站内昵称</th><th>CHM名字</th><th>Username</th><th>浏览 No.</th></tr></thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td><span class="label label-danger">Mark</span></td>
                                        <td>Otto</td>
                                        <td>@mdo</td>
                                        <td><span class="label label-info">100090</span></td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Jacob</td>
                                        <td>Thornton</td>
                                        <td>@fat</td>
                                        <td>100090</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>Larry</td>
                                        <td><span class="label label-danger">the Bird</span> </td>
                                        <td>@twitter</td>
                                        <td>100090</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td><span class="label label-success">Mark</span></td>
                                        <td>Otto</td>
                                        <td>@mdo</td>
                                        <td><span class="label label-info">100090</span></td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>Larry</td>
                                        <td><span class="label label-primary">the Bird</span></td>
                                        <td>@twitter</td>
                                        <td>100090</td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td><span class="label label-warning">Jacob</span></td>
                                        <td><span class="label label-success">Thornton</span></td>
                                        <td>@fat</td>
                                        <td><span class="label label-danger">100090</span></td>
                                    </tr>
                                    <tr>
                                        <td>7</td>
                                        <td>Larry</td>
                                        <td><span class="label label-primary">the Bird</span></td>
                                        <td>@twitter</td>
                                        <td>100090</td>
                                    </tr>
                                    <tr>
                                        <td>8</td>
                                        <td><span class="label label-warning">Jacob</span></td>
                                        <td><span class="label label-success">Thornton</span></td>
                                        <td>@fat</td>
                                        <td><span class="label label-danger">100090</span></td>
                                    </tr>
                                    <tr>
                                        <td>9</td>
                                        <td><span class="label label-success">Mark</span></td>
                                        <td>Otto</td>
                                        <td>@mdo</td>
                                        <td><span class="label label-info">100090</span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-bell fa-fw"></i>Notifications Panel</div>
                            <div class="panel-body">
                                <div class="list-group">
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-twitter fa-fw"></i>3 New Followers
                                    <span class="pull-right text-muted small"><em>12 minutes ago</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-envelope fa-fw"></i>Message Sent
                                    <span class="pull-right text-muted small"><em>27 minutes ago</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>New Task
                                    <span class="pull-right text-muted small"><em>43 minutes ago</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-upload fa-fw"></i>Server Rebooted
                                    <span class="pull-right text-muted small"><em>11:32 AM</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-bolt fa-fw"></i>Server Crashed!
                                    <span class="pull-right text-muted small"><em>11:13 AM</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-warning fa-fw"></i>Server Not Responding
                                    <span class="pull-right text-muted small"><em>10:57 AM</em>
                                    </span>
                                    </a>

                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-bolt fa-fw"></i>Server Crashed!
                                    <span class="pull-right text-muted small"><em>11:13 AM</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-warning fa-fw"></i>Server Not Responding
                                    <span class="pull-right text-muted small"><em>10:57 AM</em>
                                    </span>
                                    </a>
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <i class="fa fa-shopping-cart fa-fw"></i>New Order Placed
                                    <span class="pull-right text-muted small"><em>9:49 AM</em>
                                    </span>
                                    </a>
                                </div>
                                <a href="javascript:void(0);" class="btn btn-info btn-block">View All Alerts</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="script/jquery-1.10.2.js"></script>
    <script src="script/bootstrap.js"></script>
    <script src="script/jquery.metisMenu.js"></script>
    <script src="script/custom.js"></script>
    <script src="script/index/index.js"></script>
</body>
</html>
</#escape>