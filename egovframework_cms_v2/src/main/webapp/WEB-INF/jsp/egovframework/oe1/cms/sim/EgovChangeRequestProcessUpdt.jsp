<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @JSP Name    : EgovChangeRequestProcessUpdt.jsp
  * @Description : 변경요청처리 수정
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.19  김영심          최초 생성
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
<title>변경요청처리 수정</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

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
<validator:javascript formName="egovOe1ChangeRequestProcessVO" staticJavascript="false" xhtml="true" cdata="true"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*********************************************************/
/* 변경요청처리내용 수정 요청 
/*********************************************************/
function fn_egov_save() {

	if(validateEgovOe1ChangeRequestProcessVO(document.chgProcUpdaterForm)){

		//CCB승인여부 Y를 체크한 경우, 승인일 / 승인검토내용입력여부 확인
		if (document.chgProcUpdaterForm.changeConfmAt[0].checked == true) {
							
			if (document.chgProcUpdaterForm.changeConfmDe.value==""){
				alert("CCB승인건입니다. CCB승인일을 입력하십시오");
				document.chgProcUpdaterForm.changeConfmDe.focus();
				return false;
			}
			if (document.chgProcUpdaterForm.changeConfmDe.value > document.chgProcUpdaterForm.changeRceptDe.value){
				alert("CCB승인일이 변경접수일 이후입니다.. CCB승인일을 확인하십시오");
				document.chgProcUpdaterForm.changeConfmDe.focus();
				return false;
			}	   
			if (document.chgProcUpdaterForm.changeConfmDe.value < document.chgProcUpdaterForm.changeRequstDe.value){
				alert("CCB승인일이  변경요청일 이전입니다.. CCB승인일을 확인하십시오");
				document.chgProcUpdaterForm.changeConfmDe.focus();
				return false;
			}	   
						
			if (document.chgProcUpdaterForm.changeExmntOpinion.value=="" ){
				alert("CCB승인건입니다. CCB검토의견을 입력하십시오");
				document.chgProcUpdaterForm.changeExmntOpinion.focus();
				return false;
			}
			
			if (document.chgProcUpdaterForm.changeExmntOpinion.value.length > 200){
				alert("CCB검토의견은 200자 이상 입력할 수 없습니다");
				document.chgProcUpdaterForm.changeExmntOpinion.focus();
				return false;
			}
			
		}
			
		if(confirm('<spring:message code="common.save.msg" />')){
        	document.chgProcUpdaterForm.action = "<c:url value='/cms/sim/admin/changeProcUpdate.do'/>";
    		document.chgProcUpdaterForm.submit();		
		}
	}
}

/*********************************************************/
/* 변경요청처리 목록화면 요청 */
/*********************************************************/ 
function fn_egov_changeProcList() {
	document.chgProcUpdaterForm.action = "<c:url value='/cms/sim/admin/changeProcList.do'/>";
	document.chgProcUpdaterForm.submit();			
}

/*********************************************************
* 변경요청명 클릭 시, 변경요청서상세팝업 호출
******************************************************** */
function fn_egov_select_change_request_popup(requestId) {
	window.open('<c:url value='/'/>cms/sim/gnrl/changeRequestDetailPopup.do?'
	    	+ 'changeRequestID=' 
	    	+ requestId
	   		,'변경요청서상세'
	   		,'left=100,top=100,width=800,height=500,menubar=no,toolbar=no,location=no,status=no,resizable=yes,scrollbars=1');
}

/*********************************************************
* 권한별 담당자정보 팝업조회(변경작업담당자권한=?)
******************************************************** */
function fn_egov_AuthorUser(authorCode) {

	window.open('<c:url value='/'/>cms/com/EgovOe1AuthorUserPopup.do?'
	    	+ 'authorCode=' 
	    	+ authorCode
	   		,'권한담당자조회'
	   		,'left=200,top=200,width=800,height=500,menubar=no,toolbar=no,location=no,status=no,resizable=no,scrollbars=yes');
}

function fn_egov_AuthorUser_Callback(mberId,uniqId, mberNm) {

	document.chgProcUpdaterForm.changeOpertorId.value = mberId;
	document.chgProcUpdaterForm.uniqId.value = uniqId;
	document.chgProcUpdaterForm.changeOpertorNm.value = mberNm;
}

/*********************************************************
* CCB승인여부= 'Y'인 경우에만 CCB관련정보 입력가능 
******************************************************** */
function fn_changeConfmAt_proc(){

	if (document.chgProcUpdaterForm.changeConfmAt[0].checked == true) {
		document.chgProcUpdaterForm.changeExmntOpinion.readOnly=false;
		document.chgProcUpdaterForm.changeConfmDe.readOnly=false;
		document.chgProcUpdaterForm.calImg.disabled=false;
	} else if (document.chgProcUpdaterForm.changeConfmAt[1].checked == true){
		document.chgProcUpdaterForm.changeExmntOpinion.readOnly=true;
		document.chgProcUpdaterForm.changeConfmDe.readOnly=true;
		document.chgProcUpdaterForm.calImg.disabled=true;
		document.chgProcUpdaterForm.changeExmntOpinion.value = "";
		document.chgProcUpdaterForm.changeConfmDe.value = "";
	}
}
//-->
</script>

</head>

<body onload="javascript:fn_changeConfmAt_proc();">
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
<form:form commandName="changeProcessvo" name="chgProcUpdaterForm" action="/oe1/cms/sim/admin/changeProcList.do" method="post" onsubmit="javascript:fn_egov_changeProcList(); return false;">
<form:hidden  path="changeRequstProcessId" />
<input type="hidden" name="changeRequstId" value="<c:out value='${changeRequestvo.changeRequstId}'/>" />
<input type="hidden" name="authorCode" value="ROLE_OPER_CHARGER" />
<input type="hidden" name="changeRceptDe" value="<c:out value='${changeProcessvo.changeRceptDe}'/>" />
<input type="hidden" name="changeRequstDe" value="<c:out value='${changeRequestvo.changeRequstDe}'/>" />

<div id="content_pop">
<!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 변경요청처리정보 수정</strong></h1></div>
<!-- // 타이틀 -->
	<div id="datail_table2">
	<table  summary="변경요청처리정보 수정 ">
	<caption>수정테이블</caption>		
	 
	  	<tr>
    		<th style="width:15%" scope="row"> 변경요청명</th>
    		<td colspan="3">
    		<c:out value="${changeRequestvo.changeRequstNm}"/>
    		</td>
  		</tr>
		<tr>
	    	<th scope="row">업무구분</th>
		    <td ><c:out value="${changeRequestvo.operJobSeCode}"/></td>
    		<th style="width:15%" scope="row">긴급여부</th>
    		<td ><c:out value="${changeRequestvo.emrgncyRequstAt}"/></td>
		</tr>
  		<tr>
    		<th scope="row">요청사유분류</th>
    		<td ><c:out value="${changeRequestvo.changeRequstResnCode}"/></td>
	    	<th scope="row">변경요청일</th>
    		<td><c:out value="${changeRequestvo.changeRequstDe}"/></td>
  		</tr>
  		<tr>
  			<th scope="row">처리상태</th>
    		<td ><c:out value="${changeProcessvo.changeProcessSttusCodeNm}"/></td>
  			<th scope="row">변경접수일</th>
    		<td><c:out value="${changeProcessvo.changeRceptDe}"/></td>
  		</tr>
  		<tr>
    		<th  scope="row"><label for="changeSeCode" class="th_add">변경구분</label></th>
    		<td>
      		<select name="changeSeCode" id="changeSeCode" tabindex="1" style="width:130px" title="변경구분선택">
          	<option value='' >--선택하세요--</option>
          	<c:forEach var="codeinfo" items="${changeSeCodeList}" varStatus="status">
             	<option value='${codeinfo.code}' <c:if test="${changeProcessvo.changeSeCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>
			</select>
    		</td>
    		<th  scope="row"><label for="emrgncyProcessAt" class="th_add">긴급처리여부</label></th>  
    		<td>
      		<select name="emrgncyProcessAt" id="emrgncyProcessAt" style="width:130px" tabindex="2" title="긴급처리여부선택">
          	<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${emrgncyProcessAtList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeProcessvo.emrgncyProcessAt == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
    	</tr>	
  		<tr>
    		<th scope="row"><label for="changeAffcSeCode" class="th_add">변경영향도</label></th>
    		<td>
    		<select name="changeAffcSeCode" id="changeAffcSeCode" style="width:130px" tabindex="3" title="변경영향도선택">
      		<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${changeAffcSeCodeList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeProcessvo.changeAffcSeCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
    		<th scope="row"><label for="changeScopeSeCode" class="th_add">변경범위</label></th>
    		<td>
    		<select name="changeScopeSeCode" id="changeScopeSeCode" style="width:130px" tabindex="4" title="변경범위선택">
      		<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${changeScopeSeCodeList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeProcessvo.changeScopeSeCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
		</tr>
		<tr>    		
		    <th scope="row"><label for="changeConfmAt">CCB승인여부</label></th>
    		<td>
	     	<input type="radio" name="changeConfmAt"  id="changeConfmAt" value="Y" onclick="fn_changeConfmAt_proc()" title="CCB승인" <c:if test="${changeProcessvo.changeConfmAt eq 'Y'}" > checked</c:if> /> Y &nbsp;
	     	<input type="radio" name="changeConfmAt"  id="changeConfmAt" value="N" onclick="fn_changeConfmAt_proc()" title="CCB미승인" <c:if test="${changeProcessvo.changeConfmAt eq 'N'}" > checked</c:if> /> N
	     	</td>
    		<th scope="row"><label for="changeConfmDe">CCB승인일</label></th>
    		<td>
			<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
			<form:input path="changeConfmDe"  id="changeConfmDe" maxlength="10" cssClass="inputsmall01" cssStyle="width:80px;" tabindex="5" title="CCB승인일입력"/>
            <img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.chgProcUpdaterForm, document.chgProcUpdaterForm.changeConfmDe); return false;" />
			</td>
	  	</tr>
  		<tr>
    		<th scope="row"><label for="changeExmntOpinion" >CCB검토의견</label></th>
    		<td  colspan="3">
      		<form:textarea path="changeExmntOpinion"  id="changeExmntOpinion" rows="3" cssStyle="width:500px;" cols="10" cssClass="textarea" tabindex="6" title="CCB검토의견입력"/></td>
  		</tr>
  		<tr>
  		    <th scope="row"><label for="changeOpertorId"  class="th_add">변경담당자</label></th>
  		    <td colspan="3">
 				<select name="changeOpertorId" id="changeOpertorId" title="변경담당자선택" style="width:220px" tabindex="7" >
	          	<option value='' >--선택하세요--</option>
	           	<c:forEach var="authorUser" items="${authorUser}" varStatus="status">
	            <option value='${authorUser.mberId}' <c:if test="${changeProcessvo.changeOpertorId == authorUser.mberId}">selected="selected"</c:if>>${authorUser.mberNm} [${authorUser.mberId}]</option>
			  	</c:forEach>  
	          	</select>
  		    <!-- 
			<input name="changeOpertorId"   id="changeOpertorId" type="hidden" value="<c:out value='${changeProcessvo.changeOpertorId}'/> "  />
			<input name="uniqId" type="hidden" />
			<input name="changeOpertorNm"   id="changeOpertorNm" readonly="readonly"  value="<c:out value='${changeProcessvo.changeOpertorNm}'/>" tabindex="7" title="담당자선택"/>
			<a href="#Link"  onclick="fn_egov_AuthorUser(document.chgProcUpdaterForm.authorCode.value); return false;" class="board_text_link" target="_blank" title="새창">[담당자찾기]</a>
			 -->
			</td>    		  		    
  		</tr>
  		<tr>
  		    <th scope="row"><label for="planExmntMndtAt">담당자권한위임</label></th>
  		    <td colspan="3">
      		<input type="checkbox"  name="planExmntMndtAt"  id="planExmntMndtAt" value="Y" title="계획검토권한위임" <c:if test="${changeProcessvo.planExmntMndtAt eq 'Y' }">checked</c:if> /> 계획검토 &nbsp;
      		<input type="checkbox"  name="comptExmntMndtAt"   id="comptExmntMndtAt" value="Y" title="변경완료검토권한위임" <c:if test="${changeProcessvo.comptExmntMndtAt eq 'Y' }">checked</c:if> /> 변경완료검토
  		    </td>
  		</tr>
  	</table>
	</div>
	
	<div class="subbtn_align">  
		<ul> 
  			<li class="btn02_leftbg"></li>
		    <li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_save(); return false;" class="btn_link" tabindex="8">저장</a></li>
			<li class="btn02_rightbg"></li>
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input" onclick="fn_egov_changeProcList(); return false;" tabindex="9" /></span></li>
			<li class="submit_btn01_right"></li>
		</ul> 
	</div>
	
</div>
<!-- search 조건 -->

  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
  <input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
  <input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
  <input name="searchChangeProcessSttusCode" type="hidden" value="<c:out value='${searchVO.searchChangeProcessSttusCode}'/>"/>  
  <input name="searchEmrgncyRequstAt" type="hidden" value="<c:out value='${searchVO.searchEmrgncyRequstAt}'/>"/>
  <input name="searchOperJobSeCode" type="hidden" value="<c:out value='${searchVO.searchOperJobSeCode}'/>"/>
  
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