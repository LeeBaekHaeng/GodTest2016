<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>
<%
  /**
   * @JSP Name : EgovMethodStructurePopup.jsp
   * @Description : 메서드 구조 정보 팝업
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

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css"/>
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/css/egovframework/oe1/cms/com/jquery.treeview.css' />" rel="stylesheet" type="text/css"/>
 
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javascript" language="javascript" defer="defer"></script>

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

<script type="text/javascript">
  $(document).ready(function(){
	  $("#treeArea")
	  //.load("<c:url value='/cms/arc/ams/getObjectCompnTreeAll.do?condition='/>"+$("#objectType").val()+"&searchKeyword="+$("#upperObjectId").val(), 
	  .load("<c:url value='/cms/arc/ams/getObjectCompnTreeAll.do?condition='/>"+$("#objectType").val()+"&searchKeyword="+$("#upperObjectId").val(),
		function() {
			$("#treeview").treeview({
			animated: "fast"
		});} );
  });

</script>

<script type="text/javascript">

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

<style type="text/css">

.treeview .expandable-hitarea-nodeClass { background-image: url('/oe1/images/egovframework/oe1/cms/arc/class.gif'); }
.treeview .expandable-hitarea-nodeMethod { background-image: url('/oe1/images/egovframework/oe1/cms/arc/method.gif'); }
.treeview .expandable-hitarea-nodeParam { background-image: url('/oe1/images/egovframework/oe1/cms/arc/parameter.gif'); }
.treeview .expandable-hitarea-nodeQuery { background-image: url('/oe1/images/egovframework/oe1/cms/arc/query.gif'); }

</style>

</head>
<body> 
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<div id="topnavi">
	    <c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
	</div>  

	<!-- 메인 시작 -->
	<div id="container">
		
		<div id="content">
		<!-- BODY 내용 START -->
		<form:form commandName="scrinVO" name="detailForm" method="post">
		<div id="content_pop">

			<div id="treeArea">
			Loading...
			</div> 

		<!-- 검색조건 유지 -->
		<input type="hidden" name="objectType" id="objectType" value="<c:out value="${objectType}"/>"/>
		<input type="hidden" name="upperObjectId" id="upperObjectId" value="<c:out value="${upperObjectId}"/>"/>
		<a href="#top_menu" class="hide_a"> </a>
		</div>
		</form:form>
	</div>
	</div>
	</div>
	
		<!-- BODY 내용 END -->
</body>

</html>

