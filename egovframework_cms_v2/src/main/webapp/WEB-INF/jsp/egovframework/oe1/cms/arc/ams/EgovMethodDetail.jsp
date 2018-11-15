<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovMethodDetail.jsp
   * @Description : 메서드 상세정보
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
<title>Method Information</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("#accordion").accordion({
		collapsible: true
	});	
$("#accordion").css({'background':'#f1f1f1','font-family':'굴림','border':'1px solid #d5d5d5','height':'390px'});	
$(".table_style tr").mouseover(function() {$(this).addClass("over");}).mouseout(function() {$(this).removeClass("over");});

$("div.linkdiv a").click(function(){ 	
	var topurl=$(this).attr("href");
	$('#content').load(topurl);
	});	
			
	$(".image_rollover").mouseover(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //ff
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "ff") $(this).attr("src", file_name + "n." + file_type);
	}).mouseout(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //on
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "on") $(this).attr("src", file_name + "off." + file_type);
	});			
});	
</script>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


function fn_egov_openPopup(url) {
	window.open(url);
}
function fn_egov_openParameter(id) {
	fn_egov_openPopup("<c:url value='/cms/arc/ams/getParameterDetail.do?id=' />"+id);
	
}
function fn_egov_close() {
   	window.close();
}

-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap"><!-- header 시작 -->

<div id="content"><!-- BODY 내용 START -->

<input type="hidden" name="id" value="<c:out value='${vo.methdId}' />" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 메서드 상세정보</strong></h1></div>
	<!-- 타이틀 -->
	<div id="datail_table">
	<table summary="클래스 상세정보">
	<caption>클래스 상세정보</caption>
		<tr>
			<th>메서드명</th>
			<td>
				<c:out value='${vo.methdNm}' />
			</td>
		</tr>
		<tr>
			<th>어노테이션</th>
			<td>
				<c:out value='${vo.methdAnnot}' />
			</td>
		</tr>
		<tr>
			<th>리턴타입</th>
			<td>
				<c:out value='${vo.methdTy}' />
			</td>
		</tr>
		<tr>
			<th>메서드 설명</th>
			<td>
				<pre><c:out value='${vo.methdDc}' /></pre>
			</td>
		</tr>
	</table>
  </div>
  <br/>
  <div id="h2_topnav"><h1><strong> 파라메터 목록 </strong></h1></div>
  <div id="result_table">
	<table summary="파라메터 목록">
	<colgroup>
		<col width="40%"/>
		<col width="60%"/>
	</colgroup>	
	<caption>파라메터 목록</caption>
		<tr>
			<th>리턴타입</th>
			<th>이름</th>
		</tr>
		<c:forEach var="result" varStatus="status"  items="${paramList}">
		<tr>
			<td><c:out value='${result.paramtrTy}' /></td>
			<td><a href="<c:url value='/cms/arc/ams/getParameterDetail.do?id=${result.paramtrId}' />"><c:out value='${result.paramtrNm}' /></a></td>
		</tr>
		</c:forEach>
	</table>
  </div>
  <br/>
  <div id="h2_topnav"><h1><strong> 이 메서드가 호출하는 메서드 목록 </strong></h1></div>
  <div id="result_table">
	<table summary="메서드 목록">
	<colgroup>
		<col width="40%"/>
		<col width="60%"/>
	</colgroup>	
	<caption>메서드 목록</caption>
		<tr>
			<th>클래스 명</th>
			<th>메서드 명</th>
		</tr>
		<c:forEach var="result" varStatus="status"  items="${callList}">
		<tr>
			<td><a href="<c:url value='/cms/arc/ams/getClassDetail.do?id=${result.classId}' />"><c:out value='${result.classNm}' /></a></td>
			<td><a href="<c:url value='/cms/arc/ams/getMethodDetail.do?id=${result.methdId}' />"><c:out value='${result.methdNm}' /></a></td>
		</tr>
		</c:forEach>
	</table>
  </div>
  <br/>
  <div id="h2_topnav"><h1><strong> 이 메서드를 호출하는 메서드 목록 </strong></h1></div>
  <div id="result_table">
	<table summary="메서드 목록">
	<colgroup>
		<col width="40%"/>
		<col width="60%"/>
	</colgroup>
	<caption>메서드 목록</caption>
		<tr>
			<th>클래스 명</th>
			<th>메서드 명</th>
		</tr>
		<c:forEach var="result" varStatus="status"  items="${calledList}">
		<tr>
			<td><a href="<c:url value='/cms/arc/ams/getClassDetail.do?id=${result.callclassId}' />"><c:out value='${result.callclassNm}' /></a></td>
			<td><a href="<c:url value='/cms/arc/ams/getMethodDetail.do?id=${result.callmethdId}' />"><c:out value='${result.callmethdNm}' /></a></td>
		</tr>
		</c:forEach>
	</table>
  </div>
  <br/>
  <div id="h2_topnav"><h1><strong> 이 메서드가 사용하는 쿼리 목록 </strong></h1></div>
  <div id="result_table">
	<table summary="쿼리목록">
	<colgroup>
		<col width="40%"/>
		<col width="60%"/>
	</colgroup>	
	<caption>쿼리 목록</caption>
		<tr>
			<th>쿼리 명</th>
			<th>쿼리 설명</th>
		</tr>
		<c:forEach var="result" varStatus="status"  items="${queryList}">
		<tr>
			<td><c:out value='${result.quryId}' /></td>
			<td><a href="<c:url value='/cms/arc/ams/getQueryDetail.do?id=${result.quryId}' />"><c:out value='${result.quryNm}' /></a></td>
		</tr>
		</c:forEach>
	</table>
  </div>
  <div id="h2_topnav"><h1><strong> 메서드 소스코드 </strong></h1></div>
  <div id="datail_table">  
  <pre>
  <c:out value='${vo.methdCode}' />
  </pre>
  </div>
  <Br>
  <br>
  <br>
  
	 <!-- 버튼 시작 -->
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="닫기" class="submit_btn_input"  onclick="fn_egov_close(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>		

</div>

<!-- BODY 내용 END --></div>

</div>
<!-- //전체 DIV끝 -->

</body>
</html>

