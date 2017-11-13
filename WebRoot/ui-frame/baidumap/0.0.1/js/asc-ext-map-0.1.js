	var map;
	//回调方法
	var completCallBackFun ={};
var address={};
	//加载的覆盖物，用于标注时删除
	var showlabels=new Array();
	//构造ascMap showlable:是否展示标注，drawingToolOptions：标注工具选项,定义divID,areaName：行政区域name，
	//zoom：默认展示级别,zoomTool:是否展示缩放工具,drawJSON:展示数据,styleOptions:标注样式,areaColor:行政区域是否颜色区分
	function ascMap(mapObj){
			var areaName = mapObj.areaName;
			var zoom = mapObj.zoom;
			var zoomTool = mapObj.zoomTool;
			var drawingToolOptions = mapObj.drawingToolOptions;
			var drawJSON = mapObj.drawJSON;
			var styleOptions = mapObj.styleOptions;
			completCallBackFun = mapObj.completCallBackFun
			getAddressCallBackFun = mapObj.getAddressCallBackFun
			// 百度地图API功能
			map = new BMap.Map("allmap", {enableMapClick:false});    // 创建Map实例

			map.centerAndZoom(areaName,zoom);

			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

			if(zoomTool){
					var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
					var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
					map.addControl(top_left_control);        
					map.addControl(top_left_navigation);   
			}
		
			var geoc = new BMap.Geocoder();    

			map.addEventListener("click", function(e){        
				var pt = e.point;
				geoc.getLocation(pt, function(rs){
					var addComp = rs.addressComponents;
					
					
					address["province"]=addComp.province;
					address["city"]=addComp.city;
					address["district"]=addComp.district;
					address["street"]=addComp.street;
					address["streetNumber"]=addComp.streetNumber;
			           if(typeof(getAddressCallBackFun == "function")){
			        	   getAddressCallBackFun(address);
			           }
				});        
			});
			
			
			drawingManagerInit(map,drawingToolOptions,styleOptions);

			addlabel(map,drawJSON,styleOptions);
			return overlays;
	}
	
	function drawingManagerInit(map,drawingToolOptions,styleOptions){
		//是否显示绘制工具
		var enDrawTool = (!drawingToolOptions)?false:true ;
		
		   //实例化鼠标绘制工具
	    var drawingManager = new BMapLib.DrawingManager(map, {
	        isOpen: false, //是否开启绘制模式
	        enableDrawingTool: enDrawTool, //是否显示工具栏
	        drawingToolOptions: {
	            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
	            offset: new BMap.Size(100, 5), //偏离值
                scale:0.8,
                drawingModes: drawingToolOptions
	        },
	        circleOptions: styleOptions, //圆的样式
	        polylineOptions: styleOptions, //线的样式
	        polygonOptions: styleOptions, //多边形的样式
	        rectangleOptions: styleOptions //矩形的样式
	    });  
		 //添加鼠标绘制工具监听事件，用于获取绘制结果
	    var result = drawingManager.addEventListener('overlaycomplete', overlaycomplete);


		 return result;
	}
	

	
	//计算中心点
	var calculateCenter = function(lnglatarr){
	    var total = lnglatarr.length;
	    var X=0,Y=0,Z=0;
	    $.each(lnglatarr, function(index, lnglat) {
	      var lng = lnglat.lng * Math.PI / 180;
	      var lat = lnglat.lat * Math.PI / 180;
	      var x,y,z;
	      x = Math.cos(lat) * Math.cos(lng);
	      y = Math.cos(lat) * Math.sin(lng);
	      z = Math.sin(lat);
	      X += x;
	      Y += y;
	      Z += z;
	    });

	    X = X/total;
	    Y = Y/total;
	    Z = Z/total;

	    var Lng = Math.atan2(Y,X);
	    var Hyp = Math.sqrt(X*X + Y*Y);
	    var Lat = Math.atan2(Z,Hyp);

	    return new BMap.Point(Lng*180/Math.PI,Lat*180/Math.PI);
	  };
	//展示自定义标注
		function addlabel(map,drawObj,styleOptions) {

			for(var i=0; i<drawObj.length; i++) {
				var drawingType =  drawObj[i].drawing;
				if (drawingType == BMAP_DRAWING_MARKER) {
					var drawpoint = drawObj[i].point
					var marker = new BMap.Marker(new BMap.Point(drawpoint.lng,drawpoint.lat));  
					map.addOverlay(marker); 	
				}
				if (drawingType == BMAP_DRAWING_CIRCLE) {
					var centerPoint = new BMap.Point(drawObj[i].center.lng,drawObj[i].center.lat);
					circle = new BMap.Circle(centerPoint, drawObj[i].radius,styleOptions);
					map.addOverlay(circle); 
				}
				if (drawingType == BMAP_DRAWING_POLYLINE || drawingType == BMAP_DRAWING_POLYGON || drawingType == BMAP_DRAWING_RECTANGLE) {
					var pointArrays = drawObj[i].pointArrays
				    var pointArray = new Array();
	
					for(var polygonIndex=0;polygonIndex<pointArrays.length;polygonIndex++){
		
						  var point = new BMap.Point(pointArrays[polygonIndex].lng,pointArrays[polygonIndex].lat);
						  pointArray.push(point);
					}
					//多边形中心点
//					var marker = new BMap.Marker(calculateCenter(pointArrays));  
//					map.addOverlay(marker); 
					var polygon = new BMap.Polygon(pointArray,styleOptions); 
				
					map.addOverlay(polygon); 
				}
			
			}
		    var optsArray = [{},{},{}];
		    var labelArray = [];
		    showlabels = map.getOverlays()
		/* 	    for(var i = 0;i < pointArray.length; i++) {
		      optsArray[i].position = pointArray[i];
		      labelArray[i] = new BMap.Label(contentArray[i],optsArray[i]);
		      labelArray[i].setStyle({
				color : "red",
				fontSize : "12px",
					 height : "20px",
					 lineHeight : "20px",
					 fontFamily:"微软雅黑"
				 });
		      map.addOverlay(labelArray[i]);
		    }	   */
		}
	
	//标注完成后展现
		//标注结果	   
		var overlays = [];
	   var overlaycomplete = function (e) {
       		cleardrawingAll();
       		overlays.push(e.overlay);

       		
	   		var result = {};
	   		result["drawing"]  = e.drawingMode;
			if (e.drawingMode == BMAP_DRAWING_MARKER) {
				var centerPoint = {};
            	centerPoint["lng"] =""+e.overlay.getPosition().lng ;
            	centerPoint["lat"]=""+e.overlay.getPosition().lat;
            

       			result["point"]=centerPoint;
			}
			if (e.drawingMode == BMAP_DRAWING_CIRCLE) {
				result["radius"] =""+e.overlay.getRadius();//半径
            	var centerPoint = {};
            	centerPoint["lng"] =""+e.overlay.getCenter().lng;
            	centerPoint["lat"]=""+e.overlay.getCenter().lat;
            	result["center"]=centerPoint;
			}
			if (e.drawingMode == BMAP_DRAWING_POLYLINE || e.drawingMode == BMAP_DRAWING_POLYGON || e.drawingMode == BMAP_DRAWING_RECTANGLE) {
				result["length"]=""+e.overlay.getPath().length;//点的数量
				var pointArrays = new Array();
		        var path = e.overlay.getPath();//Array<Point> 返回多边型的点数组
	            for(var i=0;i<path.length;i++){
	            	pointArrays.push(jQuery.parseJSON('{"lng":"' + path[i].lng + '","lat":"' + path[i].lat + '"}'));
	            	result["pointArrays"] = pointArrays;
	            }
			}
			
			if(typeof(completCallBackFun=="function")){
				completCallBackFun(result);
			}

           return result;
       };


        function cleardrawingAll() {
            for (var i = 0; i < overlays.length; i++) {
                map.removeOverlay(overlays[i]);
            }
     
            for (var j = 0; j < showlabels.length; j++) {
                map.removeOverlay(showlabels[j]);
            }
            overlays.length = 0
        }

