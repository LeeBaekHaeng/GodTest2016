<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @JSP Name : EgovCompnDetail.jsp
 * @Description : 컴포넌트 상세조회
 * @Modification Information
 * 
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2010.07.16  김연수          최초 생성
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
<title>컴포넌트 상세조회 </title>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

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
<!-- validator scrpit START -->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="compnVO" staticJavascript="false"	xhtml="true" cdata="false" />
	
<!-- 업무 scrpit START -->
<c:set var="rowCount" value="${fn:length(resultlist)}"/>
<script type="text/javaScript" language="javascript" defer="defer"><!--
<!--

/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/cms/arc/selectCompn.do'/>";
   	document.detailForm.submit();		
}

function fn_egov_selectTree() {
   	document.detailForm.action = "<c:url value='/test.do'/>";
   	document.detailForm.submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
	if(confirm('<spring:message code="common.delete.msg" />')){
	   	document.detailForm.action = "<c:url value='/cms/arc/removeCompn.do'/>";
	   	document.detailForm.submit();
	}		
}

/* 글 등록 function */
function fn_egov_save() {	

	tag = document.getElementsByTagName("input"); 

	for(var i=0; i<tag.length;i++)
	{
		if(tag[i].getAttribute("name") == "classId")
	    {
			if(fn_egov_isEmpty(tag[i].value)==true)
			 {
		    	 alert("클래스명의 형식이 올바르지 않거나 입력되지 않았습니다");
			     return;
		     }
	    }
		if(tag[i].getAttribute("name") == "classPkg")
	    {
			if(fn_egov_isEmpty(tag[i].value)==true)
			 {
		    	 alert("패키지명의 형식이 올바르지 않거나 입력되지 않았습니다");
			     return;
		     }
	    }
	}
	frm = document.detailForm;
	if(!validateCompnVO(frm)){
        return;
    }else{
    	if(confirm('<spring:message code="common.save.msg" />'))  {
	    	frm.action = "<c:url value='/cms/arc/updtCompn.do'/>";
	        frm.submit();
    	}
    }
}


/*********************************************************
* 추가처리 함수
******************************************************** */
function fn_egov_addRow() {

	tId= document.getElementById("tableId");
	
	var rowCount = tId.rows.length;

		// 이전 로우에 데이터가 없으면 추가버튼 기능 작동 안함.
		if(rowCount > 1){
			var rowNullAT = document.detailForm.classNm[rowCount-1].value
			
			if(null == rowNullAT || "" == rowNullAT){
				alert("클래스명을 입력하세요");
				return;
			}
		}else{
			var rowNullAT = document.detailForm.classNm.value

			if(null == rowNullAT || "" == rowNullAT){
				alert("클래스명을  입력하세요");
				return;
			}
		}
		
	  //row 추가
	  var oRow = tId.insertRow(-1);
	 
	  oRow.onmouseover=function(){tId.clickedRowIndex=this.rowIndex};
	  //cell 추가	  
	  var oCell1 = oRow.insertCell(0);
	  var oCell2 = oRow.insertCell(1);
	  var oCell3 = oRow.insertCell(2);
	  var oCell4 = oRow.insertCell(3);

	  //tId.rows[0].cells[0].width="100";
	  //tId.rows[0].cells[2].width="100";
	  
	  //스타일 적용
	  //oCell1.className="tbtd_content";
	  //oCell2.className="tbtd_content";

	  oCell1.innerHTML = "<input name='classNm' readonly='readonly' value='' size='35'/>";
	  oCell2.innerHTML = "<a href='#Link' onclick='fn_egov_selectCompnPop(event)'> 검색</a>";
	  oCell3.innerHTML = "<input name='classPckage' readonly='readonly' value='' size='45'/>";
	  oCell4.innerHTML = "<a href='#Link' onclick='delRow(event)'>삭제</a> <input name='classId' type='hidden' value=''><input name='useAt' type='hidden' value=''>";
	  document.detailForm.addRowCoutn.value = tId.rows.length;
	  document.recalc();
		  	  
}

/*********************************************************
 * 
 *  삭제 처리 함수
 *********************************************************/
function fn_egov_delRow(count) {	

	 var rowCount = tId.rows.length ;
		tId= document.getElementById("tableId");	
	if(1 < rowCount){
		tId.deleteRow(tId.clickedRowIndex);
		rowCount = rowCount - 1;
	}else{
		document.detailForm.classNm.value = "";
		document.detailForm.classPckage.value = "";
	}
		
	document.detailForm.addRowCoutn.value = tId.rows.length;	

}

//조회한 데이터를 화면에 뿌려준 후 clickedrowIndex가 파이어폭스에서 작동하지 않아 아랫 삭제처리함수를 사용함
function delRow(evt) {
	 tId= document.getElementById("tableId");
	  //인터넷 익스플로러와 파이어폭스를 구분하여 이벤트 객체를 얻습니다
	 var targetE;
	 if (evt == null) {
	  evt = window.event;
	 }
	 if (evt.srcElement) {
	  targetE = evt.srcElement;
	 } else if (evt.target) {
	  targetE = evt.target;
	 }
	 if (targetE.nodeType==3) {
	  targetE = targetE.parentNode;
	 }
	
	 //이벤트가 발생한 객체가 무엇인지 알고 싶을 때 보세요
	 //alert(targetE.tagName);
	
	 var oTable = document.getElementById("tableId");
	 if (evt.srcElement) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.

		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[3].childNodes[1] == targetE || oTable.rows[i].cells[3].childNodes[0] == targetE) {
		   oTable.deleteRow(i);
		   break;
		  }
		 }
	 } else if (evt.target) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.

		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[3].childNodes[0] == targetE || oTable.rows[i].cells[3].childNodes[3] == targetE) {
		   oTable.deleteRow(i);
		   break;
		  }
		 }
	 }
	 document.detailForm.addRowCoutn.value = tId.rows.length;	
	 
}

/*********************************************************
* 문자 길이검사
******************************************************** */
function fn_egov_isEmpty(str) 
{
	if (null == str || 0 == str.length || "" == str) 
	{
		return true;
	}
	return false;
}

/*********************************************************
* 팝업처리 함수
******************************************************** */
function fn_egov_selectCompnPop(evt){

	tId= document.getElementById("tableId");

	// 클릭이벤트 해당 인덱스 찾기
	var rowIndex = "";
	 var targetE;
	 if (evt == null) {
	  evt = window.event;
	 }
	 if (evt.srcElement) {
	  targetE = evt.srcElement;
	 } else if (evt.target) {
	  targetE = evt.target;
	 }
	 if (targetE.nodeType==3) {
	  targetE = targetE.parentNode;
	 }
	//이벤트가 발생한 객체가 무엇인지 알고 싶을 때 보세요
	//alert(targetE.tagName);
	 var oTable = document.getElementById("tableId");
	 if (evt.srcElement) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.
			
		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[1].childNodes[0] == targetE) {
			  rowIndex = i;
		   break;
		  }
		 }
	 } else if (evt.target) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.

		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[1].childNodes[0] == targetE || oTable.rows[i].cells[1].childNodes[1] == targetE) {
			  rowIndex = i;
		   break;
		  }
		 }
	 } 

	var rowCount = tId.rows.length;
	
	if(rowCount > 1){

		var rowNullAT = document.detailForm.classNm[rowCount-1].value;
		
		if(null != rowNullAT){
			window.open("<c:url value='/cms/arc/selectClassListPopUp.do?popupAt=Y&rowCount="+rowCount+"&rowIndex=' />"+rowIndex,"classList","width=800,height=550,left=0,top=0");
		}else{
			alert("클래스명을 입력하세요");
			return;
		}
		
	}else{
		window.open("<c:url value='/cms/arc/selectClassListPopUp.do?popupAt=Y&rowIndex=0&rowCount=1' />","classList","width=800,height=550,left=0,top=0");
	}
}
function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>

	tId= document.getElementById("tableId");
	var rowCount = tId.rows.length;
	document.detailForm.addRowCoutn.value = tId.rows.length;
}
-->
</script>
<!-- 업무 scrpit END -->
</head>

<body onload="init();">
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
		
		<div id="content">
		<!-- BODY 내용 START -->
		<form:form commandName="compnVO" name="detailForm">
		<input name="addRowCoutn" id="addRowCoutn" type="hidden" value=""/>
		<div id="content_pop">
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 컴포넌트 상세정보</strong></h1></div>
		<!-- // 타이틀 -->		

		<!-- 목록 시작-->
		<div id="result_table">
		<table summary="컴포넌트 상세정보" class="table_style" >
		<caption>컴포넌트 상세정보</caption>
			<colgroup>
				<col width="100">
				<col width="150">
				<col width="100">
				<col width="150">
			</colgroup>				
			<tr>
				<th scope="row"><span class="th_add">업무</span></th>
				<td colspan="3">
					<select name="compnPckage" id="compnPckage" title="업무">
					  <option value=''>--업무명--</option>
					  <c:forEach items="${compnCodeList}" var="codeInfo" varStatus="status">
						<option value='${codeInfo.code}' <c:if test="${compnVO.compnPckage == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
					  </c:forEach>
					</select>					
				</td>	
			</tr>
			<tr>
				<th scope="row"><span class="th_add">컴포넌트</span></th>
				<td class="tbtd_content" colspan="3" >
					<form:input path="compnNm" maxlength="90" cssClass="txt" size="91" title="컴포넌트"/>
					&nbsp;<form:errors path="compnNm" />
				</td>	
			</tr>
			<tr>
				<th scope="row"><span class="tbtd_caption">컴포넌트 설명</span></th>					
				<td class="tbtd_content" colspan="3">
					<form:input path="compnDc" maxlength="900" cssClass="txt" size="91" title="컴포넌트 설명"/>
					&nbsp;<form:errors path="compnDc" />
				</td>
			</tr>
			<tr>				
				<th scope="row"><span class="tbtd_caption">비고</span></th>
				<td class="tbtd_content" colspan="3">
					<form:input path="etcDc" maxlength="900" cssClass="txt" size="91" title="비고"/>
					&nbsp;<form:errors path="etcDc" />
				</td>
			</tr>
			<tr style="display:none">				
				<th scope="row"><span class="tbtd_caption">compnId</span></th>
				<td class="tbtd_content" colspan="3">
					<form:input path="compnId" maxlength="900" cssClass="txt" size="91" title="compnId"/>
					&nbsp;<form:errors path="compnId" />
				</td>
			</tr>
		</table>
		<!-- 타이틀 끝 -->

		
		<!-- 타이틀 시작-->
		
		<div id="h2_topnav"><h1><strong>클래스 정보</strong></h1></div>
		
		<!-- 타이틀 끝 -->	
		<table summary="컴포넌트 상세정보 패키지 및 클래스" class="table_style" >
		<caption>컴포넌트 상세정보</caption>
			<colgroup>
				<col width="200">
				<col width="30">
				<col width="250">
				<col width="30">
			</colgroup>				
			<tr>
				<td class="tbtd_caption" >클래스명  </td>
				<td class="tbtd_caption" >        </td>
				<td class="tbtd_caption" >패키지명  </td>
				<td><span class="btn_blue_l"><a href="javascript:fn_egov_addRow();">추가</a></span></td>
			</tr>
		</table>	
		<table class="table_style" id="tableId">
			<colgroup>
				<col width="200">
				<col width="30">
				<col width="250">
				<col width="30">
			</colgroup>			
			<c:if test="${!empty resultlist}">
			<c:forEach var="compnVO" items="${resultlist}" varStatus="status">
			  <tr onmouseover="tableId.clickedRowIndex=this.rowIndex">
			    <td class="tbtd_content">
			    
			    	<!-- 2010.11.17 
			    	<label for="classNm"<c:out value="${status.count}"/> style="display: none;">클래스명<c:out value="${status.count}"/> </label>
			    	-->

			    	<label for="classNm<c:out value='${status.count}'/>" style="display: none;">클래스명<c:out value="${status.count}"/> </label>
			    	
			    	<input name="classNm" readonly="readonly" id="classNm<c:out value='${status.count}'/>" value='<c:out value="${compnVO.classNm}" escapeXml="false"/>'  size="35"/>
			    </td>
			    <td>
			    	<a href="#Link" onclick="fn_egov_selectCompnPop(event)" title="새창">검색</a>
			    </td>
			    <td class="tbtd_content">
			    	<label for="classPckage<c:out value='${status.count}'/>" style="display: none;">패키지명<c:out value="${status.count}"/> </label>
			    	<input name="classPckage" readonly="readonly" id="classPckage<c:out value='${status.count}'/>" value='<c:out value="${compnVO.classPckage}" escapeXml="false"/>'  size="45">
			    </td>
			    <td class="tbtd_content">
			       		<!-- <a href="javascript:fn_egov_delRow(<c:out value="${status.count}"/>);">삭제</a> -->
			       		<a href="#Link" onclick="delRow(event); return false;">삭제</a>
			        <input name="useAt" type="hidden" id="useAt<c:out value='${status.count}'/>" value='<c:out value="${compnVO.useAt}" escapeXml="false"/>'  />
			        <input name="classId" type="hidden" id="classId<c:out value='${status.count}'/>" value='<c:out value="${compnVO.classId}" escapeXml="false"/>'  />
			    </td>
			  </tr>
			  <c:set var="rowCount" value="${status.count}"/>
			</c:forEach>
			</c:if>
			
			<c:if test="${empty resultlist}">
			<c:set var="rowCount" value="1"/>
			   <tr onmouseover="tableId.clickedRowIndex=this.rowIndex">
			    <td class="tbtd_content">
			    	<input name="classNm" readonly="readonly" id="classNm" class="txt" value='<c:out value="${compnVO.classNm}" escapeXml="false"/>' size="35"/>
			    </td>
			    <td>
					<a href="#Link" onclick="fn_egov_selectCompnPop(event)" title="새창">검색</a>
				</td>
			    <td class="tbtd_content">
			   	 <input name="classPckage" readonly="readonly" id="classPckage" class="txt" value='<c:out value="${compnVO.classPckage}" escapeXml="false"/>' size="45"/>
			    </td>
			    <td class="tbtd_content">
			    <span class="btn_blue_l"><a href="javascript:fn_egov_delRow();">삭제</a></span>
				    <input name="useAt" type="hidden" id="useAt"<c:out value="${status.count}"/> value='<c:out value="${compnVO.useAt}" escapeXml="false"/>'  />
				    <input name="classId" type="hidden" id="classId" class="txt" value='<c:out value="${compnVO.classId}" escapeXml="false"/>' />
				</td>
			  </tr>  
			</c:if>  
		</table>
		</div>
		<!-- 목록 끝 -->

		<!-- 버튼 시작 -->
	    <div class="subbtn_align">          
	        <ul>
		    	<li class="btn02_leftbg"></li>
	            <li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_save(); return false;" class="btn_link">저장</a></li>
	            <li class="btn02_rightbg"></li>

		    	<li class="btn02_leftbg"></li>
	            <li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_delete(); return false;" class="btn_link">삭제</a></li>
	            <li class="btn02_rightbg"></li>

		    	<li class="btn02_leftbg"></li>
	            <li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_selectList(); return false;" class="btn_link">목록</a></li>
	            <li class="btn02_rightbg"></li>
	        </ul>
	    </div>
	    
		<!-- 버튼 끝 -->			
		</div>
	</form:form>	
	<!-- BODY 내용 END -->
	</div>		
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	<!-- 검색조건 유지 -->
	<input type="hidden" name="searchCondition" value="<c:out value='${compnVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${compnVO.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${compnVO.pageIndex}'/>"/>
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

