<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="<c:url value="/js/egovframework/com/LeftMenu.js"/>"/></script>
<script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript">

function fn_egov_initManage(){
	getContentTab(1);
}

function getContentTab(index){
	
	
	var url = '';
	if(index=='1'){
		//goLeftMenu('/bopr/mom/EgovRehndnJobList.do','batchOpr');
		url="/bopr/mom/EgovRehndnJobList.do";
		//url="goLeftMenu('/bopr/mom/EgovRehndnJobList.do','batchOpr')";
	}else if(index=='2'){
		//goLeftMenu('/bopr/mom/selectRehndnList.do','batchOpr');
		url="/bopr/mom/selectRehndnList.do";
		//url="goLeftMenu('/bopr/mom/selectRehndnList.do','batchOpr')";
	}
	
	var targetDiv = "#tabs-" + index; 
	var targetSize = ((document.getElementById("tabs").childNodes.length - 1)/2)-1;
	
	for(var i = 1; i < targetSize; i++){
		styleDiv = "tabs-" + (i);
		if(index==(i)){
			document.getElementById(styleDiv).style.display = "block";
		}else{
			document.getElementById(styleDiv).style.display = "none";
		}
	}

	$.get(url, null, function(result){
		$(targetDiv).html(result);   // 해당 div에 결과가 나타남
	});
	
}
</script>

</head>
<body onload="fn_egov_initManage();">
<div id="tabs">
	<ul>
		<li><a href="#tabs-1" onclick="getContentTab(1);">재처리 대상 관리</a>
		<a href="#tabs-2" onclick="getContentTab(2);">재처리 결과 관리</a></li>
	</ul>

	<!-- 그리드가 나타날 페이지가 통째로, 각 탭에 따라서 아래 div에 들어간다 -->
	<div id="tabs-1" style="display:none"></div>      
	<div id="tabs-2" style="display:none"></div>
</div>
</body>
</html>