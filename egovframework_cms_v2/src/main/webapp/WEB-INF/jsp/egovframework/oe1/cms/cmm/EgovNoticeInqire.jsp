<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovOe1CrdnRegist.jsp
  * @Description : 유관기관 등록 화면
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
<title><c:out value='${result.bbsNm}'/> - 글조회</title>

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

<script type="text/javascript" src="<c:url value='/js/egovframework/cop/bbs/EgovBBSMng.js' />"></script>
<script type="text/javascript">
	function onloading() {
		if ("<c:out value='${msg}'/>" != "") {
			alert("<c:out value='${msg}'/>");
		}
	}
	
	function fn_egov_select_noticeList(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cms/cmm/selectBoardList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_delete_notice() {
		
		if(confirm('<spring:message code="common.delete.msg" />')) {
			document.frm.action = "<c:url value='/cms/cmm/deleteBoardArticle.do'/>";
			document.frm.submit();
		}	
	}
	
	function fn_egov_moveUpdt_notice() {
		if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
			alert('등록시 사용한 패스워드를 입력해 주세요.');
			document.frm.password.focus();
			return;
		}

		document.frm.action = "<c:url value='/cms/cmm/forUpdateBoardArticle.do'/>";
		document.frm.submit();			
	}

	function fn_egov_addReply() {
		document.frm.action = "<c:url value='/cms/cmm${prefix}/addReplyBoardArticle.do'/>";
		document.frm.submit();			
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

<div id="content"><!-- BODY 내용 START -->
<form name="frm" method="post" action="">
<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" />
<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" />
<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" />
<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" />
<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" />


<div id="content_pop"><!-- 타이틀 -->
	<div id="h2_topnav">
	<h1><strong> <c:out value='${result.bbsNm}'/> - 글조회</strong></h1>
	</div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	  <table summary="글조회테이블">
		<caption>글조회테이블</caption>
	  <tr> 
	    <th scope="row">제목</th>
	    <td colspan="5"><c:out value="${result.nttSj}" /></td>
	  </tr>
	  <tr> 
	    <th scope="row">작성자</th>
	    <td>
	    <c:choose>
	    	<c:when test="${anonymous == 'true'}">
	    		******
	    	</c:when>
	    	<c:otherwise>
	    		<c:out value="${result.frstRegisterNm}" />
	    	</c:otherwise>
	    </c:choose>

	    </td>
	    <th scope="row">작성일</th>
	    <td><c:out value="${result.frstRegisterPnttm}" /></td>
	    <th scope="row">조회수</th>
	    <td><c:out value="${result.rdcnt}" /></td>
	  </tr>    
	  <tr> 
	    <th scope="row">글내용</th>
	    <td colspan="5">
				<c:out value="${result.nttCn}" escapeXml="false" />	
	    </td>
	  </tr>
	  <c:if test="${not empty result.atchFileId}">
		<tr>
			<th>첨부파일</th>
			<td colspan="5">
			    <div id="file">
		  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
		    			<c:param name="param_atchFileId" value="${result.atchFileId}" />
		    		</c:import> 
				</div>
			</td>
		</tr>
	  </c:if>   
	</table>
	</div>
	
	<div class="subbtn_align">          
	<ul>
		<c:if test="${result.frstRegisterId == sessionUniqId}">
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="fn_egov_moveUpdt_notice(); return false;" /></span></li>
			<li class="submit_btn01_right"></li>
	
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fn_egov_delete_notice(); return false;" /></span></li>
			<li class="submit_btn01_right"></li>
		</c:if>
		
		<c:if test="${result.replyPosblAt == 'Y'}">
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="답글작성" class="submit_btn_input"  onclick="fn_egov_addReply(); return false;" /></span></li>
			<li class="submit_btn01_right"></li>
		</c:if>     

		<li class="submit_btn01_left"></li>
		<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_select_noticeList('1'); return false;" /></span></li>
		<li class="submit_btn01_right"></li>
	</ul>
	</div>

	</div>
	
</form>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 -->
</div>
<!-- //전체 DIV끝 -->

</body>
</html>