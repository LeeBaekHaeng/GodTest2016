<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.oe1.cms.com.service.EgovOe1Properties" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Description : 공지사항/자유게시판/자료실 목록 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.02  김정수         최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.02
  *  
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<meta http-equiv="Content-language" content="ko">
<title><c:out value="${brdMstrVO.bbsNm}"/></title>
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

<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sys/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">
<!--
	function press(event) {
	}

	function fn_egov_addNotice() {
	}
	
	function fn_egov_select_noticeList(pageNo) {
	}
	
	function fn_egov_inqire_notice(nttId, bbsId) {		
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
<!--
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_noticeList('1');
		}
	}

	function fn_egov_addNotice() {
		document.frm.action = "<c:url value='/cms/cmm${prefix}/addBoardArticle.do'/>";
		document.frm.submit();
	}
	
	function fn_egov_select_noticeList(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cms/cmm${prefix}/selectBoardList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_notice(nttId, bbsId, frstRegisterId, rdcnt) {
		document.frm.nttId.value = nttId;
		document.frm.bbsId.value = bbsId;
		document.frm.frstRegisterId.value = frstRegisterId;
		document.frm.rdcnt.value = rdcnt;
		document.frm.action = "<c:url value='/cms/cmm${prefix}/selectBoardArticle.do'/>";
		document.frm.submit();
	}			
//-->
</script>
</c:otherwise>
</c:choose>

</head>
<body onload="document.frm.searchWrd.focus();">
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

<div id="content"><!-- BODY 내용 START -->

<form name="frm" action ="" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="frstRegisterId"  value="<c:out value='${result.frstRegisterId}'/>" />
<c:if test="${result.rdcnt == null}">
<input type="hidden" name="rdcnt"  value="0" />
</c:if>
<c:if test="${result.rdcnt != null}">
<input type="hidden" name="rdcnt"  value="<c:out value='${result.rdcnt}'/>" />
</c:if>
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />

<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav">
	<h1><strong> <c:out value="${brdMstrVO.bbsNm}"/></strong></h1>
	</div>
	<!-- // 타이틀 -->

					<!-- 검색 시작 -->
					<div class="search_area_submit">
						<ul>
						<li style="margin:5px"><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색"/></li>
						<li>
							<select name="searchCnd" class="use" title="검색조건" >
								<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
						   		<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>			   
						   		<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
							</select>						
						</li>
						<li style="margin:5px">
					         <input name="searchWrd" class="input02" value='<c:out value="${boardVO.searchWrd}"/>' onkeypress="press(event);"  title="검색조건" />
						</li>
						<li>
							<ul>
							    <li class="submit_btn01_left"></li>
							    <li><input type="submit" value="검색" onclick="fn_egov_select_noticeList('1'); return false;" class="submit_btn01"></li>
							    <li class="submit_btn01_right"></li>
							</ul>					
						</li>
						</ul>
					</div>
					<!-- 검색 끝 -->

	<div id="result_table">
	<table summary="순번, 제목 또는   작성자, 작성일, 조회수   목록입니다" class="table_style">
	<caption>게시판 목록   검색 결과</caption>
		<colgroup>
			<col width="10%">				
			<col width="44%">
			<c:if test="${anonymous != 'true'}">
    			<col width="20%">
    		</c:if>
			<col width="15%">
			<col width="8%">
		</colgroup>	
 		<thead>
			  <tr> 
			    <th scope="col">순번</th>
			    <th scope="col">제목</th>
			   	<c:if test="${anonymous != 'true'}">
			    	<th>작성자</th>
			    </c:if>
			    <th scope="col">작성일</th>   
			    <th scope="col">조회수</th>         
			  </tr>
 		</thead>    
 		<tbody>
			 <c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td align="center"><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>		
			    <td scope="row" align="left" class="list_td_left">
			    	<c:choose>
			    		<c:when test="${result.useAt == 'N'}">
			    			<c:out value="${result.nttSj}" />
			    		</c:when>
			    		<c:otherwise>
			    		
			    			<!-- 2010.11.9 
							<a href="#Link" onclick="fn_egov_inqire_notice('<c:out value="${result.nttId}"/>','<c:out value="${result.bbsId}"/>', '<c:out value="${result.frstRegisterId}"/>', '<c:out value="${result.rdcnt}"/>'); return false;" class="board_text_link">
								<c:out value="${result.nttSj}"/>
							</a>
							-->
							
							<a href="<c:url value='/cms/cmm${prefix}/selectBoardArticle.do'/>" onclick="fn_egov_inqire_notice('<c:out value="${result.nttId}"/>','<c:out value="${result.bbsId}"/>', '<c:out value="${result.frstRegisterId}"/>', '<c:out value="${result.rdcnt}"/>'); return false;" class="board_text_link">
								<c:out value="${result.nttSj}"/>
							</a>
							
			    		</c:otherwise>
			    	</c:choose>
			    </td>
		    	<c:if test="${anonymous != 'true'}">
			    	<td align="center"><c:out value="${result.frstRegisterNm}"/></td>
			    </c:if>
			    <td align="center"><c:out value="${result.frstRegisterPnttm}"/></td>
			    <td align="center"><c:out value="${result.rdcnt}"/></td>
				
			  </tr>
			 </c:forEach>	  
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
		    	
		  			<c:choose>
			  			<c:when test="${anonymous == 'true'}">
			    			<td class="listCenter" colspan="4" >검색된 데이터가 없습니다.</td>
			    		</c:when>
			    		<c:otherwise>
			    			<td class="listCenter" colspan="5" >검색된 데이터가 없습니다.</td>
			    		</c:otherwise>
		    	    </c:choose>
		 	  </tr>		 
			 </c:if>  
 		</tbody>  
	</table>
	</div>
	
	<div id="pagenav_div"><ui:pagination
		paginationInfo="${paginationInfo}" type="image"
		jsFunction="fn_egov_select_noticeList" />
	</div>

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

	<!-- 버튼 시작-->
	<c:if test="${brdMstrVO.authFlag == 'Y'}">

	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fn_egov_addNotice(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
	
	</c:if>
	<!-- 버튼 끝 -->

	</div>
</form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>

<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->
		
</body>
</html>