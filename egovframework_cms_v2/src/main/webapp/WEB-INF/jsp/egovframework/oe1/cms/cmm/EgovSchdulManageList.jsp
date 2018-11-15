<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
  /**
  * @JSP Name : EgovSchdulManageList.jsp
  * @Description : 전체일정 목록조회
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
<title>전체일정 목록조회 화면</title>

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
/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.deptSchdulManageVO.pageIndex.value = 1;
	document.deptSchdulManageVO.action = "<c:url value='/cms/cmm/EgovOe1SchdulManageList.do'/>";
   	document.deptSchdulManageVO.submit();
}

function fn_egov_link_page(pageNo){
	document.deptSchdulManageVO.pageIndex.value = pageNo;
	document.deptSchdulManageVO.action = "<c:url value='/cms/cmm/EgovOe1SchdulManageList.do'/>";
	document.deptSchdulManageVO.submit();
}

function fn_select(id){
	document.deptSchdulManageVO.schdulId.value = id;
	document.deptSchdulManageVO.action = "<c:url value='/cms/cmm/EgovOe1SchdulManageDetail.do'/>";
	document.deptSchdulManageVO.submit();
}

function fn_regist(){
	document.deptSchdulManageVO.action = "<c:url value='/cms/cmm/EgovOe1SchdulManageRegist.do'/>";
	document.deptSchdulManageVO.submit();
}

function fnTabMenuSelect(objArr){
	if(objArr == 0){
		document.deptSchdulManageVO.action ="<c:url value='/cms/cmm/EgovOe1SchdulManageMonthList.do'/>";
		document.deptSchdulManageVO.submit();
	}if(objArr == 1){
		document.deptSchdulManageVO.action ="<c:url value='/cms/cmm/EgovOe1SchdulManageWeekList.do'/>";
		document.deptSchdulManageVO.submit();
	}if(objArr == 2){
		document.deptSchdulManageVO.action ="<c:url value='/cms/cmm/EgovOe1SchdulManageDailyList.do'/>";
		document.deptSchdulManageVO.submit();
	}
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

		<!-- BODY 내용 START -->
		<div id="content">
		
		<!-- form 시작 -->
		<form:form commandName="deptSchdulManageVO" name="deptSchdulManageVO" method="post" onsubmit="fn_egov_selectList(); return false;">
		
		<!-- 상세 조회를 위한 ID 유지 -->
		<input type="hidden" name="schdulId" />  
		<input type="hidden" name="schdulBgnde" />
		<input type="hidden" name="schdulEndde" />
		<input type="hidden" name="pageIndex" value="<c:out value="${deptSchdulManageVO.pageIndex}"/>"/>
		<input type="hidden" name="viewType" value="L">

		<!-- content_pop 시작 -->
		<div id="content_pop">
		
			<!-- 타이틀 시작 -->
			<div id="h2_topnav">
			<h1><strong>전체일정목록</strong></h1>
			</div>
			<!-- // 타이틀 끝 -->		
			 
		<!-- 월간/주간/일간 목록 네비게이션 -->
		<table summary="월간일정보기, 주간일정보기, 일간일정보기   입니다" >
		<tr> 
		
		  <!-- 2010.11.17 		
		  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu" name="tabMenu"><b><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageMonthList.do'/>" onClick="fnTabMenuSelect(0); return false;">월간일정보기</a></b></td>
		  <td height="20px" width="1x" bgcolor="#FFFFFF"></td>
		  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu" name="tabMenu"><b><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageWeekList.do'/>" onClick="fnTabMenuSelect(1); return false;">주간일정보기</a></b></td>
		  <td height="20px" width="1x" bgcolor="#FFFFFF" ></td>
		  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu" name="tabMenu"><b><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageDailyList.do'/>" onClick="fnTabMenuSelect(2); return false;">일간일정보기</a></b></td>
		  <td height="20px" width="1x" bgcolor="#FFFFFF"> </td>
		  -->
		  
		  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu1" ><b><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageMonthList.do'/>" onClick="fnTabMenuSelect(0); return false;">월간일정보기</a></b></td>
		  <td height="20px" width="1x" bgcolor="#FFFFFF"></td>
		  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu2" ><b><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageWeekList.do'/>" onClick="fnTabMenuSelect(1); return false;">주간일정보기</a></b></td>
		  <td height="20px" width="1x" bgcolor="#FFFFFF" ></td>
		  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu3" ><b><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageDailyList.do'/>" onClick="fnTabMenuSelect(2); return false;">일간일정보기</a></b></td>
		  <td height="20px" width="1x" bgcolor="#FFFFFF"> </td>
		  
		</tr>
		</table>

						<!-- 검색영역 -->
						<div class="search_area_submit">
						<ul>
							<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" /></li>
							<li style="margin:6px"><label for="searchSchdulSeCode">일정명 : </label></li>
							<li>
									<input name="searchKeyword" id="searchKeyword" value="<c:out value='${deptSchdulManageVO.searchKeyword}'/>" class="input01"  style="width:150px;"  title="searchKeyword"/>							
							</li>
							<li>
									<select name="searchSchdulSeCode" id="searchSchdulSeCode" Class="calendar_option" title="일정구분" onChange="fn_list();">
									  <option value=''>--일정구분--</option>
									  <c:forEach items="${schdulSeCode_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${deptSchdulManageVO.searchSchdulSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
									</select>							
							</li>
							<li>
								<ul>
								    <li class="submit_btn01_left"></li>								    
								    <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
								    <li class="submit_btn01_right"></li>
								</ul>	
							</li>
						</ul>
						</div>
						<!-- /검색영역 -->	
						
			<!-- 리스트 시작 -->
			<div id="result_table">
				<table summary="순서, 일정ID, 일정명, 일정구분, 중요도, 반복구분   목록입니다" class="table_style">
				<caption>전체일정 목록</caption>
					<colgroup>
						<col width="80">				
						<col>
						<!-- <col width="100"/> -->
						<col width="140">
						<col width="80">	
						<col width="80">
						<col width="80">	
					</colgroup>		
					<thead>  
					<tr>
						<th scope="col" align="center">순서</th>
						<!-- <th scope="col" align="center">일정ID</th> -->
						<th scope="col" align="center">일정명</th>
						<th scope="col" align="center">일시</th>
						<th scope="col" align="center">일정구분</th>
						<th scope="col" align="center">중요도</th>
						<th scope="col" align="center">반복구분</th>
					</tr>
					</thead>
					<tbody>
		    		<c:if test="${empty resultList}">
		    			<tr>    
		   		  			<td colspan="6">
			    			검색 결과가 없습니다.
		  	  				</td>
		    			</tr>
		    		</c:if>			
		    		<c:if test="${!empty resultList}">								
						<c:forEach var="result" items="${resultList}" varStatus="status">	
						<tr>
							<td align="center" class="listtd"><c:out value="${paginationInfo.totalRecordCount - (deptSchdulManageVO.pageIndex - 1) * deptSchdulManageVO.pageSize - status.count + 1}"/></td>
							<td scope="row" align="left" class="listtd">
								<a href="<c:url value='/cms/cmm/EgovOe1SchdulManageDetail.do'/>?schdulId=<c:out value="${result.schdulId}"/>" onClick="fn_select('<c:out value="${result.schdulId}"/>'); return false;" class="board_text_link"><c:out value="${result.schdulNm}"/></a>
							</td>
							<td align="center" class="listtd"><c:out value="${result.schdulBgnde}"/> - <c:out value="${result.schdulEndde}"/></td>
							<td align="center" class="listtd"><c:out value="${result.codeNmOfSchdulSe}"/></td>
							<td align="center" class="listtd"><c:out value="${result.codeNmOfSchdulIpcrCode}"/></td>
							<td align="center" class="listtd"><c:out value="${result.codeNmOfReptitSeCode}"/></td>
						</tr>			
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
			<!-- 리스트 끝 -->
			<!-- Pagination 끝-->
			<div id="pagenav_div">
				<ui:pagination paginationInfo = "${paginationInfo}"	 type="image"	 jsFunction="fn_egov_link_page"	   />
				<!--<form:hidden path="pageIndex" />-->
			</div>							
			<!-- Pagination 끝-->
			<!-- 버튼 시작 -->		
			<div class="subbtn_align">  
			    <ul>
					<li class="btn02_leftbg"></li>
					<li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageRegist.do'/>" onclick="fn_regist(); return false;" class="btn_link">등록</a></li>
					<li class="btn02_rightbg"></li>
			    </ul>
			</div>				
			<!-- 버튼 끝 -->
			
		</div>
		</form:form>
		<!-- form 끝 -->
		
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