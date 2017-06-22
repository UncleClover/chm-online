/**
 * Ê×Ò³Ò³Ãæ´¦Àí(index.ftl)
 * @author zhangdq
 * @time 2017-06-22 16:25
 */
$(function(){
	// ×ó²àµ¼º½Ä¿Â¼¡¢Ë÷ÒýºÍËÑË÷ÇÐ»»
	$("#main-menu li a").click(function(){
		var _currClass = $(this).attr("class");
		if(_currClass && _currClass.indexOf("current") > -1){
			return;
		}
		$("#main-menu li a").removeClass("current");
		$(this).addClass("current");
		
		// ×óµ¼º½ÄÚÈÝÇÐ»»
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
	
	// ×óµ¼º½Ë÷ÒýËÑË÷
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
	
	// ×óµ¼º½ËÑË÷
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
});