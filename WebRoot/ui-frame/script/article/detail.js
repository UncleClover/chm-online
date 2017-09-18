/**
 * chm文件详情
 * @author zhangdq
 * @time 2017-09-18 21:41
 */
define("/ui-frame/script/article/detail", function(require, exports, module){
	var $ = require("jquery");
	var navUtils = require("/ui-frame/script/common/navUtils");
	
	function init(){
		navUtils.init();
		navUtils.events();
	}
	
	function events(){
		// 修改chm信息
		$("#update_chm").click(function(){
			
		});
	}
	
	var detail = {
			"init" : init,
			"events" : events
	};
	
	module.exports = detail;
});