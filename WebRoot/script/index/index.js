/**
 * ��ҳҳ�洦��(index.ftl)
 * @author zhangdq
 * @time 2017-06-22 16:25
 */
$(function(){
	// ��ർ��Ŀ¼�������������л�
	$("#main-menu li a").click(function(){
		var _currClass = $(this).attr("class");
		if(_currClass && _currClass.indexOf("current") > -1){
			return;
		}
		$("#main-menu li a").removeClass("current");
		$(this).addClass("current");
	});
});