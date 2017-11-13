'<#escape x as x?html>
<!DOCTYPE html>
<script>
    // 此名称为固定_proxyServerAddress，以便apijs中能够获取
    var _proxyServerAddress = "10.1.235.24:28080";
</script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>chm-online</title>
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
   	<link rel="stylesheet" href="/ui-frame/baidumap/0.0.1/css/DrawingManager_min.css" />	
	<script type="text/javascript" src="/ui-frame/script/jquery-1.10.1.js"></script>	
	<script type="text/javascript" src="/ui-frame/baidumap/0.0.1/js/mapapi-2.0.min.js"></script>
	<script type="text/javascript" src="/ui-frame/baidumap/0.0.1/js/DrawingManager_min.js"></script>
	<script type="text/javascript" src="/ui-frame/baidumap/0.0.1/js/asc-ext-map-0.1.js"></script>
</head>
<body>
	<div id="allmap"></div>
	<input type="hidden"  id="lnglatVal"/>
</body>
<script type="text/javascript">
	console.log(111);

	var mapObj ={};
	mapObj.areaName="郑州";

	mapObj.zoomTool = true;
	mapObj.drawingToolOptions = [
									BMAP_DRAWING_MARKER, //画点 
									BMAP_DRAWING_CIRCLE, //画圆 
									BMAP_DRAWING_POLYLINE, // 画线 
									BMAP_DRAWING_POLYGON, // 画多边形 
									BMAP_DRAWING_RECTANGLE,// 画矩形
									];
	mapObj.styleOptions = {
            strokeColor: "red",    //边线颜色。
            fillColor: "red",      //填充颜色。当参数为空时，圆形将没有填充效果。
            strokeWeight: 3,       //边线的宽度，以像素为单位。
            strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
            fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
            strokeStyle: 'solid' //边线的样式，solid或dashed。
        }
	mapObj.drawJSON = [{"drawing":"circle","radius":"1945.2379785504752","center":{"lng":"116.53772","lat":"39.969284"}},
                {"drawing":"polygon","pointArrays":[{"lng":"116.06399","lat":"40.107384"},{"lng":"116.205419","lat":"40.162545"},{"lng":"116.309479","lat":"40.069407"},{"lng":"116.381343","lat":"40.074266"},{"lng":"116.400316","lat":"40.038038"},{"lng":"116.362946","lat":"40.029641"},{"lng":"116.391117","lat":"39.989409"},{"lng":"116.389392","lat":"39.973928"},{"lng":"116.379044","lat":"39.970832"},{"lng":"116.380193","lat":"39.956232"},{"lng":"116.342249","lat":"39.949153"},{"lng":"116.341674","lat":"39.904445"},{"lng":"116.260036","lat":"39.90356"},{"lng":"116.253137","lat":"39.92968"},{"lng":"116.211168","lat":"39.92304"},{"lng":"116.227841","lat":"39.947825"},{"lng":"116.188172","lat":"39.992504"},{"lng":"116.167475","lat":"39.991178"},{"lng":"116.184147","lat":"40.01417"},{"lng":"116.145628","lat":"40.035386"},{"lng":"116.10021","lat":"40.034944"},{"lng":"116.073763","lat":"40.044666"},{"lng":"116.051917","lat":"40.091489"}]},
                {"drawing":"polygon","pointArrays":[{"lng":"116.47333","lat":"40.092373"},{"lng":"116.40204","lat":"40.040247"},{"lng":"116.360071","lat":"40.029641"},{"lng":"116.392267","lat":"39.990293"},{"lng":"116.388817","lat":"39.976582"},{"lng":"116.448033","lat":"39.958002"},{"lng":"116.455507","lat":"39.949153"},{"lng":"116.450908","lat":"39.872999"},{"lng":"116.479654","lat":"39.859708"},{"lng":"116.43941","lat":"39.83888"},{"lng":"116.512424","lat":"39.821592"},{"lng":"116.526222","lat":"39.836664"},{"lng":"116.57624","lat":"39.84021"},{"lng":"116.599811","lat":"39.828242"},{"lng":"116.610735","lat":"39.830015"},{"lng":"116.629132","lat":"39.867683"},{"lng":"116.634881","lat":"39.892932"},{"lng":"116.629707","lat":"39.930565"},{"lng":"116.652703","lat":"39.95579"},{"lng":"116.649829","lat":"40.000022"},{"lng":"116.553818","lat":"40.054829"},{"lng":"116.554393","lat":"40.066757"},{"lng":"116.545769","lat":"40.075591"},{"lng":"116.50495","lat":"40.085307"},{"lng":"116.5061","lat":"40.084865"}]},
                {"drawing":"polygon","pointArrays":[{"lng":"116.30751","lat":"39.990836"},{"lng":"116.308157","lat":"39.981023"},{"lng":"116.34646","lat":"39.982101"},{"lng":"116.344628","lat":"39.991941"},{"lng":"116.344628","lat":"39.991886"}]},
                {"drawing":"polygon","pointArrays":[{"lng":"116.307797","lat":"39.980581"},{"lng":"116.315379","lat":"39.968638"},{"lng":"116.328171","lat":"39.973034"},{"lng":"116.324398","lat":"39.981797"},{"lng":"116.324182","lat":"39.98177"}]},
                {"drawing":"polygon","pointArrays":[{"lng":"116.32465","lat":"39.981742"},{"lng":"116.328243","lat":"39.972868"},{"lng":"116.351419","lat":"39.973421"},{"lng":"116.346928","lat":"39.982074"}]},
                {"drawing":"rectangle","length":"4","pointArrays":[{"lng":"116.412671","lat":"39.918787"},{"lng":"116.41648","lat":"39.918787"},{"lng":"116.41648","lat":"39.913502"},{"lng":"116.412671","lat":"39.913502"}]},
                {"drawing":"polyline","length":"2","pointArrays":[{"lng":"116.418923","lat":"39.920281"},{"lng":"116.421582","lat":"39.917071"}]},
                {"drawing":"polygon","length":"4","pointArrays":[{"lng":"116.425427","lat":"39.920364"},{"lng":"116.423379","lat":"39.918898"},{"lng":"116.427439","lat":"39.917265"},{"lng":"116.430026","lat":"39.917625"}]},
                {"drawing":"circle","radius":"208.46001964566142","center":{"lng":"116.407569","lat":"39.923048"}},
                {"drawing":"rectangle","length":"4","pointArrays":[{"lng":"116.40243","lat":"39.917154"},{"lng":"116.406814","lat":"39.917154"},{"lng":"116.406814","lat":"39.910596"},{"lng":"116.40243","lat":"39.910596"}]},
                {"drawing":"marker","point":{"lng":"116.387518","lat":"40.104964"}}];
		//标注之后回调获得经纬度坐标	
		mapObj.completCallBackFun = function(e){
			console.log("completCallBackFun",JSON.stringify(e));
		};
		
		mapObj.getAddressCallBackFun = function(e){
			console.log("getAddressCallBackFun",JSON.stringify(e));
		};
		
		var result = new ascMap(mapObj);


</script>
</html>
</#escape>