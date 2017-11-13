	var map,marker,toolbar,mouseTool;
	//回调方法
	var completCallBackFun ={};

	//构造ascMap showlable:是否展示标注，drawingToolOptions：标注工具选项,定义divID,areaName：行政区域name，
	//zoom：默认展示级别,zoomTool:是否展示缩放工具,drawJSON:展示数据,styleOptions:标注样式,areaColor:行政区域是否颜色区分
	function ascCMMap(mapObj){
			var areaCenter = mapObj.areaCenter;//{lng: 116.80, lat: 40.0}
			var mapZoom = mapObj.zoom;
			var drawJSON = mapObj.drawJSON;
			completCallBackFun = mapObj.completCallBackFun
			// 中移地图API功能  
			  map = new CMMap.Map("mapContainer", {
					view: {center: areaCenter, zoom: mapZoom, rotation: 0},
					zooms: [3, 18],
					cursor: "default",
					animateEnable: true,
					rotateEnable: false,
					resizeEnable: false,
					dragEnable: true,
					zoomEnable: true,
					doubleClickZoom: true,
					keyboardEnable: true,
					jogEnable: true,
					scrollWheel: true,
					touchZoom: true
			  });

			//在地图中添加ToolBar插件
			map.plugin(["CMMap.ToolBar"], function() {
				
				
				toolbar=new CMMap.ToolBar({size:20,offset:new 
					CMMap.Pixel (10,10),ruler:true,direction:true}); 
				map.addControl(toolbar);

			});
			
			//加载比例尺插件
			map.plugin(["CMMap.Scale"], function() {
				scale = new CMMap.Scale();
			});

			 map.plugin(["CMMap.MouseTool"], function() {
	                //在地图中添加MouseTool插件
	                mouseTool = new CMMap.MouseTool(map);


	                map.addListener(mouseTool, "draw", function(e) {
	                    try {
	                      //  console.log(e);
	                        var str = "";
	                        if (e.type == "polyline") {
	                            // 折线
	                            var i = 0;
	                            
	                            var pointArrays =  new Array();
	                            for (i = 0; i < e.obj.path.length; i++) {
	                            
	                                	pointArrays.push(jQuery.parseJSON('{"lng":"' +e.obj.path[i][0] + '","lat":"' + e.obj.path[i][1] + '"}'));
	                         
	                            }
	                   
	                           
	                            str = {"drawing":"polyline","length": e.obj.path.length.toString(),"pointArrays":pointArrays};
	                           
	                        }  else if (e.type == "polygon" || e.type == 'rectangle') {
	                            // 多边形
	                            var i = 0;
	                            var pointArrays =  new Array();
	                            for (i = 0; i < e.obj.path.length; i++) {
	                            	pointArrays.push(jQuery.parseJSON('{"lng":"' + e.obj.path[i].getLng().toString()+ '","lat":"' +  e.obj.path[i].getLat().toString()+ '"}'));
	                      
	                            }
	                            str = {"drawing":"polygon","length":e.obj.path.length.toString(),"pointArrays":pointArrays};
	                            //console.log("polygon==="+str);

	                        } else if (e.type == 'circle') {
	                            //圆
	                        
	                        	 str = {"drawing":"circle","radius":e.obj.getRadius().toString(),"center":{"lng": e.obj.getCenter().getLng().toString(),"lat":e.obj.getCenter().getLat().toString()}};
	                        
	                            //console.log("circle==="+str);
	                        } else if (e.type == 'marker') {
	                            //str = "坐标：" + e.obj.getPosition().toString();
	                            // debugger;
	                            //转为屏幕坐标
	                          // console.log("marker=坐标：=="+JSON.stringify(str));
	                           str = {"drawing":"marker","point":{"lng":e.obj.getPosition().lng.toString(),"lat":e.obj.getPosition().lat.toString()}}
	                            var pix = map.lnglatToPixel(e.obj.getPosition(), map.getZoom());
	                           // str += "  屏幕坐标：（" + pix.x + "," + pix.y + ")";
	                            //console.log("marker=屏幕坐标：：=="+JSON.stringify(pix));
	                            // 绑定事件
	                            mouseTool.close(false);
	                            var marker = new CMMap.Marker({
	                                map: window.map,
	                                position: e.obj,
	                                //angle: Math.round(Math.random() * 360)
	                            });
	                            marker.addListener(marker, "click", function(e) {
	                           // alert("我被单击了");
	                               // console.log("circle==="+JSON.stringify(e));
	                            });
	                            // console.log(pix);
								
	                            // var lnglat = new CMMap.LngLat(116.39825820922851, 36.904600759441024);
	                            // var level = 13;
	                            // var pixel = map.lnglatToPixel(lnglat, level);
	                            // alert(pixel.x + "," + pixel.y);

	                        }
	                    
	                      //  document.getElementById("result").innerHTML = str;

	                        //e.obj.setMap(null); //绘制后不显示图形
	            			if(typeof(completCallBackFun=="function")){
	            				completCallBackFun(str);
	            			}
	                    } catch (err) {
	                       // document.getElementById("result").innerHTML = err.message;
	                    }
	                });


	                
	                mouseTool.draw=function callback(a,e) {
	                var g = e.obj;//obj属性就是鼠标事件完成所绘制的覆盖物对象。
	               
				  

	                };
	                

	            });
		
	
			addlabel(map,drawJSON);
	
	}

	

	
	//展示自定义标注
		function addlabel(map,drawObj) {
	
            map.plugin(["CMMap.OverLay"],function() {
				for(var i=0; i<drawObj.length; i++) {
				
					var drawingType =  drawObj[i].drawing;
					if (drawingType == "marker") {
						
						var drawpoint = drawObj[i].point
			               var emarker = new CMMap.Marker({
			                    map:window.map,
			                    position:new CMMap.LngLat(drawpoint.lng,drawpoint.lat),
			                    clickable:true,
			                    draggable:true
			                    
			                });

					}
					if (drawingType == "circle") {
				//	console.log(JSON.stringify(drawObj[i]));
		                circle = new CMMap.Circle({
		                	map: map,
		                	center: new CMMap.LngLat(parseFloat(drawObj[i].center.lng), parseFloat(drawObj[i].center.lat)),// 圆心位置
		                	radius:parseInt(drawObj[i].radius) ,                                    //半径
		                	strokeColor: "#4196fc",                             //线颜色
		                	strokeOpacity: 1,                                //线透明度
		                	strokeWeight: 3,                                 //线粗细度
		                	fillColor: "#4196fc",                            //填充颜色
		                	fillOpacity: 0.35                                //填充透明度
		                	});
					}
					
					if (drawingType == "polyline"){
						var pointArrays = drawObj[i].pointArrays
					    var pointArray = new Array();
		
						for(var polygonIndex=0;polygonIndex<pointArrays.length;polygonIndex++){
			
							  var point = [pointArrays[polygonIndex].lng,pointArrays[polygonIndex].lat];
							  pointArray.push(point);
						}
                        polyline = new CMMap.Polyline({
                        map:map,
                        path: pointArray,             // 设置线覆盖物路径
                        strokeColor: "#4196fc",    // 线颜色
                        strokeOpacity: 0.8,        // 线透明度
                        strokeWeight: 3,          // 线宽
                        isOutline:true,           // 是否有描边
                        outlineColor:"#4196fc"    // 描边颜色
                     
                        });
					}
					if (drawingType == "polygon"){
						var pointArrays = drawObj[i].pointArrays
					    var pointArray = new Array();
		
						for(var polygonIndex=0;polygonIndex<pointArrays.length;polygonIndex++){
							var polygonpointLng = pointArrays[polygonIndex].lng;
							var polygonpointLat = pointArrays[polygonIndex].lat;
							var point = new Object();
							point.lng = parseFloat(polygonpointLng);
							point.lat = parseFloat(polygonpointLat);
							
							  pointArray.push(point);
						}
					 // console.log("polygon  pointArray=="+JSON.stringify(pointArray));
					       polygon = new CMMap.Polygon({
                               map:map,
                               path: JSON.stringify(pointArray),             // 设置线覆盖物路径
                               strokeColor: "#4196fc",    // 线颜色
                               strokeOpacity: 0.1,        // 线透明度
                               strokeWeight: 3,           // 线宽
                               fillColor: "#4196fc",      // 填充色
                               fillOpacity: 1,            // 填充透明度
                               strokeStyle: "dashed",     // 线样式
                               strokeDasharray: [10,2,10] // 补充线样式
                               });
					}
				}
            });
		}
	

		//画点
		function drawpoint() {

			mouseTool.marker();
		};
		//画线
		function drawline() {
			mouseTool.polyline();
		};
		//画面
		function drawpolygon() {
			mouseTool.polygon();
		};
		//画面
		function drawcircle() {
			mouseTool.circle();
		};
		//画面
		function drawclean() {

			
			mouseTool.close(true);
		};




