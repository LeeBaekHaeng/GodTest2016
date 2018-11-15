<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @JSP Name : EgovOperImprovReqUpdt.jsp
  * @Description : 운영개선요청 상세 수정 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.20  박수림         최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.20
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */ 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>운영개선요청</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" language="javascript" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />" ></script>

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
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="updateOIRForm" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fn_egov_onload(){
	var maxFileNum = document.updateOIRForm.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}
	
/* 목록 화면 function */
function fn_egov_selectList() {
   	document.updateOIRForm.action = "<c:url value='/cms/srm/gnrl/selectOperImprovReqList.do'/>";
   	document.updateOIRForm.submit();		
}

/* 저장 function */
function fn_egov_save() {	
	frm = document.updateOIRForm;
	var day = new Date();
	var year= day.getYear();
	var mon = day.getMonth()+1; 
	var date= day.getDate(); 
	if((mon+"").length < 2){
		mon = "0" + mon;
	}
	if((date+"").length < 2){
		date = "0" + date;
	}
	var today = year +"-"+ mon +"-"+ date;	
	if(frm.requstAtchFileId.value!=""){
		frm.requstAtchFileId.value = frm.atchFileId.value;
	}

	if(!validateUpdateOIRForm(frm)){
        return;
    }else{
	    if(frm.comptRequstDe.value < today){
		    alert("완료요청일은 현재일 이후로 입력가능합니다.");
		    return false;
	    }        
        if(confirm('<spring:message code="common.save.msg" />')){
        	frm.action = "<c:url value='/cms/srm/gnrl/updateOperImprovReq.do' />";
        	frm.submit();
        }
    }
}

-->
</script>
</head>
<body onload="javascript:fn_egov_onload();">
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

<form:form commandName="vo" name="updateOIRForm" enctype="multipart/form-data" method="post" action="oe1/cms/srm/gnrl/selectOperImprovReqList.do" >
<input type="hidden" name="operImprvmRequstId"  value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="requstAtchFileId"    value="<c:out value='${vo.requstAtchFileId }'/>"/>
<input type="hidden" name="lastUpdusrId"        value="<c:out value='${s_mberId}'/>"/>
<input type="hidden" name="returnUrl"           value="/oe1/cms/srm/gnrl/updateOperImprovReqView.do" />
<input type="hidden" name="fileListCnt"         value="0" />
<input type="hidden" name="posblAtchFileNumber" value="10" />

<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 운영개선요청 수정</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	<table summary="개선요청명,업무구분,완료요청일,요청내용,첨부파일 수정테이블 입니다.">
	<caption>운영개선요청 수정 테이블</caption>	
		<tr>
			<th scope="row" class="tbtd_caption"><span class="th_add"><label for="operImprvmRequstSj">개선요청명</label></span></th>
			<td class="tbtd_content" colspan='3'>
				<form:input path="operImprvmRequstSj" id="operImprvmRequstSj" maxlength="30" cssClass="inputsmall" title="제목" tabindex="1"/>
				&nbsp;<form:errors path="operImprvmRequstSj" />
			</td>
		</tr>
		<tr>
			<th scope="row">요청자</th>
			<td><c:out value="${vo.frstRegisterNm}"/></td>
			<th scope="row" width="20%">요청일</th>
			<td><c:out value="${vo.frstRegisterPnttm}"/></td>
		</tr>
		<tr>
			<th scope="row" width="20%"><span class="th_add"><label for="operJobSeCode">업무구분</label></span></th>
			<td>
				<form:select path="operJobSeCode" id="operJobSeCode" cssClass="use" cssStyle="width:100px;" title="업무" tabindex="2">
				<form:option value="" label="--선택하세요--"   />
	           	<c:forEach var="codeinfo" items="${operJobSeCode}" varStatus="status">
				<form:option value="${codeinfo.code}" label="${codeinfo.codeNm}"   />
			  	</c:forEach>  
	          	</form:select>	          	
			</td>
			<th scope="row" width="30%"><span class="th_add"><label for="comptRequstDe">완료요청일</label></span></th>
			<td>
				<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
				<form:input path="comptRequstDe" id="comptRequstDe" maxlength="10" cssClass="inputsmall01" cssStyle="width:80px"  title="완료요청일" tabindex="3"/>
	            <img src="<c:url value='/'/>images/egovframework/oe1/cms/com/bu_icon_carlendar.gif" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.updateOIRForm, document.updateOIRForm.comptRequstDe); return false;" />							
			</td>
		</tr>
		<tr>
			<th scope="row" class="tbtd_caption"><span class="th_add"><label for="operImprvmRequstCn">요청내용</label></span></th>
			<td class="tbtd_content" colspan='3'>
				<form:textarea path="operImprvmRequstCn" id="operImprvmRequstCn" rows="10" cols="70"  cssClass="textarea" title="내용" tabindex="4" />
				&nbsp;<form:errors path="operImprvmRequstCn" />
			</td>
		</tr>

  		<tr>
    		<th scope="row" id="file_upload_posbl" ><label for="egovComFileUploader">첨부파일</label></th>
    		<td colspan="3" >
      		<input type="file" name="fileField" id="egovComFileUploader" class="ser_box" />
    		</td>
  		</tr>
  		<tr> 
    		<th scope="row" rowspan="2">첨부파일목록</th>
    		<td colspan="3">
      		<c:if test="${vo.requstAtchFileId != '' && not empty vo.requstAtchFileId }">
			<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
		  	<c:param name="param_atchFileId" value="${vo.requstAtchFileId}" />
			</c:import>
	  		</c:if>	
    		</td>
  		</tr> 
  		<tr>
			<td colspan="3"><div id="egovComFileList"></div></td>
  		</tr>  		
  						
	</table>
  </div>
  <div class="subbtn_align">  		
	  	<ul>	  	
 		<li class="btn02_leftbg"></li>
	    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_save(); return false;" class="btn_link">저장</a></li>
		<li class="btn02_rightbg"></li>	  	
		<li class="submit_btn01_left"></li>
		<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input" onclick="fn_egov_selectList(); return false;" /></span></li>
		<li class="submit_btn01_right"></li>
  		</ul>
  </div>  

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition"    value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword"      value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="srequstSttusCode"   value="<c:out value='${searchVO.srequstSttusCode}'/>"/>
<input type="hidden" name="soperJobSeCode"     value="<c:out value='${searchVO.soperJobSeCode}'/>"/>
<input type="hidden" name="pageIndex"          value="<c:out value='${searchVO.pageIndex}'/>"/>
</div>
</form:form>
<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

