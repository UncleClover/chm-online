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
 *  });
 *  
 *  @author zhangdq
 *  @time 2017-07-18 16:11
 *  @version V2017.0.0.1
 *  @Email qiang900714@126.com
 */
define("/ui-frame/script/common/cSea",function(require, exports, module){
	seajs.use(pageUrl, function(c){
		c.init();
		c.events();
	});
});