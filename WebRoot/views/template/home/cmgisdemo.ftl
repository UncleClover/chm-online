<#escape x as x?html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>chm-online</title>
   	<script type="text/javascript" src="/ui-frame/script/jquery-1.10.1.js"></script>	
	<script type="text/javascript" src="http://223.100.246.29:9091/SDKService/jssdk/auth?v=1.1&key=quki4ft38o535b8pvp94fm1999wf8927"></script>
	<script type="text/javascript" src="/ui-frame/script/asc-ext-cmmap-0.1.js"></script>
	<link rel="stylesheet" href="/ui-frame/script/asc-ext-cmmmap-0.1.css">

</head>
<body>
 <body  onload="cmmapInit();">
		<!--  以下工具根据需求自行增加-->
<div>
		<button onclick="javascript:drawpoint();" style="float: left;">鼠标绘制点</button>
		<button onclick="javascript:drawline();" style="float: left;">鼠标绘制线</button>
		<button onclick="javascript:drawpolygon();" style="float: left;">鼠标绘制面</button>
		<button onclick="javascript:drawcircle();" style="float: left;">鼠标绘制圆</button>
		<button onclick="javascript:drawclean();" style="float: left;">关闭</button>
</div>
		<div id="mapContainer" style="position: relative;"/>
 </body>
 
<script type="text/javascript">
	function cmmapInit(){
		var mapObj ={};
		
		//通过系统管理获取当前行政区域中心点经纬度
		mapObj.areaCenter= {lng: 113.663221, lat: 34.7568711};

		mapObj.drawJSON = [
{"drawing":"polyline","length":"2","pointArrays":[{"lng":"113.56807708740234","lat":"34.781522653588105"},{"lng":"113.6041259765625","lat":"34.770101576676645"}]},
{"drawing":"circle","radius":"2293.340648107292","center":{"lng":"113.6480712890625","lat":"34.780394716350614"}},
	                {"drawing":"polyline","length":"2","pointArrays":[{"lng":"116.418923","lat":"39.920281"},{"lng":"116.421582","lat":"39.917071"}]},
	                {"drawing":"polygon","length":"4","pointArrays":[{"lng":"116.425427","lat":"39.920364"},{"lng":"116.423379","lat":"39.918898"},{"lng":"116.427439","lat":"39.917265"},{"lng":"116.430026","lat":"39.917625"}]},
	                {"drawing":"circle","radius":"2008.46001964566142","center":{"lng":"114.407569","lat":"39.923048"}},
	                {"drawing":"marker","point":{"lng":"113.57683181762695","lat":"34.755858266398334"}},
	                {"drawing":"polygon","length":"4","pointArrays":[{"lng":"113.62249374389648","lat":"34.74669047997149"},{"lng":"113.62266540527344","lat":"34.731173446283925"},{"lng":"113.6810302734375","lat":"34.74104825943732"},{"lng":"113.6480712890625","lat":"34.78124067072453"}]},
	                {"drawing":"marker","point":{"lng":"116.387518","lat":"40.104964"}}];
			//标注之后回调获得经纬度坐标	
			mapObj.completCallBackFun = function(e){
				console.log("callback",JSON.stringify(e));
			};
			
			ascCMMap(mapObj);
	}
	
</script>
</#escape>

