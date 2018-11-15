<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
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
<title><c:out value='${bdMstr.bbsNm}'/> - 답글쓰기</title>

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

<script type="text/javascript" src="<c:url value='/editor/js/HuskyEZCreator.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sys/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/com/validator.do"/>"></script>
<validator:javascript formName="board" staticJavascript="false" xhtml="true" cdata="false"/>


<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">

	function fn_egov_validateForm(obj) {
		return true;
	}

	function fn_egov_regist_notice() {
		oEditors.getById["nttCn"].exec("UPDATE_IR_FIELD", []);
		
		if (!validateBoard(document.board)){
			return;
		}
		
		if (confirm('등록 하시겠습니까?')) {
			document.board.action = "<c:url value='/cms/cmm${prefix}/replyBoardArticle.do'/>";
			document.board.submit();					
		}
	}
	
	function fn_egov_select_noticeList() {
		document.board.action = "<c:url value='/cms/cmm${prefix}/selectBoardList.do'/>";
		document.board.submit();	
	}
</script>


</head>
<!-- body onload="javascript:editor_generate('nttCn');"-->
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

<form:form commandName="board" name="board" method="post" enctype="multipart/form-data" >
<input type="hidden" name="replyAt" value="Y" />
<input type="hidden" name="pageIndex"  value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="nttId" value="<c:out value='${searchVO.nttId}'/>" />
<input type="hidden" name="parnts" value="<c:out value='${searchVO.parnts}'/>" />
<input type="hidden" name="sortOrdr" value="<c:out value='${searchVO.sortOrdr}'/>" />
<input type="hidden" name="replyLc" value="<c:out value='${searchVO.replyLc}'/>" />

<input type="hidden" name="bbsId" value="<c:out value='${bdMstr.bbsId}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${bdMstr.bbsAttrbCode}'/>" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${bdMstr.bbsTyCode}'/>" />
<input type="hidden" name="replyPosblAt" value="<c:out value='${bdMstr.replyPosblAt}'/>" />
<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />
<input type="hidden" name="tmplatId" value="<c:out value='${bdMstr.tmplatId}'/>" />


<c:if test="${anonymous != 'true'}">
<input type="hidden" name="ntcrNm" value="dummy">	<!-- validator 처리를 위해 지정 -->
<input type="hidden" name="password" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

<div id="content_pop">
	
		<div id="h2_topnav">
			<h1><strong><c:out value='${bdMstr.bbsNm}'/> - 답글쓰기</strong></h1>
		</div>
	
		<div id="datail_table">
		<table summary="답글쓰기">
		<caption>답글쓰기 테이블</caption>
		
	  <tr> 
	    <th><span class="th_add">제목</span></th>
	    <td colspan="3">
	      <input name="nttSj" type="text" size="60" value="RE: <c:out value='${result.nttSj}'/>"  maxlength="60" title="제목" /> 
	      <br/><form:errors path="nttSj" />
	    </td>
	  </tr>
	  <tr> 
	    <th><span class="th_add">내용</span></th>
	    <td colspan="3">
	      <textarea name="nttCn" id="nttCn"  style="width:680px; height:300px" rows="3" cols="60" title="내용"></textarea> 
	      <form:errors path="nttCn" />
	    </td>
	  </tr> 
	  <c:if test="${anonymous == 'true'}">
		  <tr> 
		    <th>작성자</th>
		    <td colspan="3">
		      <input name="ntcrNm" type="text" size="20" value=""  maxlength="10"  title="작성자" />
		      <br/><form:errors path="ntcrNm" />	
		    </td>
		  </tr>
		  <tr> 
		    <th>비밀번호</th>
		    <td colspan="3">
		      <input name="password" type="password" size="20" value="" maxlength="20"  title="비밀번호" />
		    </td>
		  </tr>
	  </c:if>
	  <tr>
	    <th height="23">파일첨부</th>
	    <td colspan="3">
	  		<c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">

             <div id="temp">
             	<input name="file_1" id="egovComFileUploader" type="file"   title="파일첨부" />
			    <div id="egovComFileList"></div>
			 </div>
			
		     <script type="text/javascript">
		     var maxFileNum = document.board.posblAtchFileNumber.value;
		     if (maxFileNum==null || maxFileNum=="") {
		    	 maxFileNum = 3;
		     }
			 var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
			 multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );			
		     </script>	  
			</c:if>
			
	    </td>
	  </tr>
	  
	</table>
	</div>
	
	<div class="subbtn_align">          
	<ul>
		<c:if test="${bdMstr.authFlag == 'Y'}">
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_regist_notice(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		</c:if>

	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_select_noticeList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>

</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "nttCn",
	sSkinURI: "<c:url value='/editor/SEditorSkin.html'/>",
	fCreator: "createSEditorInIFrame"
});

</script>

</body>
</html>