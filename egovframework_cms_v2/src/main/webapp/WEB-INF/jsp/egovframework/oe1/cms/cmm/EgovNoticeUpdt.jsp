<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title><c:out value='${bdMstr.bbsNm}'/> - 게시글 수정</title>

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
	function fn_egov_validateForm(obj){
		return true;
	}

	function fn_egov_regist_notice(){
		oEditors.getById["nttCn"].exec("UPDATE_IR_FIELD", []);
		
		if (!validateBoard(document.board)){
			return;
		}
		
		if (confirm('수정 하시겠습니까?')) {
			document.board.action = "<c:url value='/cms/cmm${prefix}/updateBoardArticle.do'/>";
			document.board.submit();					
		}
	}	
	
	function fn_egov_select_noticeList() {
		document.board.action = "<c:url value='/cms/cmm${prefix}/selectBoardList.do'/>";
		document.board.submit();	
	}
	
	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";			
		} else {
			document.getElementById('file_upload_posbl').style.display = "none";
			document.getElementById('file_upload_imposbl').style.display = "block";
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

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="board" name="board" method="post" enctype="multipart/form-data" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="returnUrl" value="<c:url value='/cms/cmm/forUpdateBoardArticle.do'/>"/>

<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" />

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
	<h1><strong><c:out value='${bdMstr.bbsNm}'/> - 게시글 수정</strong></h1>
	</div>
	
	<div id="datail_table">
	<table summary="제목,내용,작성자,비밀번호,첨부파일,첨부파일목록  입니다.">
	<caption>게시글쓰기 테이블</caption>
	  <tr> 
	    <th scope="row" ><span class="th_add"><label for="nttSj">제목</label></span></th>
	    <td>
	      <input name="nttSj"  title="제목" type="text" size="80" value='<c:out value="${result.nttSj}" />'  maxlength="120" />

	       <br><form:errors path="nttSj" />
	    </td>
	  </tr>
	  <tr> 
	    <th scope="row" ><span class="th_add"><label for="nttCn">내용</label></span></th>
	    <td colspan="3">
		      <textarea name="nttCn" id="nttCn"  title="내용" style="width:680px; height:300px" rows="3" cols="60" > <c:out value="${result.nttCn}" escapeXml="false"/></textarea>
	    </td>
	  </tr>  
	  <c:if test="${anonymous == 'true'}">
		  <tr> 
		    <th scope="row" ><label for="ntcrNm">작성자</label></th>
		    <td colspan="3">
		      <input name="ntcrNm"  title="작성자"  type="text" size="20" value='<c:out value="${result.ntcrNm}" />'  maxlength="10" />
		    </td>
 
		  </tr>
		  <tr> 
		    <th scope="row" ><label for="password">비밀번호</label></th>
		    <td colspan="3">
		      <input name="password" type="password"  title="비밀번호"  size="20" value="" maxlength="20" />
		    </td>
		  </tr>
	  </c:if>
	  <c:if test="${not empty result.atchFileId}">

	  </c:if>	

	  	
	  <c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">

		  <tr> 
		    <th scope="row" ><label for="egovComFileUploader">첨부파일</label></th>
		    <td >

			  	<c:if test="${result.atchFileId == ''}">
			  		<input type="hidden" name="fileListCnt" value="0" />
			  	</c:if>

	            <div id="temp">
		      		<input name="fileField" id="egovComFileUploader"  title="첨부파일"  type="file" class="ser_box" />
				    <div id="egovComFileList"></div>
	   	        </div>		  
			</td>		    
		  </tr>

 		<tr> 
   		<th scope="row" >첨부파일목록</th>
   		<td>

		    <script type="text/javascript">
				var maxFileNum = document.board.posblAtchFileNumber.value;
			
				if(maxFileNum==null || maxFileNum==""){
				    maxFileNum = 3;
				}  
				var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
				multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
		     </script>

      		<c:if test="${not empty result.atchFileId}">
				<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
				  	<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
	  		</c:if>	
   		</td>
 		</tr>
	  </c:if>

 		 
	</table>   
	</div>
	
	<div class="subbtn_align">          
	<ul>
		<c:if test="${bdMstr.authFlag == 'Y'}">
			<c:if test="${result.frstRegisterId == searchVO.frstRegisterId}">
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_regist_notice(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>
			</c:if>
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