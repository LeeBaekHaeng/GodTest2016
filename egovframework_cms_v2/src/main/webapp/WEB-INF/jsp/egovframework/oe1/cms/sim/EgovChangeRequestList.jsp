<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name    : EgovChangeRequestList.jsp
  * @Description : 변경요청서 목록 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.10  김영심          최초 생성
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
<title>변경요청서 목록</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/*검색버튼 클릭 시, 조회 요청 */
function fn_egov_selectList() {
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/cms/sim/gnrl/getChangeRequestList.do'/>";
   	document.listForm.submit();		
}

/*특정페이지 조회 요청*/
function fn_egov_linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/sim/gnrl/getChangeRequestList.do'/>";
   	document.listForm.submit();
}
/*변경요청서등록처리 화면요청*/
function fn_egov_linkRegist() {
	document.listForm.action = "<c:url value='/cms/sim/gnrl/changeRequestRegistView.do'/>";
   	document.listForm.submit();		
}

/*변경요청서상세화면 요청 */
function fn_egov_linkDetail(id,code){
	document.listForm.changeRequstId.value = id;
	document.listForm.changeProcessSttusCode.value = code;
	document.listForm.action = "<c:url value='/cms/sim/gnrl/changeRequestDetailSelect.do'/>";
   	document.listForm.submit();
}
//-->
</script>

</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap"><!-- header 시작 -->
<a href="#top_menu" class="hide_a"> </a>
<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
<!-- //header 끝 --> <!-- 메인 시작 -->
<div id="container"><!-- 좌메뉴 시작 -->
<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->
<form:form commandName="changeRequestvo" name="listForm" action="/oe1/cms/sim/gnrl/getChangeRequestList.do" method="post"  onsubmit="javascript:fn_egov_selectList(); return false;">
<input type="hidden" name="changeRequstId" />
<input type="hidden" name="changeProcessSttusCode" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
<div id="content_pop"><!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong>변경요청서 목록</strong></h1></div>
	<div class="search_area_submit">
		<ul>
		<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" /></li>
		<li>
          <select name="searchChangeProcessSttusCode" id="searchChangeProcessSttusCode" style="width:100px" tabindex="1"  title="처리상태선택">
            <option value=''>--처리상태--</option>
            <c:forEach var="codeinfo" items="${changeProcessSttusCodeList}" varStatus="status">
		    	<option value='${codeinfo.code}' <c:if test="${searchVO.searchChangeProcessSttusCode== codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		    </c:forEach>
          </select>
		</li>
		<li>
          <select name="searchOperJobSeCode" id="searchOperJobSeCode" style="width:100px" tabindex="2" title="업무구분선택">
            <option value=''>--업무구분--</option>
            <c:forEach var="codeinfo" items="${operJobSeCodeList}" varStatus="status">
		    	<option value='${codeinfo.code}' <c:if test="${searchVO.searchOperJobSeCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		    </c:forEach>
          </select>
		</li>
		<li>
          <select name="searchEmrgncyRequstAt" id="searchEmrgncyRequstAt" style="width:100px" tabindex="3" title="긴급여부선택">
          	<option value='' >--긴급여부--</option>
           	<c:forEach var="codeinfo" items="${emrgncyRequstAtList}" varStatus="status">
            	<option value='${codeinfo.code}' <c:if test="${searchVO.searchEmrgncyRequstAt == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          </select>
		</li>
		<li>
          <select name="searchCondition" id="searchCondition" style="width:100px" tabindex="4" title="세부구분선택">
            <option value=''  <c:if test="${searchVO.searchCondition == ''}">selected="selected"</c:if>>--세부구분--</option>
		    <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>변경요청명</option>
		    <option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>>변경요청자명</option>
          </select>
		</li>
		<li>
		<input name="searchKeyword" id="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" title="검색어" tabindex="4" class="input01" style="width:100px" maxlength="16"/>
		</li>
		<li>
		    <div class="submit_gray_btn_top">		
			<ul>
				<li class="submit_gray_btn_left"></li>
				<li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
				<li class="submit_gray_btn_right"></li>
			</ul>
			</div>	
		</li>
		</ul>
	</div>

	<div id="result_table">
	<table  width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style" summary="이 표는 변경요청서관리정보를 제공하며 변경요청명, 처리상태, 업무구분, 요청일시, 요청자, 긴급여부 정보로 구성되어 있습니다.">
	<caption>변경요청서목록</caption>
	<colgroup>
		<col width="5%">				
		<col width="40%">
		<col width="10%">  
		<col width="15%">
		<col width="11%">			
		<col width="10%">
		<col width="9%">    
	</colgroup>		
		<thead>
  			<tr>
    			<th  scope="col">순번</th>
    			<th  scope="col">변경요청명</th>
    			<th  scope="col">처리상태</th>
    			<th  scope="col">업무구분</th>
    			<th  scope="col">요청일시</th>
    			<th  scope="col">요청자</th>
    			<th  scope="col">긴급여부</th>
  			</tr>
  		</thead>
  		<tbody>
    		<c:if test="${empty resultList}">
    			<tr>    
   		  			<td colspan="7">
	    			검색 결과가 없습니다.
  	  				</td>
    			</tr>
    		</c:if>
   	 		<c:if test="${!empty resultList}">
	  			<c:forEach var="changeRequestvo" items="${resultList}" varStatus="status">
	  			<tr>
	    			<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
	    			<c:choose>
	    			<c:when test="${fn:length(changeRequestvo.changeRequstNm) >= 30}">
	    			<td align="left"><a href="<c:url value='/cms/sim/gnrl/changeRequestDetailSelect.do'/>?changeRequstId=<c:out value="${changeRequestvo.changeRequstId}"/>&amp;changeProcessSttusCode=<c:out value="${changeRequestvo.changeProcessSttusCode}"/>" onclick="fn_egov_linkDetail('<c:out value="${changeRequestvo.changeRequstId}"/>','<c:out value="${changeRequestvo.changeProcessSttusCode}"/>'); return false;" class ="board_text_link" ><c:out value="${fn:substring(changeRequestvo.changeRequstNm,0,30)}"/></a></td>
	    			</c:when>
	    			<c:when test="${fn:length(changeRequestvo.changeRequstNm) < 30}">
	    			<td align="left"><a href="<c:url value='/cms/sim/gnrl/changeRequestDetailSelect.do'/>?changeRequstId=<c:out value="${changeRequestvo.changeRequstId}"/>&amp;changeProcessSttusCode=<c:out value="${changeRequestvo.changeProcessSttusCode}"/>" onclick="fn_egov_linkDetail('<c:out value="${changeRequestvo.changeRequstId}"/>','<c:out value="${changeRequestvo.changeProcessSttusCode}"/>'); return false; " class ="board_text_link" ><c:out value="${changeRequestvo.changeRequstNm}"/></a></td>
	    			</c:when>
	    			</c:choose>
	    			<td><c:out value="${changeRequestvo.changeProcessSttusCodeNm}"/></td>
	    			<td><c:out value="${changeRequestvo.operJobSeCode}"/></td>
					<td class="hardbreak" align="left"><span><c:out value="${changeRequestvo.changeRequstDe}"/></span></td>
	    			<td><c:out value="${changeRequestvo.changeRqesterId}"/></td>
	    			<c:choose>
	    			<c:when test="${changeRequestvo.emrgncyRequstAt == '긴급'}">
	    			<td><font color="red"><c:out value="${changeRequestvo.emrgncyRequstAt}"/></font></td>
	    			</c:when>
	    			<c:when test="${changeRequestvo.emrgncyRequstAt == '일반'}">
	    			<td><font color="green"><c:out value="${changeRequestvo.emrgncyRequstAt}"/></font></td>
	     			</c:when>
	    			</c:choose>  
	  			</tr>
	  			</c:forEach>
			</c:if>  
  		</tbody>
 	</table>
	</div>
	
	<!--  page start -->
	<div id="pagenav_div">
	<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_linkPage" />
	</div>
	<!--  page end -->
		
	<div class="subbtn_align">
		<ul>
			<li class="btn02_leftbg"></li>
			<li class="btn02_middlebg"><a href="<c:url value='/cms/sim/gnrl/changeRequestRegistView.do'/>" onclick="fn_egov_linkRegist(); return false;" class="btn_link"  tabindex="5">등록</a></li>
			<li class="btn02_rightbg"></li>
		</ul>
	</div>

 </div>
</form:form>
<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->
</body>
</html>
