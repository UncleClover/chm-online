// seajs配置
seajs.config({
	base: "/ui-frame/script/",
	alias : {
				"jquery" : "/ui-frame/script/jquery-1.10.2"
			}
});


/**
 * 规范sea js文件的开发
 * init():初始化页面数据
 * events():处理页面事件
 * eg.
 * 	define("js_file_url", function(require, exports, module){
 *  	// 初始化页面数据
 *  	function init(){...}
 *  
 *  	// 处理页面事件
 *  	function events(){...}
 *  
 *  	// 自定义JS方法写在此处-start
 *  	......
 *  	// 自定义JS方法写在此处-start
 *  	
 *  	var js_file_name = {
 *  		"init" : init,
 *  		"events" : events
 *  	};
 *  	module.exports = js_file_name;
 *  });
 *  
 *  @author zhangdq
 *  @time 2017-07-18 16:11
 *  @version V2017.0.0.1
 *  @Email qiang900714@126.com
 */
seajs.use(pageUrl, function(c){c.init();c.events();});
