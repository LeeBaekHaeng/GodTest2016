<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>

<%
  /**
  * @JSP Name : EgovUserManage.jsp
  * @Description : 사용자 정보  목록 화면
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                 최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-language" content="ko">
<title>사용자 정보  목록 화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

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
function fn_selectUser(id) {
	document.listForm.uniqId.value = id;
   	document.listForm.action = "<c:url value='/cms/usr/EgovOe1UserSelect.do'/>";
   	document.listForm.submit();		
}

function fn_addUser(){
	document.listForm.action = "<c:url value='/cms/com/EgovOe1UserInsertView.do'/>";
   	document.listForm.submit();		
}

function fn_egov_selectList() {
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/cms/sys/EgovOe1UserManage.do'/>";
   	document.listForm.submit();
}

function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/sys/EgovOe1UserManage.do'/>";
   	document.listForm.submit();
}

/*엑셀다운로드*/
function fn_egov_addExcelDown(){
	if(confirm("엑셀 다운로드시 시간이 오래 걸릴 수 있습니다. \n다운 받겠습니까? ")){
   		document.listForm.action = "<c:url value='/cms/sys/EgovOe1UserExcelDown.do'/>";
   		document.listForm.submit();	
	}
}

</script>
<!-- 업무 scrpit END -->

</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	
  	<a href="#top_menu" class="hide_a" title="숨김"></a>  
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
					<!-- form 시작 -->
					<form:form commandName="userSearchVO" name="listForm" method="post" action="oe1/cms/sys/EgovOe1UserManage.do" onsubmit="javascript:fn_egov_selectList(); return false;">

					<!-- 상세 조회 시 ID 유지 -->
					<input type="hidden" name="uniqId" />

						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>사용자정보목록</strong></h1>
						</div>
						<!-- // 타이틀 -->
						
						<!-- 검색영역 -->
						<div class="search_area_submit">
						<ul>
							<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" title="검색" /></li>
							<li>				
									<form:select path="searchCondition" cssClass="use" title="searchCondition">
										<form:option value="0" label="사용자ID" />
										<form:option value="1" label="사용자명" />
									</form:select>
							</li>
							<li>
									<input name="searchKeyword" id="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>" class="input01"  style="width:150px;"  title="searchKeyword"/>							
							</li>
								<li>
									<ul>
									    <li class="submit_btn01_left"></li>
									    <li><input type="submit" value="검색" onclick="fn_egov_link_page('1'); return false;" class="submit_btn01"></li>
									    <li class="submit_btn01_right"></li>
									</ul>	
								</li>
								</ul>
							</div>
						<!-- /검색영역 -->							
							
							<!-- 목록 시작-->
								<div id="result_table">
								<table summary="순번, 사용자ID, 사용자이름, 이메일  목록입니다" class="table_style">
									<caption>사용자정보목록  결과</caption>
									<colgroup>
										<col width="20">				
										<col width="100">
										<col width="100">
										<col width="100">
										<col width="100">
										<col width="100">
									</colgroup>		  
									<tr>
										<th scope="col" align="center">순번</th>
										<th scope="col" align="center">사용자ID</th>
										<th scope="col" align="center">사용자이름</th>
										<th scope="col" align="center">이메일</th>
										<th scope="col" align="center">핸드폰번호</th>
										<th scope="col" align="center">가입일자</th>
									</tr>
									<c:if test="${empty resultList}">
						    			<tr>    
						   		  			<td colspan="6">
							    			검색 결과가 없습니다.
						  	  				</td>
						    			</tr>
						    		</c:if>									
									<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td align="center"><c:out value="${paginationInfo.totalRecordCount - (userSearchVO.pageIndex - 1) * userSearchVO.pageSize - status.count + 1}"/></td>
										<td scope="row" align="left">
										
											<!-- 2010.11.11 
											<a href="#Link" onclick="javascript:fn_selectUser('<c:out value="${result.uniqId}"/>'); return false;" class="board_text_link">
												<c:out value="${result.mberId}"/>
											</a>
											-->
											
											<a href="<c:url value='/cms/usr/EgovOe1UserSelect.do'/>?uniqId=<c:out value='${result.uniqId}'/>" onclick="fn_selectUser('<c:out value="${result.uniqId}"/>'); return false;" class="board_text_link" title="상세조회">
												<c:out value="${result.mberId}"/>
											</a>
											
										</td>
										<td align="center"><c:out value="${result.mberNm}"/></td>
										<td align="center"><c:out value="${result.mberEmailAdres}"/></td>
										<td align="center"><c:out value="${result.moblphonNo}"/></td>
										<td align="center"><c:out value="${result.sbscrbDe}"/></td>
									</tr>
									</c:forEach>
								</table>
							</div>
							<!-- 목록 끝 -->

							<div id="pagenav_div">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" /> 
								<form:hidden path="pageIndex" />
							</div>	
							<div class="subbtn_align">          
							<ul>
							    <li class="submit_btn01_left"></li>
							    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fn_addUser(); return false;" /></span></li>
							    <li class="submit_btn01_right"></li>
								<li class="btn02_leftbg"></li>
								<li class="btn02_middlebg"><a href="<c:url value='/cms/sys/EgovUserListExcelDwon.do'/>" onclick="fn_egov_addExcelDown(); return false;" class="btn_link">엑셀다운로드</a></li>
								<li class="btn02_rightbg"></li>									    
							</ul>
							</div>
							<!-- 버튼 끝 -->
					</form:form>
					<!-- form 끝 -->					
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
