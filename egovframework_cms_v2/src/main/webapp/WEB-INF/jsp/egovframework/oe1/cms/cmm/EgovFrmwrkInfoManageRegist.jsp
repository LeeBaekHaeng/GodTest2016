<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @JSP Name : EgovFrmwrkInfoManageRegist.jsp
  * @Description : 프레임웍환경정보 등록
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.03  김영심          최초 생성
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
<title>프레임웍환경정보 등록</title>

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
<validator:javascript formName="egovOe1FrmwrkInfoManageVO" staticJavascript="false" xhtml="true" cdata="true"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/*프레임웍환경정보 등록처리 요청 */
function fn_egov_save() {
		
	if(validateEgovOe1FrmwrkInfoManageVO(document.frmwrkInfoForm)){
		
		if(confirm('<spring:message code="common.save.msg" />')){
    	document.frmwrkInfoForm.action = "<c:url value='/cms/cmm/frmwrkInfoRegister.do'/>";
    	document.frmwrkInfoForm.submit();		
		}
	}
}
/*프레임웍환경정보 목록 요청 */
function fn_egov_frmwrkInfoList() {
	document.frmwrkInfoForm.action = "<c:url value='/cms/cmm/frmwrkInfoList.do'/>";
	document.frmwrkInfoForm.submit();
}

//-->
</script>
</head>
<body>
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

<form:form commandName="frmwrkInfoVO" name="frmwrkInfoForm"  action="/oe1/cms/cmm/frmwrkInfoRegister.do" method="post" onsubmit="javascript:fn_egov_save(); return false;">
<input name="infoChghy" type="hidden" value="최초등록"/>

<div id="content_pop">

	<div id="h2_topnav"><h1><strong> 프레임웍환경정보 처리</strong></h1></div>
	<div id="datail_table2">
	<table summary="프레임웍환경정보 항목을 등록합니다.">
	<caption>프레임웍환경정보처리</caption>
		<tr>
    		<th style="width:20%;" rowspan="2" scope="row">프레임워크 관리정보</th>
    		<td style="width:20%;"><label for="presnatnlyr"><span class="th_add">Presentation Layer</span></label></td>
    		<td style="width:60%;">
	    		<form:select path="presnatnlyr" id="presnatnlyr" cssClass="opselect_smaill01" cssStyle="width:100px;" title="Presentation Layer"  tabindex="1">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${presnatnlyr_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="presnatnlyr" />
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;"><label for="persstnlyr"><span class="th_add">persistence Layer</span></label></td>
    		<td style="width:60%;">
	    		<form:select path="persstnlyr" id="persstnlyr" cssClass="opselect_smaill01" cssStyle="width:100px;" title="persistence Layer"  tabindex="2">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${persstnlyr_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="persstnlyr" />
	   		</td>
  		</tr>	
		<tr>
    		<th style="width:20%;" rowspan="5" scope="row">기초정보</th>
    		<td style="width:20%;"><label for="dbmsKindCode"><span class="th_add">DBMS</span></label> </td>
    		<td style="width:60%;">
	    		<form:select path="dbmsKindCode"  id="dbmsKindCode" cssClass="opselect_smaill01" cssStyle="width:100px;" title="DBMS"  tabindex="3">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${dbmsKindCode_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="dbmsKindCode" />
				&nbsp;&nbsp;<label for="dbmsVer"> Ver. </label><form:input path="dbmsVer"  id="dbmsVer" cssClass="inputsmall01" cssStyle= "width:80px" maxlength="50" title="DBMS버전" tabindex="4"/>
	   			&nbsp;<form:errors path="dbmsVer" />
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;"><label for="websKindCode"><span class="th_add">WEB서버</span></label></td>
    		<td style="width:60%;">
	    		<form:select path="websKindCode"  id="websKindCode" cssClass="opselect_smaill01" cssStyle="width:100px;" title="WEB서버"  tabindex="5">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${websKindCode_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="websKindCode" />
				&nbsp;&nbsp;<label for="websVer"> Ver. </label><form:input path="websVer"  id="websVer" cssClass="inputsmall01"  cssStyle= "width:80px" maxlength="50" title="WEB서버버전" tabindex="6"/>
	   			&nbsp;<form:errors path="websVer" />				
	   		</td>
  		</tr>	
  		<tr>
    		<td style="width:20%;"><label for="wasKindCode"><span class="th_add">WAS </span></label></td>
    		<td style="width:60%;">
	    		<form:select path="wasKindCode"  id="wasKindCode" cssClass="opselect_smaill01" cssStyle="width:100px;" title="WAS"  tabindex="7">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${wasKindCode_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="wasKindCode" />
				&nbsp;&nbsp;<label for="wasVer"> Ver. </label><form:input path="wasVer"  id="wasVer" cssClass="inputsmall01"  cssStyle= "width:80px"  maxlength="50" title="WAS버전" tabindex="8"/>
	   			&nbsp;<form:errors path="wasVer" />							
	   		</td>
  		</tr>	
  		<tr>
    		<td style="width:20%;"><label for="osKindCode"><span class="th_add">OS</span></label> </td>
    		<td style="width:60%;">
	    		<form:select path="osKindCode"  id="osKindCode" cssClass="opselect_smaill01" cssStyle="width:100px;" title="OS"  tabindex="9">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${osKindCode_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="osKindCode" />
				&nbsp;&nbsp; <label for="osVer">Ver. </label><form:input path="osVer"  id="osVer" cssClass="inputsmall01"  cssStyle= "width:80px"  maxlength="50" title="OS버전" tabindex="10"/>
	   			&nbsp;<form:errors path="osVer" />						
	   		</td>
  		</tr>	
  		<tr>
    		<td style="width:20%;"><label for="jdkVerCode"><span class="th_add">JDK버전</span></label> </td>
    		<td style="width:60%;">
	    		<form:select path="jdkVerCode"  id="jdkVerCode" cssClass="opselect_smaill01" cssStyle="width:100px;" title="JDK버전"  tabindex="11">
	    		<option value="">--선택하세요--</option>
				<c:forEach items="${jdkVerCode_result}" var="codeInfo" varStatus="status">
				<option value="${codeInfo.code}" ><c:out value="${codeInfo.codeNm}"/></option>
				</c:forEach>
				</form:select>
				&nbsp;<form:errors path="jdkVerCode" />
	   		</td>
  		</tr>	  	
		<tr>
    		<th style="width:20%;" rowspan="3"  scope="row">컴포넌트 적용정보</th>
    		<td style="width:20%;"><label for="serverScrtyApplcAt">Server Security 적용여부</label> </td>
    		<td style="width:60%;">
			<input type="radio" name="serverScrtyApplcAt"  id="serverScrtyApplcAt" value="Y"  title="Yes"  /> Y &nbsp;
	     	<input type="radio" name="serverScrtyApplcAt"  id="serverScrtyApplcAt" value="N"  title="No" checked/> N
			<form:errors path="serverScrtyApplcAt" />
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;"><label for="gpkiApplcAt">GPKI 적용여부</label> </td>
    		<td style="width:60%;">
			<input type="radio" name="gpkiApplcAt"  id="gpkiApplcAt" value="Y"  title="Yes"  /> Y &nbsp;
	     	<input type="radio" name="gpkiApplcAt"  id="gpkiApplcAt" value="N"  title="No" checked/> N
			<form:errors path="gpkiApplcAt" />
	   		</td>
  		</tr>  		
		<tr>
    		<td style="width:20%;"><label for="ogcrLoginApplcAt">인증서 로그인 적용여부</label> </td>
    		<td style="width:60%;">
			<input type="radio" name="ogcrLoginApplcAt"  id="ogcrLoginApplcAt" value="Y"  title="Yes"  /> Y &nbsp;
	     	<input type="radio" name="ogcrLoginApplcAt"  id="ogcrLoginApplcAt" value="N"  title="No" checked/> N
			<form:errors path="ogcrLoginApplcAt" />
	   		</td>
  		</tr>   		
		<tr>
    		<th style="width:20%;" rowspan="3"  scope="row">기타 정보</th>
    		<td style="width:20%;"><label for="etcInfo01">기타정보1 </label></td>
    		<td style="width:60%;">
			<form:input path="etcInfo01"  id="etcInfo01" cssClass="inputsmall01" size="80" maxlength="80" title="기타정보1" tabindex="12"/>
			<form:errors path="etcInfo01" />
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;"><label for="etcInfo02">기타정보2 </label></td>
    		<td style="width:60%;">
			<form:input path="etcInfo02"  id="etcInfo02" cssClass="inputsmall01" size="80" maxlength="80" title="기타정보2" tabindex="13"/>
			<form:errors path="etcInfo02" />
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;"><label for="etcInfo03">기타정보3 </label></td>
    		<td style="width:60%;">
			<form:input path="etcInfo03"  id="etcInfo03" cssClass="inputsmall01" size="80" maxlength="80" title="기타정보3" tabindex="14"/>
			<form:errors path="etcInfo03" />
	   		</td>
  		</tr>  		  			  			
	</table>
	</div>

	<div class="subbtn_align">  
			<ul> 
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" tabindex="15"/></span></li>
			    <li class="submit_btn01_right"></li>
		    	<li class="btn02_leftbg"></li>
		    	<li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/frmwrkInfoList.do'/>" onclick="fn_egov_frmwrkInfoList(); return false;" class="btn_link" tabindex="16">목록</a></li>
		    	<li class="btn02_rightbg"></li>
			</ul> 
	</div>   
</div>
	
<!-- search 조건 -->
  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
  <input name="toDate" type="hidden" value="<c:out value='${searchVO.toDate}'/>"/>
  <input name="fromDate" type="hidden" value="<c:out value='${searchVO.fromDate}'/>"/>
  
</form:form>
	
</div>

<!-- BODY 내용 END -->

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
