<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
  /**
  * @JSP Name : EgovSchdulManageDetail.jsp
  * @Description : 전체일정 상세조회
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
<title>전체일정 상세정보 화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/cmm/jquery-ui-1.8.4.custom.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

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

function fn_list(){
	if(document.DeptSchdulManageForm.viewType.value=="C"){
		document.DeptSchdulManageForm.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageMonthList.do'/>";
	}else if(document.DeptSchdulManageForm.viewType.value=="W"){
		document.DeptSchdulManageForm.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageWeekList.do'/>";
	}else if(document.DeptSchdulManageForm.viewType.value=="D"){
		document.DeptSchdulManageForm.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageDailyList.do'/>";
	}else if(document.DeptSchdulManageForm.viewType.value=="L"){
		document.DeptSchdulManageForm.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageList.do'/>";
	}
	
	document.DeptSchdulManageForm.submit();
}


function fn_update(){
	document.DeptSchdulManageForm.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageModify.do'/>";
	document.DeptSchdulManageForm.submit();
}

function fn_delete(){
	if(confirm("삭제하시겠습니까?")){
	document.DeptSchdulManageForm.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageDelete.do'/>";
	document.DeptSchdulManageForm.submit();
	}
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

		<!-- BODY 내용 START -->
		<div id="content">

		<!-- form 시작 -->
		<form:form commandName="resultVO" name="DeptSchdulManageForm" method="post">
		
			<!-- 목록조회로 돌아갈 때 검색조건 유지 -->
			<input type="hidden" name="searchCondition" 	value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" 		value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" 			value="<c:out value='${searchMode.pageIndex}'/>"/>
			<input type="hidden" name="searchSchdulSeCode" 	value="<c:out value='${searchMode.searchSchdulSeCode}'/>"/>	
			<input type="hidden" name="viewType"			value="<c:out value='${searchMode.viewType}'/>" />		
			
			<!-- content_pop 시작 -->
			<div id="content_pop">

			<!-- 타이틀 시작 -->
			<div id="h2_topnav">
			<h1><strong>일정 상세</strong></h1>
			</div>
			<!-- // 타이틀 끝 -->			
			
			<!-- 리스트 시작 -->
			<div id="datail_table">
			<table summary="전체일정 상세 정보">
			<caption>전체일정 상세 정보</caption>			

				<tr>
					<th scope="row">일정ID</th>
					<td>
						<c:out value="${resultVO.schdulId}"/>
						<!-- 수정 및 삭제 때 ID 유지 -->
						<input type="hidden" name="schdulId" value="<c:out value="${resultVO.schdulId}"/>"/>		
					</td>
				</tr>

				<tr>
					<th scope="row">일정구분</th>
					<td>
						<c:out value="${resultVO.codeNmOfSchdulSe}"/>
					</td>
				</tr>

				<tr>
					<th scope="row">중요도</th>
					<td>
						<c:out value="${resultVO.codeNmOfSchdulIpcrCode}"/>
					</td>
				</tr>
								
				<tr>
					<th scope="row">일정명</th>
					<td>
						<c:out value="${resultVO.schdulNm}"/>
					</td>
				</tr>		
				
				<tr>
					<th scope="row">일정내용</th>
					<td>${fn:replace(fn:escapeXml(resultVO.schdulCn),crlf,"<br/>")}</td>					
				</tr>					
				
				<tr>
					<th scope="row">반복구분</th>
					<td>
						<c:out value="${resultVO.codeNmOfReptitSeCode}"/>
					</td>
				</tr>							
				<c:if test="${resultVO.reptitSeCode  eq 'W'}">
				<tr> 
				  	<th scope="row">반복요일</th>
				  	<td colspan="5"><c:out value="${resultVO.reptitDfkName}"/>
				   	</td>
				</tr>
				</c:if>	
				<tr>
					<th scope="row">시작일시</th>
					<td>
					<c:out value="${resultVO.schdulBgnde}"/>
					</td>
				</tr>

				<tr>
					<th scope="row">종료일시</th>
					<td>
						<c:out value="${resultVO.schdulEndde}"/>
					</td>
				</tr>

				<tr>
					<th scope="row">장소</th>
					<td>
						<c:out value="${resultVO.schdulPlace}"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row">담당자</th>
					<td>
						<c:out value="${resultVO.schdulChargerName}"/>
						<!-- 수정 시 담당자 ID 유지 -->
						<input type="hidden" name="schdulChargerId" value="<c:out value="${resultVO.schdulChargerId}"/>"/>
					</td>
				</tr>		

				<tr>
					<th scope="row">첨부파일</th>
					<td >
					    <div id="file">
				  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
				    			<c:param name="param_atchFileId" value="${resultVO.atchFileId}" />
				    		</c:import> 
						</div>
					</td>
				</tr>
								
				
			</table>
		  </div>
		  <!-- 리스트 끝 -->

		  <!-- 버튼 시작 -->
			<div class="subbtn_align"> 
			    <ul>
			    	<c:if test="${resultVO.frstRegisterId == s_mberId}">
			        <li class="btn02_leftbg"></li>
			        <li class="btn02_middlebg"><a href="<c:url value = '/cms/cmm/EgovOe1SchdulManageModify.do'/>?schdulId=<c:out value="${resultVO.schdulId}"/>" onclick="fn_update();return false;" class="btn_link">수정</a></li>
			        <li class="btn02_rightbg"></li>
			        <li class="btn02_leftbg"></li>
			        <li class="btn02_middlebg"><a href="<c:url value = '/cms/cmm/EgovOe1SchdulManageDelete.do'/>?schdulId=<c:out value="${resultVO.schdulId}"/>" onclick="fn_delete(); return false;" class="btn_link">삭제</a></li>
			        <li class="btn02_rightbg"></li>
			        </c:if>	
			        <li class="btn02_leftbg"></li>
			        <li class="btn02_middlebg"><a href="<c:url value = '/cms/cmm/EgovOe1SchdulManageList.do'/>" onclick="fn_list();return false;" class="btn_link">목록</a></li>
			        <li class="btn02_rightbg"></li>				        
			    </ul>
			</div>	  		  
  		<!-- 버튼 끝 -->
  		
  		</div>
  		<!-- content_pop 끝 -->
  					
  		</form:form>
  		<!-- form 끝 -->
  		
		</div>	
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>
