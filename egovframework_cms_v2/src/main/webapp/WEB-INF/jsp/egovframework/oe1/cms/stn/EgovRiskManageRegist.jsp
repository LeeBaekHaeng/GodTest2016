<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
 /**
  * @JSP Name : EgovRiskManageRegist.jsp
  * @Description : 위험 등록 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.20  이중호         최초 생성
  *
  * author 운영환경 1팀
  * since 2010.08.20
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */ 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>위험 등록</title>

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
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<validator:javascript formName="riskManage" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">

function fn_egov_onLoad() {
	var maxFileNum = document.riskManage.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_RiskManage(){
	location.href = "<c:url value='/cms/stn/admin/EgovOe1RiskManageList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
  function fn_egov_regist_RiskManage(form){
	if(!validateRiskManage(form)){ 			
		return;
	}else{
		if(confirm('<spring:message code="common.save.msg" />')){
			form.action = "<c:url value='/cms/stn/admin/EgovOe1RiskManageRegist.do'/>"; 
			form.submit();
		}
	}
}




function fn_egov_User_Call(frm) {
	var url = "<c:url value='/cms/com/EgovOe1AuthorUserPopup.do'/>?authorCode=ROLE_OPER_CHARGER";
	var id    = "userPopup";
	var param = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";

	window.open(url, id, param);

}

function fn_egov_AuthorUser_Callback(mberId, uniqId, mberNm) {
	document.riskManage.chargerId.value = mberId;
	document.riskManage.charger.value   = mberNm;
}

function fn_egov_OperImprovReqPop_Call(id) {
    /* 운영개선요청 상세팝업 호출 */
    var url = "<c:url value='/cms/srm/gnrl/selectOperImprovReqPop.do'/>?selectedId="+id;      
    var openParam = "dialogWidth:820px;dialogHeight:520px;scroll:no;status:no;center:yes;resizable:yes;";
    window.showModalDialog(url, "OperImprovReqPop", openParam);
}

</script>
</head>
<body onload="javascript:fn_egov_onLoad();">
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

<form:form commandName="riskManage" name="riskManage" method="post" enctype="multipart/form-data" action="/oe1/cms/stn/admin/EgovOe1RiskManageList.do" >
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
<input id="riskId" name="riskId" type="hidden" value="<c:out value='AUTO'/>"/>

<input type="hidden" name="posblAtchFileNumber" value="10" />

<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 위험 등록</strong></h1></div>
	
	<div id="datail_table">
	<table summary="위험명,위험구분,위험내용,위험도,담당자,작업지시내용,첨부파일 입력 테이블입니다" >
	<caption>위험 등록 테이블</caption>
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		<tr>
			<th scope="row"><span class="th_add"><label for="riskSj">위험명</label></span></th>
			<td >
				<form:input  path="riskSj" id="riskSj" size="30" maxlength="30" cssClass="inputsmall01" title="위험명" tabindex="1"/>
				<form:errors path="riskId"/>
				<form:errors path="riskSj"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="riskTyCode">위험구분</label></span></th>
			<td >
				<form:select path="riskTyCode" id="riskTyCode" cssClass="opselect_smaill01" title="위험구분" tabindex="2">
					<c:forEach var="result" items="${riskTyCode}" varStatus="status">
						<option value='<c:out value="${result.code}"/>' <c:if test="${result.code == riskManageVO.riskTyCode}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
					</c:forEach>			  		   
				</form:select>
				<form:errors path="riskTyCode"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="riskCn">위험내용</label></span></th>
			<td >
				<form:textarea path="riskCn" id="riskCn" rows="3" cols="60" cssClass="textarea" title="위험내용" tabindex="3"/>
				<form:errors   path="riskCn"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="dgdgr">위험도</label></span></th>
			<td >
				<form:select path="dgdgr" id="dgdgr" cssClass="opselect_smaill01" title="위험도" tabindex="4">
					<c:forEach var="result" items="${dgdgr}" varStatus="status">
						<option value='<c:out value="${result.code}"/>' <c:if test="${result.code == riskManageVO.dgdgr}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
					</c:forEach>			  		   
				</form:select>
				<form:errors path="dgdgr"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="chargerId">담당자</label></span></th>
			<td >
				<select name="chargerId" id="chargerId" title="담당자" tabindex="5">
	          	<option value='' >--선택하세요--</option>
	           	<c:forEach var="authorUser" items="${authorUser}" varStatus="status">
	            <option value='${authorUser.mberId}' >${authorUser.mberNm} [${authorUser.mberId}]</option>
			  	</c:forEach>  
	          	</select>				
				<!-- 
				<input type="hidden" id="chargerId" name="chargerId" size="20" maxlength="20" class="inputsmall01" readonly="readonly" style="width:20%;"title="담당자"/>
				<input type="text" id="charger"   name="charger"   size="20" maxlength="20" class="inputsmall01" readonly="readonly" style="width:200px;"title="담당자"/>
				<a href="#LINK" onclick="javascript:fn_egov_User_Call(document.riskManage)" class="board_text_link" title="새창">
					[담당자찾기]
				</a> -->
				<form:errors path="chargerId"/>		
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="opertDrctCn">작업지시내용</label></span></th>
			<td >
				<form:textarea path="opertDrctCn" id="opertDrctCn" rows="3" cols="60" cssClass="textarea" title="작업지시내용" tabindex="6"/>
				<form:errors   path="opertDrctCn"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="egovComFileUploader">첨부파일</label></th>
			<td >
	            <div id="temp">
				    <input name="file_1" id="egovComFileUploader" type="file" title="첨부파일"/>
				    <div id="egovComFileList"></div>
	   	        </div>		  
			</td>
		</tr>


	
	</table>
    </div>


<c:if test="${fn:length(relOperImprovReqList) != 0}">

	<form:hidden path="operImprvmRequstId"/>

	<!-- List -->
	<div id="result_table">
	
	<h1><strong> [관련 개선요청 목록]</strong></h1>
	<table summary="개선요청명,업무구분,요청일,요청자 목록입니다" class="table_style">
		<caption>관련 개선요청 목록 테이블</caption>
		<colgroup>
			<col width="50">
			<col width="180">
			<col width="120">
			<col width="120">
			<col width="120">
		</colgroup>
		<thead>
			<tr>
		        <th scope="col">순번</th>
		        <th scope="col">개선요청명</th>
		        <th scope="col">업무구분</th>
		        <th scope="col">요청일</th>
		        <th scope="col">요청자</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="resultInfo" items="${relOperImprovReqList}" varStatus="status">
			<tr>
				<td >
					<c:out value="${status.count}"/>
				</td>
				<td align="left">
					<a href="#LINK" onclick="fn_egov_OperImprovReqPop_Call('<c:out value="${resultInfo.operImprvmRequstId}"/>'); return false;" class="board_text_link" title="새창">
						<c:out value="${resultInfo.operImprvmRequstSj}" />
					</a>
				</td>
				<td >
					<c:out value="${resultInfo.operJobSeCode}" />
				</td>
				<td >
					<c:out value="${resultInfo.frstRegisterPnttm}" />
				</td>
				<td >
					<c:out value="${resultInfo.frstRegisterId}" />
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- /List -->	
</c:if>				
	
	<!-- 버튼 -->				
  	<div class="subbtn_align">  		
	  	<ul>
  			<li class="btn02_leftbg"></li>
		    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_regist_RiskManage(document.riskManage); return false;" class="btn_link">저장</a></li>
			<li class="btn02_rightbg"></li>
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input" onclick="fn_egov_list_RiskManage(); return false;" /></span></li>
			<li class="submit_btn01_right"></li>			
  		</ul>
  	</div> 				
	<!-- /버튼 -->		
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition"     value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword"       value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="searchDgdgr"         value="<c:out value='${searchVO.searchDgdgr}'/>"/>
<input type="hidden" name="searchRiskTyCode"    value="<c:out value='${searchVO.searchRiskTyCode}'/>"/>
<input type="hidden" name="searchRiskSttusCode" value="<c:out value='${searchVO.searchRiskSttusCode}'/>"/>
<input type="hidden" name="pageIndex"           value="<c:out value='${searchVO.pageIndex}'/>"/>
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
