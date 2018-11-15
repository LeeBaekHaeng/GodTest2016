<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @JSP Name : EgovResveMtgDuplCheckResult.jsp
  * @Description : 회의실예약 중  중복체크 팝업
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.11.01  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.11.01
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>회의실예약 중복 체크<c:out value="${registerFlag}"/> </title>

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
function fn_egov_duplicateCheck(checkVaue) {
	window.opener.document.resvemtgForm.dupCheck.value = checkVaue;
	window.close();
}
</script>
<!-- 업무 scrpit END -->
</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	


						<!-- form 시작 -->
						<form:form commandName="EgovOe1ResveMtgVO" name="listForm" method="post">
							<!-- content_pop 시작 -->	
							<div id="content_pop">
			
								<!-- 타이틀 시작-->
								<div id="h2_topnav"><h1><strong>회의실예약 중복 체크</strong></h1></div>
								<!-- 타이틀 끝 -->
								<!-- 목록 시작 -->
								<div id="result_table">
										<table rules="rows" frame="void"  class="list_area_ta" summary="번호, 사용자ID, 사용자명  목록입니다">
										<caption>회의실예약 목록</caption>
											<colgroup>
												<col width="50"/>				
												<col width="80"/>											
												<col width="100"/>				
												<col width="120"/>
												<col/>												
												<col width="80"/>
												<col width="60"/>
												<col width="70"/>													
											</colgroup>		
											<thead>  
											<tr>
												<th align="center">번호</th>
											    <th align="center">일정구분</th>
											    <th align="center">업무명</th>											    
											    <th align="center">회의시작/종료 일시</th>
											    <th align="center">제목</th>		
											    <th align="center">장소</th>
											    <th align="center">담당자</th>
											    <th align="center">반복구분</th>
											</tr>
											</thead>
											<tbody>
								    		<c:if test="${empty resultList}">
								    			<tr>    
								   		  			<td colspan="8">
									    			중복된 회의실예약이 없습니다. 확인 버튼을 클릭해야 회의실예약을 할 수 있습니다. 
								  	  				</td>
								    			</tr>
								    		</c:if>		
								    		<c:if test="${!empty resultList}">												
											  <c:forEach var="result" items="${resultList}" varStatus="status">
											  <tr>
											    <td align="center" 	class="table_no"><c:out value="${status.count}"/></td>
											    <td align="left" 	class="table_title"><c:out value="${result.schdulSeCodeName}"/></td>
											    <td align="left" 	class="table_title"><c:out value="${result.jobSeCodeName}"/></td>
											    <td align="center" 	class="table_title"><c:out value="${result.mtgStartDate}"/> ~ <c:out value="${result.mtgEndDate}"/></td>
											    <td align="left" 	class="table_title"><c:out value="${result.mtgNm}"/></td>	
											    <td align="left" 	class="table_title"><c:out value="${result.mtgPlaceIdName}"/></td>
											    <td align="center" 	class="table_title"><c:out value="${result.chargerName}"/></td>
											    <td align="left" 	class="table_title"><c:out value="${result.reptitSeCodeName}"/></td>													    										    
											  </tr>
											  </c:forEach>
										  		<tr>    
								   		  			<td colspan="8">
									    		  		<font color="red">회의실이 중복 되었습니다.  확인 후 다시 입력하세요.</font>
								  	  				</td>
								    			</tr>											  
											</c:if>
										  	</tbody>
										</table>
								</div>
							</div>
							<!-- content_pop 시작 -->
							<div class="subbtn_align">  
							<c:if test="${empty resultList}">
							    <ul>
									<li class="btn02_leftbg"></li>
									<li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_duplicateCheck('Y'); return false;" class="btn_link">확인</a></li>
									<li class="btn02_rightbg"></li>
							    </ul>
							</c:if>
							<c:if test="${!empty resultList}">
							    <ul>
									<li class="btn02_leftbg"></li>
									<li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_duplicateCheck('N'); return false;" class="btn_link">확인</a></li>
									<li class="btn02_rightbg"></li>
							    </ul>							
							</c:if>
							</div>				
						</form:form>								
						<!-- form 끝 -->
</body>
</html>
						