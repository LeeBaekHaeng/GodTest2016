<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @JSP Name    : EgovChangeRequestUpdt.jsp
  * @Description : 변경요청서 수정 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.11  김영심          최초 생성
  *
  * author 운영환경 1팀 
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>변경요청서 수정</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

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
<script type="text/javascript" src="<c:url value="/com/validator.do"/>"></script>
<validator:javascript formName="egovOe1ChangeRequestVO" staticJavascript="false" xhtml="true" cdata="true"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*******************************************************
/* 첨부파일 처리  
*******************************************************/
function fn_egov_onload(){

	var maxFileNum = document.chgUpdaterForm.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}

/*******************************************************
/* 변경요청서내용 수정처리 요청 
*******************************************************/
function fn_egov_save() {

	if(validateEgovOe1ChangeRequestVO(document.chgUpdaterForm)){

		var now = new Date();
	    var year= now.getFullYear();
	    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		var toDay = year + "-" + mon + "-" + day;
		
		if (toDay > document.chgUpdaterForm.comptRequstDe.value) {
			alert("완료요청일은 현재일 이후로 입력가능합니다.");
			return false;
		}
		
		if(confirm('<spring:message code="common.save.msg" />')){
        	document.chgUpdaterForm.action = "<c:url value='/cms/sim/gnrl/changeRequestUpdate.do'/>";
    		document.chgUpdaterForm.submit();		
		}
	}
}

/*******************************************************
/* 변경요청목록 화면 요청 
*******************************************************/
function fn_egov_selectList() {
	document.chgUpdaterForm.action = "<c:url value='/cms/sim/gnrl/getChangeRequestList.do'/>";
	document.chgUpdaterForm.submit();
}

/*********************************************************
* 타 시스템에서 변경으로 이관된 경우, 근거정보 조회
******************************************************** */
function fn_egov_select_change_request_origin(requstSysBasisId) {
	window.open('<c:url value='/'/>cms/srm/gnrl/selectOperImprovReqPop.do?'
	    	+ '&selectedId='
	    	+ requstSysBasisId
	   		,'운영개선요청상세'
	   		,'left=100,top=100,width=800,height=500,menubar=no,toolbar=no,location=no,status=no,resizable=yes,scrollbars=1');
}

//-->
</script>

</head>

<body onload="javascript:fn_egov_onload();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap"><!-- header 시작 -->
<a href="#top_menu" class="hide_a"> </a>
<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
<!-- //header 끝 --> <!-- 메인 시작 -->
<div id="container"><!-- 좌메뉴 시작 -->
<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->
<form:form commandName="changeRequestvo" name="chgUpdaterForm"  enctype="multipart/form-data" action="/oe1/cms/sim/gnrl/changeRequestUpdate.do" method="post"  onsubmit="javascript:fn_egov_save(); return false;">
<input type="hidden" name="posblAtchFileNumber" value="10" />
<input type="hidden" name="returnUrl" value="/oe1/cms/sim/gnrl/changeRequestUpdtView.do" />
<form:hidden  path="changeRequstId" />
<c:if test="${changeRequestvo.atchFileId == ''}">
<input type="hidden" name="fileListCnt" value="0" />
</c:if>

<div id="content_pop">
<!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 변경요청서 수정</strong></h1></div>
<!-- // 타이틀 -->
	<div id="datail_table2">
	<table summary="변경요청서 수정 테이블"> 
	<caption>변경요청서수정</caption>	
  		<tr>
    		<th style="width:15%" scope="row"><label for="changeRequstNm"><span class="th_add" >변경요청명</span></label></th>
    		<td  colspan="3">
    		<form:input path="changeRequstNm"  id="changeRequstNm" cssClass="inputsmall01" cssStyle="width:400px" maxlength="60" tabindex="1" title="변경요청명입력"/>
    		</td>
  		</tr>
  		<tr>
    		<th scope="row"><label for="operJobSeCode" class="th_add" >업무구분</label></th>
    		<td>
      		<select name="operJobSeCode" id="operJobSeCode" style="width:130px" tabindex="2" title="업무구분선택">
          	<option value='' >--선택하세요--</option>
          	<c:forEach var="codeinfo" items="${operJobSeCodeList}" varStatus="status">
             	<option value='${codeinfo.code}' <c:if test="${changeRequestvo.operJobSeCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>
			</select>
    		</td>
    		<th  scope="row"><label for="emrgncyRequstAt" class="th_add" >긴급여부</label></th>  
    		<td>
      		<select name="emrgncyRequstAt" id="emrgncyRequstAt" style="width:130px" tabindex="3" title="긴급여부선택">
          	<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${emrgncyRequstAtList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeRequestvo.emrgncyRequstAt == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
    	</tr>	
  		<tr>
    		<th  scope="row"><label for="changeRequstResnCode" class="th_add" >요청사유분류</label></th>
    		<td>
    		<select name="changeRequstResnCode" id="changeRequstResnCode" style="width:130px" tabindex="4" title="요청사유분류선택">
      		<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${changeRequstResnCodeList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeRequestvo.changeRequstResnCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
    		<th  scope="row"><label for="comptRequstDe" class="th_add" >완료요청일</label></th>
    		<td>
			<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
			<form:input path="comptRequstDe"   id="comptRequstDe" cssClass="inputsmall01" cssStyle="width:80px;"  maxlength="10" tabindex="5" title="완료요청일입력"/>
			<img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력팝업" title="새창" width="16" height="16" onclick="javascript:fn_egov_NormalCalendar(document.chgUpdaterForm, document.chgUpdaterForm.comptRequstDe);" />
			</td>
	  	</tr>

  		<c:if test="${fn:length(changeRequestvo.requstSysBasisId) > 0 }">
  		<tr>
    		<th  scope="row">요청시스템</th>
    		<td ><c:out value="${changeRequestvo.changeRequstSysCode}"/></td>
    		<th  scope="row">시스템근거ID</th>
    		<td >
      			<c:out value="${changeRequestvo.requstSysBasisId}"/>
      			&nbsp;
        		<a href="#LINK" onclick="fn_egov_select_change_request_origin('${changeRequestvo.requstSysBasisId}'); return false;" class ="board_text_link"  target="_blank" title="새창">[요청근거]</a>
    		</td>
  		</tr>
  		</c:if>
  		<c:if test="${fn:length(changeRequestvo.requstSysBasisId) <= 0 }">
  		<tr>
    		<th  scope="row">요청시스템</th>
		    <td >해당없음</td>
    		<th  scope="row" >시스템근거ID</th>
    		<td >해당없음</td>
  		</tr>
  		</c:if>
  		<tr>
    		<th scope="row"> <label for="changeRequstCn"><span class="th_add" >요청내용</span></label></th>
    		<td  colspan="3">
      		<form:textarea path="changeRequstCn"  id="changeRequstCn" rows="5" cssStyle="width:500px;" cols="10" cssClass="textarea" tabindex="6" title="요청내용입력"/></td>
  		</tr>
  		<tr>
	    	<th  scope="row">변경요청일</th>
    		<td><c:out value="${changeRequestvo.changeRequstDe}"/></td>
	    	<th scope="row">변경요청자</th>
    		<td><c:out value="${changeRequestvo.changeRqesterNm}"/></td>
  		</tr>

  		<tr>
    		<th  id="file_upload_posbl"  scope="row"><label for="egovComFileUploader">첨부파일</label></th>
    		<td colspan="3" >
      		<input type="file" name="fileField" id="egovComFileUploader" class="ser_box" tabindex="7" title="첨부파일" onkeydown="event.returnValue=false;"/>
    		</td>
  		</tr>
  		<tr> 
    		<th rowspan="2" scope="row">첨부파일목록</th>
    		<td colspan="3">
      		<c:if test="${not empty changeRequestvo.atchFileId}">
			<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
		  	<c:param name="param_atchFileId" value="${changeRequestvo.atchFileId}" />
			</c:import>
	  		</c:if>	
    		</td>
  		</tr> 
  		<tr>
			<td  colspan="3"><div id="egovComFileList"></div></td>
  		</tr>
  		
	</table>
	</div>
	<div class="subbtn_align">  
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" tabindex="8"/></span></li>
		    <li class="submit_btn01_right"></li>
	    	<li class="btn02_leftbg"></li>
	    	<li class="btn02_middlebg"><a href="<c:url value='/cms/sim/gnrl/getChangeRequestList.do'/>" onclick="fn_egov_selectList(); return false;" class="btn_link"  tabindex="9">목록</a></li>
	    	<li class="btn02_rightbg"></li>
		</ul> 
	</div>
	
</div>
<!-- search조건유지 -->
  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
  <input name="searchOperJobSeCode" type="hidden" value="<c:out value='${searchVO.searchOperJobSeCode}'/>"/>
  <input name="searchEmrgncyRequstAt" type="hidden" value="<c:out value='${searchVO.searchEmrgncyRequstAt}'/>"/>
  <input name="searchChangeProcessSttusCode" type="hidden" value="<c:out value='${searchVO.searchChangeProcessSttusCode}'/>"/>
  <input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
  <input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
</form:form>
</div>

<!-- BODY 내용 END -->

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->
</body>
</html>