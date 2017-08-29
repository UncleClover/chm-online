/**
 * chm文件新增
 * @author zhangdq
 * @time 2017-07-16 21:51
 */
define("/ui-frame/script/article/add", function(require, exports, module){
	require("jquery");
	require("/ui-frame/editor/kindeditor");
	require("/ui-frame/editor/lang/zh_CN");

	function init(){}
	
	function events(){
		KindEditor.ready(function(K) {
			var editor = K.create("#kindeditor", {
				width : "100%",
		        height : "700px",
		        items : ["source", "|", "undo", "redo", "|", "preview", "code", "plainpaste", "wordpaste", "|", 
		        		 "justifyleft", "justifycenter", "justifyright","justifyfull", "indent", "outdent", "lineheight", "|", 
		        		 "clearhtml", "removeformat", "quickformat", "fullscreen", "|", "formatblock", "fontname", "fontsize", 
		        		 "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "subscript", 
		        		 "superscript", "/", "image", "multiimage","table", "hr", "emoticons", "baidumap", "anchor", "link", "unlink"]
			});
			
			$("#save").click(function(){
				var title = $("#title").val();
				if(!title || !$.trim(title)){
					alert("chm标题不能为空！");
					return;
				}
				
				// 同步kindEditor的textarea的数据
				editor.sync();
				var content = $("#kindeditor").val();
				if(!content || !$.trim(content)){
					alert("chm内容不能为空！");
					return;
				}
				
				// 保存chm标题和内容
				$.ajax({
					method : "post",
					type : "json",
					url : "/article/save",
					data : {"title" : title, "content" : content},
					success : function(data){
						var result = data.result;
						console.log(result.name);
					}
				});
			});
		});
	}
	
	var add = {
			"init" : init,
			"events" : events
	};
	module.exports = add;
});