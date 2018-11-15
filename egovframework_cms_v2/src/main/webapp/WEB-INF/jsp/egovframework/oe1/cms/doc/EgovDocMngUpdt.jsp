<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovDocMngUpdt.jsp
  * @Description : 문서이력관리 수정 화면
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
<title>문서이력관리 수정</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sys/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="docmngForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.docmngForm.action = "<c:url value='/cms/doc/selectDocMngList.do'/>";
   	document.docmngForm.submit();		
}

/* 글 등록 function */
function fn_egov_save() {	

	frm = document.docmngForm;
	
	if(!validateDocmngForm(frm)){
        return;
    }else{    	
    	if(confirm("저장하시겠습니까?")){
    		document.docmngForm.action = "<c:url value='/cms/doc/updateDocMngOK.do'/>";
    		document.docmngForm.submit();
    	}	
    }
    	
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

function category(form){
	document.docmngForm.action = "<c:url value='/cms/doc/updateDocMng.do'/>";
	document.docmngForm.submit();
}

-->
</script>
</head>
<BODY onLoad="setTimeout('a()', 500);">

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
	<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
	<!-- //header 끝 --> 
	
	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
		<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
		<!-- 좌메뉴 끝 -->

		<div id="content"><!-- BODY 내용 START -->
		
			<form:form commandName="egovOe1DocMngVO" name="docmngForm"  method="post" enctype="multipart/form-data" >
			<input type="hidden" name="returnUrl" 			value="/oe1/cms/doc/updateDocMng.do" />
			<input type="hidden" name="selectedId" 			value="<c:out value='${egovOe1DocMngVO.documentId}'/>" />
			<input type="hidden" name="docNo" 				value="<c:out value='${egovOe1DocMngVO.docNo}'/>" />
			<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${egovOe1DocMngVO.posblAtchFileNumber}'/>" />			
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>문서이력관리 수정</strong></h1></div>
				<!-- // 타이틀 -->
				<div id="datail_table">
				<table summary="산출단계,문서종류,문서명,문서 설명,첨부파일 입니다" >
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
				<tr>
					<th scope="row"><span class="th_add"><label for="procsStepDv">산출단계</label></span></th>
					<td>
						<select name="procsStepDv" id="procsStepDv" tabindex="1" class="opselect_smaill01" title="산출단계"  onChange="category(this);"  disabled="disabled">
						  <option value=''>--산출단계--</option>
						  <c:forEach items="${procsStepDv_result}" var="codeInfo" varStatus="status">
						  <option value='${codeInfo.code}' <c:if test="${searchMode.procsStepDv == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
						  </c:forEach>
						</select>					
					</td>
				</tr>	
				<tr>
					<th scope="row"><span class="th_add"><label for="documentSe">문서종류</label></span></th>
					<td>
						<select name="documentSe" id="documentSe" tabindex="2" class="opselect_smaill01" title="문서종류" disabled="disabled">
						  <option value=''>--문서종류--</option>
						  <c:forEach items="${documentSe_result}" var="codeInfo" varStatus="status">
						  <option value='${codeInfo.documentSe}' <c:if test="${egovOe1DocMngVO.documentSe == codeInfo.documentSe}">selected="selected"</c:if>>${codeInfo.documentSeName}</option>
						  </c:forEach>
						</select>					
					</td>
				</tr>								
				<tr>
					<th scope="row"><span class="th_add"><label for="documentNm">문서명</label></span></th>
					<td>
						<form:input path="documentNm" maxlength="60" tabindex="3" cssClass="opselect_smaill01" title="문서명" readonly="true"/>
						&nbsp;<form:errors path="documentNm" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="documentDc">문서 설명</label></th>
					<td>
					<form:textarea path="documentDc" rows="5" cols="50" tabindex="4" cssClass="textareasmall01" title="문서설명"/>
					</td>
				</tr>	
				<tr>
					<th scope="row"><span class="th_add"><label for="chghy">변경사유</label></span></th>
					<td>
					<form:textarea path="chghy" rows="5" cols="50" tabindex="4" cssClass="textareasmall01" title="변경사유"/>
					</td>
				</tr>					  
				  <c:if test="${not empty egovOe1DocMngVO.atchFileId}">
					  <tr> 
					    <th scope="row"><label for="param_atchFileId">첨부파일목록</label></th>
					    <td>
							<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${egovOe1DocMngVO.atchFileId}" />
							</c:import>
					    </td>
					  </tr>
				  </c:if>
				  
				  	<!-- 2010.11.17 	
				  	<c:if test="${empty egovOe1DocMngVO.atchFileId}">
				  		<input type="hidden" name="fileListCnt" value="0">
				  	</c:if>
				  	-->
				  	
					  <tr> 
					    <th scope="row"><label for="file_upload_posbl">첨부파일</label></th>
					    <td colspan="3">
					    
					  	<c:if test="${empty egovOe1DocMngVO.atchFileId}">
					  		<input type="hidden" name="fileListCnt" value="0">
					  	</c:if>
					    
					    <div id="file_upload_posbl"  style="display:none;" >	
			            	<div id="temp">
						        <input name="file_1" id="egovComFileUploader" type="file" title="첨부파일"/>
						       	<div id="egovComFileList"></div>
						     </div>	  
						</div>
						<div id="file_upload_imposbl"  style="display:none;" >
							파일업로드불가				
						</div>
						</td>		    
					  </tr>
				</table>
			  	</div>
				<div class="subbtn_align"> 
				    <ul>
				        <li class="btn02_leftbg"></li>
				        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input" onclick="fn_egov_save(); return false;" /></span></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/doc/selectDicMngList.do'/>" onclick="fn_egov_selectList(); return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>
				    </ul>
				</div>									
			</div>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchCondition" 	value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" 		value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" 			value="<c:out value='${searchMode.pageIndex}'/>"/>
			<input type="hidden" name="searchProcsStepDv" 	value="<c:out value='${searchMode.searchProcsStepDv}'/>"/>
			<input type="hidden" name="searchDocumentSe" 	value="<c:out value='${searchMode.searchDocumentSe}'/>"/>				
			</form:form>
						
		</div>
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	</div>
	<!-- 메인 끝 -->
</div>
<!-- //전체 DIV끝 -->
<script type="text/javascript">
	function a(){
		var existFileNum = "";		
		if("<c:out value='${egovOe1DocMngVO.atchFileId}'/>"==""){
			existFileNum = document.docmngForm.fileListCnt.value;	
		}	    
		var maxFileNum = document.docmngForm.posblAtchFileNumber.value;

		if (existFileNum=="undefined" || existFileNum ==null) {
			existFileNum = 0;
		}
		if (maxFileNum=="undefined" || maxFileNum ==null) {
			maxFileNum = 0;
		}		
		var uploadableFileNum = maxFileNum - existFileNum;
		if (uploadableFileNum<0) {
			uploadableFileNum = 0;
		}				
		if (uploadableFileNum != 0) {
			fn_egov_check_file('Y');
			var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
			multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
		} else {
			fn_egov_check_file('N');
		}
	}

</script>
</body>
</html>

