<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovMethodStructureTree.jsp
   * @Description : 메서드 구조 트리정보
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
<script type="text/javascript" 
     src="<c:url value='/js/egovframework/oe1/cms/com/jquery.jstree.js'/>"></script>

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>

<script> 
jQuery("some-selector-to-container-node-here").jstree([ config_object ]);
 
jQuery("some-selector-to-container-node-here")
 .jstree({
  core : { /* core에 대한 옵션이 이곳에 들어간다.*/ },
  themes : { /* themes에 대한 옵션이 이곳에 들어간다.*/ },
  plugins : [ "themes", "html_data", "some-other-plugin" ]
 });

jQuery("some-selector-to-container-node-here").jstree("operation_name" [, argument_1, argument_2, ...]); 
jQuery.jstree._reference(needle).operation_name([ argument_1, argument_2, ...]);

jQuery("some-container")
.bind("loaded.jstree", function (event, data) {
 alert("TREE IS LOADED");
})
.jstree({ /* configuration here */ });
{ 
	 "inst" : /* 실제 트리 인스턴스 */, 
	 "args" : /* 함수에 넘겨진 인자값들  */, 
	 "rslt" : /* 이벤트에 사용된 어떠한 데이터  */, 
	 "rlbk" : /* 롤백객체(선택적으로 사용됨)) */
	}



</script>


</head>
<body> 
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap"><!-- header 시작 -->
<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
<div id="topnavi">
    <c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
</div>  
<!-- //header 끝 --> <!-- 메인 시작 -->
<div id="container">
<!-- 좌메뉴 시작 -->
<div id="leftmenu">
    <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
</div>
<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->
	<select id="condition">
		<option value="0">클래스</option>
		<option value="1">메소드</option>
		<option value="3">쿼리</option>
	</select>
	<input type="text" id="keyword" maxlength="30" />
	
	<input type="button" id="search" value="검색"  />
	
	
	<input type="button" class="button" value="toggle_node" id="toggle_node" style="clear:both;" />
<div id="demo1" class="demo">
 <ul>
  <li id="phtml_1">
   <a href="#">Root node 1</a>
   <ul>
    <li id="phtml_2">
     <a href="#">Child node 1</a>
    </li>
    <li id="phtml_3">
     <a href="#">Child node 2</a>
    </li>
   </ul>
  </li>
  <li id="phtml_4">
   <a href="#">Root node 2</a>
  </li>
 </ul>
</div>
	
<script type="text/javascript" class="source">
$(function () {
 $("#toggle_node").click(function () { 
  $("#demo1").jstree("toggle_node","#phtml_1");
 });
 $("#demo1")
  .bind("open_node.jstree close_node.jstree", function (e) {
   $("#log1").html("Last operation: " + e.type);
  })
  .jstree({ "plugins" : [ "themes", "html_data" ] });
});
</script>

</div>
</div>
</div>

</body>

</html>

