<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld"%>
<% 
  /**
   * @JSP Name : EgovMethdListPopup.jsp
   * @Description : 어노테이션 목록 조회 팝업
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.02  김정수          최초 생성
   *
   * author 운영환경 1팀 
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>어노테이션 목록 조회</title>
<%
//String rowCount = request.getParameter("rowCount");
//String rowIndex = request.getParameter("rowIndex");
%>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >


<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.popupAt.value = "Y";
	document.listForm.action = "<c:url value='/cms/arc/selectMethdListPopUp.do?'/>";
   	document.listForm.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.popupAt.value = "Y";
	document.listForm.action = "<c:url value='/cms/arc/selectMethdListPopUp.do?'/>";
   	document.listForm.submit();
}

function fn_egov_select(methdAnt, methdNm, classNm, methdId, classId){
	var rowCount = document.listForm.requestRowCount.value;
	var rowIndex = (Number(document.listForm.requestRowIndex.value)-1)/2;
	if(rowCount > 1){
		window.opener.document.detailForm.methdAnt[rowIndex].value = methdAnt;
		window.opener.document.detailForm.methdNm[rowIndex].value = methdNm;
		window.opener.document.detailForm.classNm[rowIndex].value = classNm;
		window.opener.document.detailForm.methdId[rowIndex].value = methdId;
		window.opener.document.detailForm.classId[rowIndex].value = classId;
	}else{
		window.opener.document.detailForm.methdAnt.value = methdAnt;
		window.opener.document.detailForm.methdNm.value = methdNm;
		window.opener.document.detailForm.classNm.value = classNm;
		window.opener.document.detailForm.methdId.value = methdId;
		window.opener.document.detailForm.classId.value = classId;
	}
	window.close();
}

</script>
<!-- 업무 scrpit END -->

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->
<div id="wrap">

	<!-- 메인 시작 -->
	<div id="container">
	
	<div id="content">
	<!-- BODY 내용 START -->
				
		<form:form commandName="classVO" name="listForm" method="post">
		<input type="hidden" name="requestRowCount" value="<egovc:out value='<%=request.getParameter("rowCount")%>'/>"/>
		<input type="hidden" name="requestRowIndex" value="<egovc:out value='<%=request.getParameter("rowIndex")%>'/>"/>
		<input type="hidden" name="classId" />
		<input type="hidden" name="popupAt" />
		<div id="content_pop">
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 어노테이션 목록 조회</strong></h1></div>
		<!-- // 타이틀 -->		
			
			<!-- 검색 시작 -->
			<div id="search_area01">
				<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="search" class="imgs" /></li>
					<li><label style="display: none;">메소드 검색</label>
						<select name="searchCondition" class="select" tabindex="1">
							<option value='0' <c:if test="${classVO.searchCondition == '0'}">selected="selected"</c:if>>클래스명</option>
							<option value='1' <c:if test="${classVO.searchCondition == '1'}">selected="selected"</c:if>>메소드명</option>
							<option value='2' <c:if test="${classVO.searchCondition == '2'}">selected="selected"</c:if>>어노테이션</option>							
						</select>
					</li>
					<li><input name="searchKeyword" type="text" size="35" value="${classVO.searchKeyword}"  maxlength="35" tabindex="2"/></li>
					<li><a href="javascript:fn_egov_selectList();">
							<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>" alt="검색" class="btn_search" />
						</a>
					</li>
				</ul>				
			</div>
			<!-- 검색 끝 -->

			<!-- List -->
			<div id="result_table">
				<table summary="메서드 명, 클래스 명, 타입입니다." width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>메소드 목록</caption>
					<colgroup>				
						<col width="130">
						<col width="100">
						<col width="150">
					</colgroup>		  
					<tr>
						<th align="center" scope="col">클래스명</th>
						<th align="center" scope="col">메서드명</th>
						<th align="center" scope="col">어노테이션</th>
					</tr>
				    <c:if test="${empty resultList}">
				    <tr>    
				   	  <td  colspan="3">
				    	검색 결과가 없습니다.
				  	  </td>
				    </tr>
				    </c:if>
				    <c:if test="${not empty resultList}">						
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="left" class="listtd"><c:out value="${result.classNm}"/>&nbsp;</td>
						<td align="left" class="listtd"><c:out value="${result.methdNm}"/>&nbsp;</td>
						<td align="left" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.methdAnt}"/>', '<c:out value="${result.methdNm}"/>', '<c:out value="${result.classNm}"/>', '<c:out value="${result.methdId}"/>', '<c:out value="${result.classId}"/>', '<c:out value="${result.methdId}"/>')"><c:out value="${result.methdAnt}"/></a></td>
						<td style="display:none"><c:out value="${result.classId}"/>&nbsp;</td>
						<td style="display:none"><c:out value="${result.methdId}"/>&nbsp;</td>
					</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>
			<!-- /List -->
            <!-- Pagination 시작 -->
			<div id="pagenav_div"><ui:pagination paginationInfo="${paginationInfo}" type="image"
				jsFunction="fn_egov_link_page" /> <form:hidden path="pageIndex" />
			</div>                     
            <!-- Pagination 끝-->

		</div>
		<!-- 검색조건 유지 -->
		<input type="hidden" name="rowCount" id="rowCount" value="<c:out value="${rowCount}"/>"/>
		<input type="hidden" name="rowIndex" id="rowIndex" value="<c:out value="${rowIndex}"/>"/>
		</form:form>
	<!-- BODY 내용 END -->

	</div>	
	<!-- 메인 끝 -->
	
</div>
<!-- //전체 DIV끝 -->
</div>
</body>
</html>	
