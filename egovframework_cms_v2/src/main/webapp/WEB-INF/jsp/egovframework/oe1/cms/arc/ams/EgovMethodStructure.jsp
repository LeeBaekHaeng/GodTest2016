<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovMethodStructure.jsp
   * @Description : 메서드 구조 정보
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.16  김연수          최초 생성
   *
   * author 운영환경 1팀 
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="Content-language" content="ko">
<title>Architecture Structure Test</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/egovframework/oe1/cms/com/jquery.treeview.css' />" rel="stylesheet" type="text/css"  " />
 
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" 
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.treeview.js'/>"></script>

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>

<script>
  $(document).ready(function(){
	  $("#treeArea").load('http://192.168.100.62:8080/oe1/cms/arc/ams/getObjectCompnTree.do?compId='+$("#compId").val(), 
		function() {
			$("#treeview").treeview({
		  	collapsed: true,
			animated: "fast"
		});} );

  	$("#keyword").keypress(function(e){
	        if(e.keyCode==13) {
	        	$("#treeArea")//.load('http://192.168.100.62:8080/oe1/cms/arc/ams/getObjectCompnTree.do?compId='+$("#compnId").val(),+'&condition='+$("#condition").val()+'&searchKeyword='+$("#keyword").val(),    	
	        				  .load("<c:url value='/cms/arc/ams/getObjectCompnTree.do?compId='/>"+$("#compId").val()+"&condition="+$("#condition").val()+"&searchKeyword="+$("#keyword").val(), 
	        			function() {
	        				$("#treeview").treeview({
	        				animated: "fast"
	        			});} );
	        }
	});
  $("#search").click(function() {
	  alert("222222");
	  $("#treeArea")//.load('http://192.168.100.62:8080/oe1/cms/arc/ams/getObjectCompnTree.do?compId='+$("#compnId").val(),+'condition='+$("#condition").val()+'&searchKeyword='+$("#keyword").val(),
	  .load("<c:url value='/cms/arc/ams/getObjectCompnTree.do?compId='/>"+$("#compId").val()+"&condition="+$("#condition").val()+"&searchKeyword="+$("#keyword").val(), 
		function() {
			$("#treeview").treeview({
			animated: "fast"
		});} );
  });

  });
</script>

<script>

	function fn_egov_openPopup(url) {
		window.open(url);
	}

	function fn_openClass(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getClassDetail.do?id=' />"+id);
	}

	function fn_openMethod(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getMethodDetail.do?id=' />"+id);
	}

	function fn_openParam(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getParameterDetail.do?id=' />"+id);
	}

	function fn_openQuery(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getQueryDetail.do?id=' />"+id);
	}
	
</script>

</head>
<body> 
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">

	<!-- header 시작 -->
    <div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
    <div id="topnavi">
        <c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
    </div>  
	<!-- //header 끝 -->	

	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
		<!-- 좌메뉴 끝 -->
		
		<div id="content">
		<!-- BODY 내용 START -->


	<select id="condition" title="condition">
		<option value="0">클래스</option>
		<option value="1">메소드</option>
		<option value="2">파라메터</option>
		<option value="3">쿼리</option>
	</select>
	<input type="text" id="keyword" title="keyword" maxlength="30" />
	<input type="button" id="search" value="검색"  />
	<input type="hidden" id="compId" value="COMP-000000000000000" />
		
<div id="treeArea">
Loading...
</div> 

<a href="#top_menu" class="hide_a"> </a>



</div>
</div>
</div>

</body>

</html>

