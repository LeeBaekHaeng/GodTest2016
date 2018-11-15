<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
   * @JSP Name : EgovOperImprovReqProc.jsp
   * @Description : 운영개선요청 처리 화면
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
<title>운영개선요청 처리</title>

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
<validator:javascript formName="procUpdateOIRForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fn_egov_onload() {
	var maxFileNum = document.procUpdateOIRForm.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}

/* 목록 화면 function */
function fn_egov_selectList() {
   	document.procUpdateOIRForm.action = "<c:url value='/cms/srm/chrg/selectOperImprovReqProcList.do'/>";
   	document.procUpdateOIRForm.submit();		
}

/* 첨부파일 2개 처리 */
function fn_egov_file(){
	var frm = document.procUpdateOIRForm;
	var requstAtchFileId = frm.requstAtchFileId.value;
	var input = document.getElementsByTagName("input");
	var cnt = 0;
	if(input != null && input.length> 0){
		for (var i = 0; i < input.length; i++) {
			if (input[i].name == "atchFileId") {
				cnt = cnt+1;
			}
		}
	}else{
		return false;
	}

	if(cnt > 1){
		frm.requstAtchFileId.value = frm.atchFileId[0].value;
		frm.processAtchFileId.value = frm.atchFileId[1].value;
	}else if (cnt == 1 || requstAtchFileId != ""){
		frm.requstAtchFileId.value = frm.atchFileId.value;
	}else if (cnt == 1 || requstAtchFileId == ""){
		frm.processAtchFileId.value = frm.atchFileId.value;
	}else {
		frm.processAtchFileId.value = frm.atchFileId.value;
	}
	return true;
}

/* 저장 function */
function fn_egov_save() {	
	frm = document.procUpdateOIRForm;
	frm.requstSttusCode.value="04"; //상태값:작업중

	if(fn_egov_file()){
	}else{
		alert("첨부파일 처리 문제발생");
		return false;
	}

	if(!validateProcUpdateOIRForm(frm)){
        return false;
    }else{
	    if(confirm('<spring:message code="common.save.msg" />')){
	    	frm.action = "<c:url value='/cms/srm/chrg/updateOperImprovReqProc.do' />";
	    	frm.submit();
	    }
    }
}

/* 완료처리 function */
function fn_egov_end() {	
	frm = document.procUpdateOIRForm;
	frm.requstSttusCode.value="06"; //상태값:완료

	if(fn_egov_file()){
	}else{
		alert("첨부파일 처리 문제발생");
	}
	
	if(!validateProcUpdateOIRForm(frm)){
        return false;
    }else{
        if(confirm("완료처리 하시겠습니까? \n\n완료처리 이후에는 처리내용을 수정할 수 없습니다.")){
			frm.action = "<c:url value='/cms/srm/chrg/updateOperImprovReqProc.do' />";
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

<form:form commandName="vo" name="procUpdateOIRForm" enctype="multipart/form-data" method="post" action="oe1/cms/srm/chrg/selectOperImprovReqProcList.do" >
<input type="hidden" name="operImprvmRequstId"  value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="selectedId"          value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="requstSttusCode"     value="<c:out value='${vo.requstSttusCode }'/>"/>
<input type="hidden" name="lastUpdusrId"        value="<c:out value='${s_mberId}'/>"/>
<input type="hidden" name="requstAtchFileId"    value="<c:out value='${vo.requstAtchFileId }'/>"/>
<input type="hidden" name="processAtchFileId"   value="<c:out value='${vo.processAtchFileId }'/>"/>
<input type="hidden" name="fileListCnt"         value="0" />
<input type="hidden" name="posblAtchFileNumber" value="10" />
<input type="hidden" name="returnUrl"           value="/oe1/cms/srm/chrg/selectOperImprovReqProc.do" />

<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 운영개선요청 처리</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	<table summary="운영개선요청  처리정보(처리완료일,처리내용,처리첨부파일) 입력화면 입니다.">
	<caption>운영개선요청 처리 테이블</caption>
		<tr>
			<th scope="row" rowspan='5'>요청정보</th>
			<th scope="row">개선요청명</th>
			<td colspan='3'><c:out value="${vo.operImprvmRequstSj}"/></td>
		</tr>
		<tr>
			<th scope="row">요청자</th>
			<td><c:out value="${vo.frstRegisterNm}"/></td>
			<th scope="row">요청일</th>
			<td><c:out value="${vo.frstRegisterPnttm}"/></td>
		</tr>
		<tr>
			<th scope="row">업무구분</th>
			<td><c:out value="${vo.operJobSeCodeNm}"/></td>
			<th scope="row">완료요청일</th>
			<td><c:out value="${vo.comptRequstDe}"/></td>
		</tr>
		<tr>
			<th scope="row">요청내용</th>
			<td colspan='3' height='100' >
				<!--<c:out value="${vo.operImprvmRequstCn}"/>-->
				${fn:replace(fn:escapeXml(vo.operImprvmRequstCn),crlf,'<br/>')}
			</td>
		</tr>
  		<tr>
			<th scope="row" width="20%">첨부파일목록</th>
			<td colspan="3">
	  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
	    			<c:param name="param_atchFileId" value="${vo.requstAtchFileId}" />
	    		</c:import> 
			</td>
  		</tr>	
		<tr>
			<th scope="row" rowspan="8">처리정보</th>
			<th scope="row">요청구분</th>
			<td>
	        	<c:out value="${vo.requstTyCodeNm}"/>				
			</td>
			<th scope="row">긴급처리여부</th>
			<td>
	        	<c:out value="${vo.emrgncyProcessAtNm}"/>	
			</td>
		</tr>
		<tr>
			<th scope="row">담당자</th>
			<td>
				<c:out value="${vo.chargerNm}"/>	
			</td>
			<th scope="row">접수일</th>
			<td><c:out value="${vo.rceptDt}"/></td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="processComptDe">처리완료일</label></span></th>
			<td colspan="3">
				<c:choose>
					<c:when test="${vo.requstSttusCode != '06'}">
						<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
						<form:input path="processComptDe" id="processComptDe" maxlength="10" cssClass="inputsmall01" cssStyle="width:80px" title="처리완료일" tabindex="1"/>
			            <img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력팝업" width="16" height="16" onclick="fn_egov_NormalCalendar(document.procUpdateOIRForm, document.procUpdateOIRForm.processComptDe); return false;" />						
					</c:when>
					<c:otherwise>
						<c:out value="${vo.processComptDe}"/>					
					</c:otherwise>
				</c:choose>	
			</td>	
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="processCn">처리내용</label></span></th>
			<td colspan="3">
				<c:choose>
					<c:when test="${vo.requstSttusCode != '06'}">	
						<form:textarea path="processCn" id="processCn" rows="7" cols="100"  cssClass="textarea" title="처리내용" tabindex="2" />
						&nbsp;<form:errors path="processCn" />					
					</c:when>
					<c:otherwise>
						${fn:replace(fn:escapeXml(vo.processCn),crlf,'<br/>')}					
					</c:otherwise>
				</c:choose>	
			</td>
		</tr>
		<c:choose>
			<c:when test="${vo.requstSttusCode == '02' || vo.requstSttusCode == '04'}">
		  		<tr>
		    		<th scope="row" id="file_upload_posbl" ><label for="egovComFileUploader">첨부파일</label></th>
		    		<td colspan="3" >
		      		<input type="file" name="fileField" id="egovComFileUploader" class="ser_box" />
		    		</td>
		  		</tr>
		  		<tr> 
		    		<th scope="row" rowspan="2">첨부파일목록</th>
		    		<td colspan="3">
		      		<c:if test="${vo.processAtchFileId != ''&& not empty vo.processAtchFileId}">
					<c:import url="/cms/cmm/selectFileInfsForUpdate2.do" charEncoding="utf-8">
				  	<c:param name="param_atchFileId" value="${vo.processAtchFileId}" />
					</c:import>
			  		</c:if>	
		    		</td>
		  		</tr> 
		  		<tr>
					<td colspan="3"><div id="egovComFileList"></div></td>
		  		</tr> 				
			</c:when>
			<c:otherwise>
		  		<tr>
					<th scope="row" width="20%">첨부파일목록</th>
					<td colspan="3">
			  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
			    			<c:param name="param_atchFileId" value="${vo.processAtchFileId}" />
			    		</c:import> 
					</td>
		  		</tr>				
			</c:otherwise>
		</c:choose>		
			
	</table>
  </div>

	<div class="subbtn_align">  		
		<ul>
			<c:if test="${vo.requstSttusCode != '06' && vo.chargerId==s_mberId}">
				<li class="btn02_leftbg"></li>
				<li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_end(); return false;" class="btn_link">완료처리</a></li>
				<li class="btn02_rightbg"></li>
				<li class="btn02_leftbg"></li>
				<li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_save(); return false;" class="btn_link">저장</a></li>
				<li class="btn02_rightbg"></li>
			</c:if>
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input" onclick="fn_egov_selectList(); return false;" /></span></li>
			<li class="submit_btn01_right"></li>				
		</ul>
	</div>   

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition"   value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword"     value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="srequstSttusCode"  value="<c:out value='${searchVO.srequstSttusCode}'/>"/>
<input type="hidden" name="soperJobSeCode"    value="<c:out value='${searchVO.soperJobSeCode}'/>"/>
<input type="hidden" name="pageIndex"         value="<c:out value='${searchVO.pageIndex}'/>"/>
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

