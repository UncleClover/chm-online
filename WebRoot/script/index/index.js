/**
 * 首页页面处理(index.ftl)
 * @author zhangdq
 * @time 2017-06-22 16:25
 */
$(function(){
	// 左侧导航目录、索引和搜索切换
	$("#main-menu li a").click(function(){
		var _currClass = $(this).attr("class");
		if(_currClass && _currClass.indexOf("current") > -1){
			return;
		}
		$("#main-menu li a").removeClass("current");
		$(this).addClass("current");
		
		// 左导航内容切换
		var menu_id = $(this).attr("id");
		if(menu_id == "context_tab"){
			$("#index,#search").hide();
			$("#context").show();
		}
		
		if(menu_id == "index_tab"){
			$("#context,#search").hide();
			$("#index").show();
		}
		
		if(menu_id == "search_tab"){
			$("#index,#context").hide();
			$("#search").show();
		}
	});
	
	// 左导航索引搜索
	$("#index-input").bind("keyup", function(event) {
		if (event.keyCode == "13") {
			var content = $(this).val();
			if(content){
				content = content.replace(/\s/g,"");
				if(content){
					$(this).val(content);
				}
			}
		}
	});
	
	// 左导航搜索
	$("#search-input").bind("keyup", function(event) {
		if (event.keyCode == "13") {
			var content = $(this).val();
			if(content){
				content = content.replace(/\s/g,"");
				if(content){
					$(this).val(content);
				}
			}
		}
	});
	
	// 新增chm
	$("#add_chm").click(function(){
		location.href = "/views/article.action";
	});
});