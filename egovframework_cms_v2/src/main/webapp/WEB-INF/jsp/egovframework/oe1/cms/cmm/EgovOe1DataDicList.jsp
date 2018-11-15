<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"       uri="http://www.springframework.org/security/tags" %>
<%
/**
 * @JSP Name : EgovOe1DataDicList.jsp
 * @Description : 자료사전목록조회
 * @Modification Information
 * 
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이중호         최초 생성
 *
 * author 실행환경팀
 * since 2011.07.01
 *  
 * Copyright (C) 2011 by MOPAS  All right reserved.
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>자료사전목록조회</title>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

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
 * 페이징 처리
 ******************************************************** */
function fn_egov_link_page(pageNo){
	var varForm				 = document.getElementById("listForm");
	varForm.action           = "<c:url value='/cms/metadata/common/selectDataDicList.do'/>";
	varForm.pageIndex.value  = pageNo;
	varForm.submit(); 	
}
/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_search(){
	var varForm				 = document.getElementById("listForm");
	varForm.action           = "<c:url value='/cms/metadata/common/selectDataDicList.do'/>";
	varForm.pageIndex.value  = 1;
	varForm.submit(); 
}
<sec:authorize ifAnyGranted="ROLE_ADMIN">
/* ********************************************************
 * 등록 화면으로 가기
 ******************************************************** */
function fn_regist(){
	var varForm				 = document.getElementById("listForm");
	varForm.action           = "<c:url value='/cms/metadata/admin/insertDataDicView.do'/>";
	varForm.submit();  	
}
</sec:authorize>
/* ********************************************************
 * 상세조회 화면으로 가기
 ******************************************************** */
function fn_detail(dtaDicaryId){
	var varForm				  = document.getElementById("listForm");
	varForm.action            = "<c:url value='/cms/metadata/common/selectDataDic.do'/>";
 	varForm.dtaDicaryId.value = dtaDicaryId;
	varForm.submit();
}
<sec:authorize ifAnyGranted="ROLE_ADMIN">
/* ********************************************************
 * 엑셀다운로드 처리
 ******************************************************** */
function fn_excel(){
	var varForm				 = document.getElementById("listForm");
	varForm.action           = "<c:url value='/cms/metadata/admin/selectDataDicListForExcel.do'/>";
	varForm.submit();  	
}
</sec:authorize>
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

			<div id="content_pop">			

				<form id="listForm" name="listForm" action="<c:url value='/cms/metadata/common/selectDataDicList.do'/>" method="post" onsubmit="fn_search();return false;">
    			<input type="submit" class="invisible">
				<input type="hidden" name="pageIndex" value="<c:out value="${searchVO.pageIndex}"/>"/>
				<input type="hidden" name="dtaDicaryId" value=""/>
				
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>자료사전목록조회 <span id="help" style="font-size:10px;border:1px solid black;">&nbsp;?&nbsp;</span></strong></h1></div>
				<div id="helpClick" style="position:absolute;overflow:auto;display:none;width:600px;height:130px;z-index:10; background-color:#cacaca; border:1px solid gray;">
					<ul>
						<li>
							<ul>
								<li>⊙ 도메인분류 - 도메인에 대한 상위분류 체계를 관리합니다.</li>
							</ul>
						</li>
						<li>&nbsp;</li>
						<li>
							<ul>
								<li>⊙ 도메인 - 데이터 항목에 대한 데이터 유형과 길이를 가지는 항목으로 특정 도메인분류에 종속되어 관리됩니다.</li>
							</ul>
						</li>
						<li>&nbsp;</li>
						<li>
							<ul>
								<li>⊙ 용어사전 - 사용할 용어의 기본단위의 사전을 관리합니다.(표준단어)</li>
							</ul>
						</li>
						<li>&nbsp;</li>
						<li>
							<ul>
								<li>⊙ 동의어 - 용어사전에 정의된 기본단위 용어의 동의어를 관리하여 동일 용어를 지향하도록 합니다.(비표준단어)</li>
							</ul>
						</li>
						<li>&nbsp;</li>
						<li>
							<ul style="color:blue;">
								<li>⊙ 자료사전 - 용어나 용어의 조합으로 데이터 항목으로 사용할 사전으로 도메인을 포함하여 관리합니다.</li>
							</ul>
						</li>
					</ul>
				</div>
				<script type="text/javaScript" language="javascript" defer="defer">
				 $("#help").click(function(e) {
					var x = e.pageX - this.offsetLeft;
					var y = e.pageY - this.offsetTop;
				 	$("#helpClick").css("display", "");
				 	$("#helpClick").css("left", x + 400);
				 	$("#helpClick").css("top" , y + 160);
				 });
				 $("#helpClick").click(function() {
				 	$(this).css("display", "none");
				 });
				</script>
				<!-- /타이틀 -->

				<!-- 검색영역 -->
				<div id="search_area01">
				<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색" /></li>
					<li>
						<label style="display: none;">자료사전검색</label>
						<select name="srchUseAt" class="select" title="검색조건">
							<option value='' selected>--사용여부--</option>
						   <option value='Y' <c:if test="${searchVO.srchUseAt == 'Y'}">selected="selected"</c:if>>사용</option>
						   <option value='N' <c:if test="${searchVO.srchUseAt == 'N'}">selected="selected"</c:if>>미사용</option>
						</select>
					</li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자료사전명 : <input name="srchDtaDicaryNm" type="text" style="width:120px;" value="${searchVO.srchDtaDicaryNm}"  maxlength="35" title="자료사전명"/></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처리자 : <input name="srchLastUpdusrNm" type="text" style="width:120px;" value="${searchVO.srchLastUpdusrNm}"  maxlength="35" title="처리자"/></li>
					<li><a href="#LINK" onClick="fn_search();return false;">
							<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>" alt="검색" class="btn_search" />
						</a></li>
				</ul>
				</div>
				<!-- /검색영역 -->

				<!-- List -->
				<div id="result_table">
				<table summary="순번, 코드ID, 코드, 코드명, 사용여부 목록입니다" class="table_style">
					<caption>사용자 권한 검색 결과</caption>
					<colgroup>
						<col width="50">
						<col width="140">
						<col width="*">
						<col width="120">
						<col width="100">
						<col width="100">
						<col width="100">
					</colgroup>
					
					<thead>
						<tr>
					        <th scope="col">순번</th>
					        <th scope="col">자료사전ID</th>
					        <th scope="col">자료사전명</th>
					        <th scope="col">자료사전영문명</th>
					        <th scope="col">사용여부</th>
					        <th scope="col">처리자</th>
					        <th scope="col">처리일자</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty resultList}"><tr><td colspan="7">검색 결과가 없습니다.</td></tr></c:if>
					<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
						<tr>
							<td>
								<c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/>
							</td>
							<td>
								<a href="<c:url value='/cms/metadata/common/selectDataDic.do'/>?dtaDicaryId=<c:out value='${resultInfo.dtaDicaryId}'/>" onclick="fn_detail('<c:out value="${resultInfo.dtaDicaryId}"/>'); return false;" class="board_text_link" title="상세조회" >
									<c:out value="${resultInfo.dtaDicaryId}" />
								</a>
							</td>
							<td scope="row" align="left">
								<c:out value="${resultInfo.dtaDicaryNm}" />
							</td>
							<td scope="row" align="left">
								<c:out value="${resultInfo.dtaDicaryEngNm}" />
							</td>
							<td>
								<c:if test="${resultInfo.useAt == 'Y'}">사용</c:if>
								<c:if test="${resultInfo.useAt == 'N'}">미사용</c:if>
							</td>
							<td>
								<c:out value="${resultInfo.lastUpdusrNm}" />
							</td>
							<td>
								<c:out value="${resultInfo.lastUpdtPnttm}" />
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- /List -->				
				
				<!-- 페이지 NAVI -->
				<div id="pagenav_div"><ui:pagination
					paginationInfo="${paginationInfo}" type="image"
					jsFunction="fn_egov_link_page" /></div>
				<!-- /페이지 NAVI -->
					
				<!-- 버튼 -->
				<div class="subbtn_align">          
				<ul>
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<li class="btn02_leftbg"></li>
					<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/admin/selectDataDicListForExcel.do'/>" onclick="fn_excel(); return false;" class="btn_link">엑셀다운로드</a></li>
					<li class="btn02_rightbg"></li>	
					</sec:authorize>			
				
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<li class="btn02_leftbg"></li>
					<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/admin/insertDomainInfoView.do'/>" onclick="fn_regist(); return false;" class="btn_link">등록</a></li>
					<li class="btn02_rightbg"></li>	
					</sec:authorize>			
				</ul>
				</div>
				<!-- /버튼 -->	
							
				</form>	
				
			</div>


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