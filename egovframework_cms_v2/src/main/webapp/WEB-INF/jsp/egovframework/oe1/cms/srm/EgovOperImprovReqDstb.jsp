<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
   * @JSP Name : EgovOperImprovReqDstb.jsp
   * @Description : 운영개선요청 분배 화면
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
<title>운영개선요청 작업분배</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" language="javascript" ></script>
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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="dstbUpdateOIRForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 목록 화면 function */
function fn_egov_selectList() {
   	document.dstbUpdateOIRForm.action = "<c:url value='/cms/srm/admin/selectOperImprovReqDstbList.do'/>";
   	document.dstbUpdateOIRForm.submit();		
}

/* 저장 function */
function fn_egov_save() {	
	frm = document.dstbUpdateOIRForm;

	if(!validateDstbUpdateOIRForm(frm)){
        return;
    }else{
        if(confirm('<spring:message code="common.save.msg" />')){
			document.dstbUpdateOIRForm.action = "<c:url value='/cms/srm/admin/updateOperImprovReqDstb.do' />";
			document.dstbUpdateOIRForm.submit();
        }
    }
}

/* 담당자선택 팝업 function */
function fn_egov_AuthorUser(frm, authorCode) {
    var url   = frm.url.value + "?authorCode=" + authorCode.value;
    var id    = "ROLE_OPER_CHARGER";
    var param = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";

    window.open(url, id, param);
}

/* 담당자선택 팝업 function */
function fn_egov_AuthorUser_Callback(mberId, uniqId, mberNm) {
	dstbUpdateOIRForm.chargerId.value = mberId;
	dstbUpdateOIRForm.uniqId.value = uniqId;
	dstbUpdateOIRForm.chargerNm.value = mberNm;
}

/* 변경이관등록 팝업 function */
function fn_egov_trans_change(){
	frm = document.dstbUpdateOIRForm;
	if(frm.requstTyCode.value==""){
		alert("요청구분은 필수입력값입니다.");
		return false;
	}else if(frm.emrgncyProcessAt.value==""){
		alert("긴급처리여부는 필수입력값입니다.");
		return false;
	}
	
	//if(!validateDstbUpdateOIRForm(frm)){
    //    return;
    //}else{
        if(confirm("변경이관 하시겠습니까?")){
        	frm.action = "<c:url value='/cms/srm/admin/updateOperImprovReqDstbTrans.do' />";
        	frm.submit();
            
        	var changeRequstSysCode = "SRM";
        	var operImprvmRequstId = frm.operImprvmRequstId.value;
        	var url = "<c:url value='/cms/sim/gnrl/changeRequestRegistPopup.do'/>?changeRequstSysCode="+changeRequstSysCode+"&requstSysBasisId="+operImprvmRequstId;	
        	var openParam = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";
        	window.open(url, "TransChange", openParam);
        }
    //}	

	
}

/* 변경등록후 입력항목, 저장버튼 비활성화 function */
function fn_egov_transEnd(){
   	document.dstbUpdateOIRForm.action = "<c:url value='/cms/srm/admin/selectOperImprovReqDstb.do'/>";
   	document.dstbUpdateOIRForm.submit();
} 

/* 요청종료팝업 function */
function fn_egov_returnPop(operImprvmRequstId){
    var url   = "<c:url value='/cms/srm/admin/selectOperImprovReqReturnPop.do'/>?operImprvmRequstId=" + operImprvmRequstId;
    var id    = "요청자contact정보";
    var param = "width=700px,height=200px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";

    window.open(url, id, param);
}

/* 요청종료처리팝업 function */
function fn_egov_return(operImprvmRequstId){
   	document.dstbUpdateOIRForm.action = "<c:url value='/cms/srm/admin/updateOperImprovReqReturn.do'/>";
   	document.dstbUpdateOIRForm.submit();
}
-->
</script>
</head>
<body>
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

<form:form commandName="vo" name="dstbUpdateOIRForm" action="oe1/cms/srm/admin/selectOperImprovReqDstbList.do" >
<input type="hidden" name="operImprvmRequstId" value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="selectedId"   value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="rcepterId"    value="<c:out value='${s_mberId}'/>"/>
<input type="hidden" name="lastUpdusrId" value="<c:out value='${s_mberId}'/>"/>
<input type="hidden" name="returnResn"   value=""/>
<input type="hidden" name="requstSttusCode" value=""/>
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 운영개선요청 작업분배</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	<table summary="개선요청명,요청자,요청일,업무구분,완료요청일,요청내용,요청첨부파일,요청구분,긴급처리여부,담당자,접수일 상세입니다">
	<caption>운영개선요청 작업분배 테이블</caption>
			<colgroup>
				<col width="50">				
				<col width="50">			
				<col width="100">
				<col width="100">
				<col width="100">
			</colgroup>	
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
			<td colspan='3' height='100'>
				<!--<c:out value="${vo.operImprvmRequstCn}"/>-->
				${fn:replace(fn:escapeXml(vo.operImprvmRequstCn),crlf,'<br/>')}
			</td>
		</tr>
  		<tr>
			<th scope="row">첨부파일목록</th>
			<td colspan="3">
	  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
	    			<c:param name="p0aram_atchFileId" value="${vo.requstAtchFileId}" />
	    		</c:import> 
			</td>
  		</tr>
  		<c:if test="${vo.requstSttusCode == '01' || vo.requstSttusCode == '02'}">	
		<tr>
			<th scope="row" rowspan="2">처리정보</th>
			<th scope="row"><span class="th_add" ><label for="requstTyCode">요청구분</label></span></th>
			<td>
	        	<select name="requstTyCode" id="requstTyCode" title="요청구분" tabindex="1" <c:if test="${vo.requstSttusCode != '01'&& vo.requstSttusCode != '02' }">disabled="disabled"</c:if>>
	          	<option value='' >--선택하세요--</option>
	           	<c:forEach var="codeinfo" items="${requstTyCode}" varStatus="status">
	            <option value='${codeinfo.code}' <c:if test="${vo.requstTyCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
			  	</c:forEach>  
	          	</select>				
			</td>
			<th scope="row"><span class="th_add"><label for="emrgncyProcessAt">긴급처리여부</label></span></th>
			<td>
	        	<select name="emrgncyProcessAt" id="emrgncyProcessAt" title="긴급처리여부" tabindex="2" <c:if test="${vo.requstSttusCode != '01'&& vo.requstSttusCode != '02' }">disabled="disabled"</c:if>>
	          	<option value='' >--선택하세요--</option>
	           	<c:forEach var="codeinfo" items="${emrgncyProcessAt}" varStatus="status">
	            <option value='${codeinfo.code}' <c:if test="${vo.emrgncyProcessAt == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
			  	</c:forEach>  
	          	</select>		
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="chargerNm">담당자</label></span></th>
			<td>
				<select name="chargerId" id="chargerId" title="담당자" tabindex="3" <c:if test="${vo.requstSttusCode != '01'&& vo.requstSttusCode != '02' }">disabled="disabled"</c:if>>
	          	<option value='' >--선택하세요--</option>
	           	<c:forEach var="authorUser" items="${authorUser}" varStatus="status">
	            <option value='${authorUser.mberId}' <c:if test="${vo.chargerId == authorUser.mberId}">selected="selected"</c:if>>${authorUser.mberNm} [${authorUser.mberId}]</option>
			  	</c:forEach>  
	          	</select>
	          	<!--  
				<input type="hidden" name="url" value="/oe1/cms/com/EgovOe1AuthorUserPopup.do" />
    			<input type="hidden" name="authorCode" value="ROLE_OPER_CHARGER " />
			    <form:hidden path="chargerId"/>
			    <input type="hidden" name="uniqId" value="" />
			    <form:input path="chargerNm" id="chargerNm" cssStyle="width:150px;" cssClass="inputsmall01_readOnly"  readonly="true" title="담당자명"/>	
			    <c:if test="${vo.requstSttusCode == '01'|| vo.requstSttusCode == '02' }">
			    	<a href="#LINK" onclick="fn_egov_AuthorUser(document.dstbUpdateOIRForm,document.dstbUpdateOIRForm.authorCode); return false;"  class="board_text_link" title="새참">[담당자찾기]</a>
			    </c:if>
			    -->
			</td>
			<th scope="row">접수일</th>
			<td><form:hidden path="rceptDt"/><c:out value="${vo.rceptDt}"/></td>
		</tr>
		</c:if>
		<c:if test="${vo.requstSttusCode != '01' && vo.requstSttusCode != '02' && vo.requstSttusCode != '07'}">	
		<tr>
			<th scope="row" rowspan='5'>처리정보</th>
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
		</c:if>
		<c:if test="${vo.requstSttusCode == '07'}">	
		<tr>
			<th scope="row" colspan="2">요청종료사유</th>
			<td colspan="3" height='100'>${fn:replace(fn:escapeXml(vo.returnResn),crlf,'<br/>')}	</td>
		</tr>
		</c:if>
	</table>
  </div>
  
  <div class="subbtn_align">  		
	  	<ul>
	  		<c:if test="${vo.requstSttusCode == '01'|| vo.requstSttusCode == '02' }">
	  			<li class="btn02_leftbg"></li>
			    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_trans_change(); return false;" class="btn_link">변경이관</a></li>
				<li class="btn02_rightbg"></li>
	  			<li class="btn02_leftbg"></li>
			    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_returnPop('${vo.operImprvmRequstId}'); return false;" class="btn_link" target="_blank" title="새창">요청종료</a></li>
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
<input type="hidden" name="searchCondition"    value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword"      value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="srequstSttusCode"   value="<c:out value='${searchVO.srequstSttusCode}'/>"/>
<input type="hidden" name="soperJobSeCode"     value="<c:out value='${searchVO.soperJobSeCode}'/>"/>
<input type="hidden" name="pageIndex"          value="<c:out value='${searchVO.pageIndex}'/>"/>
</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

