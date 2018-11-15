<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>
<%
 /**
  * @JSP Name : EgovRiskManageList.jsp
  * @Description : 위험 관리 목록 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.20  이중호         최초 생성
  *
  * author 운영환경 1팀
  * since 2010.08.20
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */ 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>위험 관리</title>

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

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_link_page(pageNo){
	document.riskManage.pageIndex.value = pageNo;
	document.riskManage.action = "<c:url value='/cms/stn/admin/EgovOe1RiskManageList.do'/>";
   	document.riskManage.submit();
}
/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fnSearch(){
	document.riskManage.pageIndex.value = 1;
   	document.riskManage.submit();
}
/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fnRegist(){
	location.href = "<c:url value='/cms/stn/admin/EgovOe1RiskManageRegist.do'/>";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fnModify(){
	location.href = "";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fnDetail(riskId){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/cms/stn/admin/EgovOe1RiskManageDetail.do'/>";
	varForm.riskId.value     = riskId;
	varForm.submit();
}


</script>
<!-- 업무 scrpit END -->

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

			<form id="Form" name="Form" method="post" action="/" >
			<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"/></div>
				<input type="hidden" name="riskId"/>
			</form>
			<form:form commandName="riskManage" name="riskManage" action="?" method="post" onsubmit="javascript:fnSearch(); return false;">
			<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"/></div>
			<div id="content_pop">			

				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>위험 관리</strong></h1></div>
				<!-- /타이틀 -->

				<!-- 검색영역 -->
				<div class="search_area_submit">
					<ul>
						<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="search" class="searchimg" /></li>
						<li>&nbsp;&nbsp;
							<form:select path="searchRiskSttusCode" cssClass="use" cssStyle="width:100px;" title="처리상태" tabindex="1">
							<form:option value="" label="--처리상태--"   />
				           	<c:forEach var="codeinfo" items="${riskSttusCodeList}" varStatus="status">
								<form:option value="${codeinfo.code}" label="${codeinfo.codeNm}"   />
						  	</c:forEach>  
				          	</form:select>		
						</li>
						<li>
							<form:select path="searchRiskTyCode" cssClass="use" cssStyle="width:100px;" title="위험구분" tabindex="2">
							<form:option value="" label="--위험구분--"   />
				           	<c:forEach var="codeinfo" items="${riskTyCodeList}" varStatus="status">
								<form:option value="${codeinfo.code}" label="${codeinfo.codeNm}"   />
						  	</c:forEach>  
				          	</form:select>			
						</li>
						<li>
								<form:select path="searchDgdgr" cssClass="use" cssStyle="width:90px;" title="위험도" tabindex="3">
								<form:option value="" label="--위험도--"   />
					           	<c:forEach var="codeinfo" items="${dgdgrList}" varStatus="status">
									<form:option value="${codeinfo.code}" label="${codeinfo.codeNm}" />
							  	</c:forEach>  
					          	</form:select>
						</li>						
						<li>&nbsp;
							<select name="searchCondition" class="select" style="width:90px;" title="검색조건" tabindex="4">
								<option  value=''>--세부구분--</option>
								<option value='1' <c:if test="${riskManage.searchCondition == '1'}">selected="selected"</c:if>>위험명</option>
								<option value='2' <c:if test="${riskManage.searchCondition == '2'}">selected="selected"</c:if>>위험내용</option>
							</select>
						</li>
						<li>
							<input name="searchKeyword" type="text" title="검색어" style="width:150px;" value="${riskManage.searchKeyword}"  maxlength="35" class="input01" tabindex="5"/>
						</li>
						<li>				
						    <div class="submit_gray_btn_top">		
							<ul>
								<li class="submit_gray_btn_left"></li>
								<li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fnSearch(); return false;" class="submit_btn_input" /></span></li>
								<li class="submit_gray_btn_right"></li>
							</ul>
							</div>	
						</li>
					</ul>	
				</div>
				<!-- /검색영역 -->

				<!-- List -->
				<div id="result_table">
				<table summary="위험명,처리상태,위험구분,위험도,등록자,등록일,예방활동결과,예방활동종료일 목록입니다" class="table_style">
					<caption>위험정보 검색 결과</caption>
					<colgroup>
						<col width="30">
						<col width="170">
						<col width="60">
						<col width="60">
						<col width="50">
						<col width="80">
						<col width="70">
						<col width="60">
						<col width="70">
					</colgroup>
					<thead>
						<tr>
					        <th scope="col">순번</th>
					        <th scope="col">위험명</th>
					        <th scope="col">처리상태</th>
					        <th scope="col">위험구분</th>
					        <th scope="col">위험도</th>
					        <th scope="col">등록자</th>
					        <th scope="col">등록일</th>
					        <th scope="col">예방활동<br>결과</th>
					        <th scope="col">예방활동<br>종료일</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty resultList}">
		    			<tr>    
		   		  			<td colspan="9">
			    			검색 결과가 없습니다.
		  	  				</td>
		    			</tr>
		    		</c:if>
					<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
						<tr>
							<td>
								<c:out value="${paginationInfo.totalRecordCount - (riskManage.pageIndex - 1) * riskManage.pageSize - status.count + 1}"/>
							</td>
							<td align="left">
								<a href="<c:url value='/cms/stn/admin/EgovOe1RiskManageDetail.do'/>?riskId=<c:out value='${resultInfo.riskId}'/>" onclick="fnDetail('<c:out value="${resultInfo.riskId}"/>'); return false;" class="board_text_link">
									<c:out value="${resultInfo.riskSj}" />
								</a>
							</td>
							<td>
								<c:choose>
									<c:when test="${not empty resultInfo.riskSttusCodeNm}">
										<c:out value="${resultInfo.riskSttusCodeNm}" />&nbsp;
									</c:when>
									<c:otherwise>
										<c:out value="-"/>&nbsp;
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:out value="${resultInfo.riskTyCode}" />
							</td>
							<td>
								<c:out value="${resultInfo.dgdgr}" />
							</td>
							<td>
								<c:out value="${resultInfo.frstRegisterNm}" />
							</td>
							<td>
								<c:out value="${resultInfo.frstRegisterPnttm}" />
							</td>
							<td>
								<c:choose>
									<c:when test="${not empty resultInfo.prevntActResultSe}">
										<c:out value="${resultInfo.prevntActResultSe}" />&nbsp;
									</c:when>
									<c:otherwise>
										<c:out value="-"/>&nbsp;
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${not empty resultInfo.prevntActEndDe}">
										<c:set var="endDe" value="${resultInfo.prevntActEndDe}"/><c:out value="${fn:substring(endDe,0,4)}"/>-<c:out value="${fn:substring(endDe,4,6)}"/>-<c:out value="${fn:substring(endDe,6,8)}"/>
									</c:when>
									<c:otherwise>
										<c:out value="-"/>&nbsp;
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- /List -->				
				
				<!-- 페이지 NAVI -->
				<div id="pagenav_div">
					<ui:pagination 	paginationInfo="${paginationInfo}"
									type="image"
									jsFunction="fn_egov_link_page" /> 
					<input type="hidden" name="pageIndex" />
				</div>
				<!-- /페이지 NAVI -->
				
				<!-- 버튼 -->				
			  	<div class="subbtn_align">  		
				  	<ul>
			  			<li class="btn02_leftbg"></li>
					    <li class="btn02_middlebg"><a href="<c:url value='/cms/stn/admin/EgovOe1RiskManageRegist.do'/>" onclick="fnRegist(); return false;" class="btn_link">등록</a></li>
						<li class="btn02_rightbg"></li>
			  		</ul>
			  	</div> 				
				<!-- /버튼 -->				

			</div>
			</form:form>

		<!-- BODY 내용 END -->
		</div>	

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>
