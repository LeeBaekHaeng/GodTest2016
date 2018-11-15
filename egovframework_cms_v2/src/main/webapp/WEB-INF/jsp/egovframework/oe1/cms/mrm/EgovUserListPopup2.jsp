<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @JSP Name : EgovUserListPopup2.jsp
  * @Description : 회의실예약 관리자 선택 팝업
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>사용자목록<c:out value="${registerFlag}"/> </title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" />

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
function pageInit() {
	<c:if test="${fn:length(memberList) == 1}">
		fn_egov_select("<c:out value='${memberList[0].mngrId}'/>", "<c:out value='${memberList[0].mngrNm}'/>");
	</c:if>
}
/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/mrm/inquiryGeneralMemberListPopup2.do'/>";
   	document.listForm.submit();
}
function fn_egov_select(mngrId, mngrNm) {
	window.opener.document.resvemtgForm.chargerId.value = mngrId;
	window.opener.document.resvemtgForm.chargerName.value = mngrNm;
	window.close();
}

/* 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/cms/mrm/inquiryGeneralMemberListPopup2.do'/>";
   	document.listForm.submit();
}
</script>
<!-- 업무 scrpit END -->
</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	


						<!-- form 시작 -->
						<form:form commandName="egovOe1MtgRmVO" name="listForm" method="post">
						<input type="hidden" name="pageIndex" value="<c:out value="${egovOe1MtgRmVO.pageIndex}"/>"/>
							<!-- content_pop 시작 -->	
							<div id="content_pop">
			
								<!-- 타이틀 시작-->
								<div id="h2_topnav"><h1><strong>사용자목록</strong></h1></div>
								<!-- 타이틀 끝 -->
								<div class="search_area_submit">
								<ul>
									<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" /></li>
									<li>
										<label style="display: none;">사용자검색</label>
										<form:select path="searchCondition"  tabindex="1" title="검색조건">																						
											<form:option value="name"  	label="사용자명" />
											<form:option value="tim" 	label="소속팀" />
											<form:option value="cop" 	label="소속사" />
										</form:select>									
										<input name="searchKeyword" id="searchKeyword" value="<c:out value='${searchMode.searchKeyword}'/>" tabindex="2" class="input01"  style="width:150px;"  title="searchKeyword"/>							
									</li>
									<li>
										<ul>
											<li class="btn02_leftbg"></li>
										    <li><input type="button" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn01"></li>
									        <li class="btn02_rightbg"></li>
										</ul>	
									</li>
								</ul>
								</div>			
								<!-- 목록 시작 -->
								<div id="result_table">
										<table rules="rows" frame="void"  class="list_area_ta" summary="번호, 사용자ID, 사용자명  목록입니다">
										<caption>사용자 목록</caption>
											<colgroup>
												<col width="80"/>				
												<col width="150"/>
												<col width="150"/>
												<col width="150"/>												
												<col/>
											</colgroup>		
											<thead>  
											<tr>
												<th align="center">번호</th>
											    <th align="center">사용자ID</th>
											    <th align="center">사용자명</th>
											    <th align="center">소속팀</th>
											    <th align="center">소속사</th>		
											</tr>
											</thead>
											<tbody>
								    		<c:if test="${empty memberList}">
								    			<tr>    
								   		  			<td colspan="5">
									    			검색 결과가 없습니다.
								  	  				</td>
								    			</tr>
								    		</c:if>		
								    		<c:if test="${!empty memberList}">												
											  <c:forEach var="result" items="${memberList}" varStatus="status">
											  <tr onmouseover="this.style.backgroundColor='#ffe4d2'; this.style.color='#ffffff'" onmouseout="this.style.backgroundColor='white'; this.style.color='#000000'">
											    <td class="table_no"><c:out value="${paginationInfo.totalRecordCount - (egovOe1MtgRmVO.pageIndex - 1) * egovOe1MtgRmVO.pageSize - status.count + 1}"/></td>
											    <td class="table_content"><c:out value="${result.mberId}"/></td>
											    <td class="table_title"><a href="#" title="사용자상세" class="board" onclick="javascript:fn_egov_select('<c:out value="${result.mberId}"/>', '<c:out value="${result.mberNm}"/>')" onkeypress="javascript:fn_egov_select('<c:out value="${result.mberId}"/>', '<c:out value="${result.mberNm}"/>')"><c:out value="${result.mberNm}"/></a></td>
											    <td class="table_title"><c:out value="${result.groupName}"/></td>
											    <td class="table_title"><c:out value="${result.pstinstName}"/></td>											    
											  </tr>
											  </c:forEach>
											</c:if>
										  	</tbody>
										</table>
								</div>
								<!-- 목록 끝 -->
								<div id="pagenav_div">
									<ui:pagination paginationInfo = "${paginationInfo}"	 type="image"	 jsFunction="fn_egov_link_page"	   />
									<!--<form:hidden path="pageIndex" />-->
								</div>										

							</div>
							<!-- content_pop 시작 -->
			
						</form:form>								
						<!-- form 끝 -->


</body>
</html>
						