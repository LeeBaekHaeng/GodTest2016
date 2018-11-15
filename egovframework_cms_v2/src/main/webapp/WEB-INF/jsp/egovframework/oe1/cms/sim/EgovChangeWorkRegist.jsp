<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeWorkRegist.jsp
  * @Description : 변경작업 등록 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.12  김아름          최초 생성
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
<title>변경작업등록</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
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
<validator:javascript formName="changeWorkVO" staticJavascript="false" xhtml="true" cdata="true"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* 저장 function */
function fn_egov_regist_change_work(flag){
	if(document.changeWorkPlanVO.realOpertBeginDe.value > document.changeWorkPlanVO.realOpertEndDe.value)
		alert("실제작업 시작일과 종료일을 확인하세요");
	else{
		if(flag==1){ //저장
			if(validateChangeWorkVO(document.changeWorkPlanVO)){
		     	 if(confirm('<spring:message code="common.save.msg" />')){
					document.changeWorkPlanVO.action = "<c:url value='/cms/sim/chrg/addChangeWork.do'/>";
					document.changeWorkPlanVO.submit();	
		      	}
		      }
		}else if(flag ==2){ //검토요청
			if(validateChangeWorkVO(document.changeWorkPlanVO)){
				if(confirm("완료검토 요청 후에는 수정할 수 없습니다. 관리자에게 완료검토를 요청 하시겠습니까?")){
				document.changeWorkPlanVO.action = "<c:url value='/cms/sim/chrg/selectChangeWorkComptConfm.do'/>";
				document.changeWorkPlanVO.submit();
				}
			}
		}else if(flag==3){ //변경완료
			if(validateChangeWorkVO(document.changeWorkPlanVO)){
				if(confirm("담당자 권한위임건입니다. 작업완료를 하시겠습니까?")){
				document.changeWorkPlanVO.action = "<c:url value='/cms/sim/chrg/confmChangeWorkBySelf.do'/>";
				document.changeWorkPlanVO.submit();
				}
			}
		}
	}
}

/* 목록 function */
function fn_egov_back(){
	document.changeWorkPlanVO.pageIndex.value=1;

	if(document.changeWorkPlanVO.searchSttusCode.value =="true")
		;
	else
		document.changeWorkPlanVO.changeProcessSttusCode.value = "";

	document.changeWorkPlanVO.action = "<c:url value='/cms/sim/chrg/selectChangeWorkPlanList.do'/>";
	document.changeWorkPlanVO.submit();
}

/* 초기화 function 
- 파일첨부 삭제시 리턴되는 페이지가 분기해주는 컨트롤러라서 code와 id항목이 필요 
*/
function init(){
	document.changeWorkPlanVO.code.value = document.changeWorkPlanVO.changeProcessSttusCode.value;
	document.changeWorkPlanVO.id.value =document.changeWorkPlanVO.changeRequstProcessId.value;

	if(document.changeWorkPlanVO.opertComptAt.value =='U')
		document.changeWorkPlanVO.unsolvCn.disabled = false;
	else{
		document.changeWorkPlanVO.unsolvCn.disabled = true;
		document.changeWorkPlanVO.unsolvCn.value = "";
	}
	
	//첨부파일 처리 
	var maxFileNum = 10;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}

/* 미해결여부체크 function */
function checkUnsolv(){
	if(document.changeWorkPlanVO.opertComptAt.value =='U')
		document.changeWorkPlanVO.unsolvCn.disabled = false;
	else{
		document.changeWorkPlanVO.unsolvCn.disabled = true;
		document.changeWorkPlanVO.unsolvCn.value = "";
	}
}

//-->
</script>
</head>
<body onload="init();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<div id="wrap">
<!-- 전체 레이어 시작 -->
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

<form:form commandName="changeWorkPlanVO" name="changeWorkPlanVO" action="/oe1/cms/sim/chrg/selectChangeWorkPlanList.do" enctype="multipart/form-data"  method="post">
<form:hidden path="changeRequstProcessId"/>
<input type="hidden" name="posblAtchFileNumber" value="10" />
<input name="exmntResultCode"  type="hidden" value="${changeWorkPlan3.comptExmntResultCode}"/>
<input type="hidden" name="returnUrl" value="/oe1/cms/sim/chrg/branchChangeWorkPlan.do" />
<c:if test="${changeWorkPlan.atchFileId == '' }"><input type="hidden" name="fileListCnt" value="0" /></c:if>
<input name="changeRequstId"  type="hidden"/>
<input name="s_mberId"  type="hidden" value="${s_mberId }"/>
<input name="code" type="hidden"/>
<input name="id" type="hidden"/>
<input name="requstSysBasisId"  type="hidden" value="${changeWorkPlan.requstSysBasisId}"/> 
<input name="changeRequstSysCode"  type="hidden" value="${changeWorkPlan.changeRequstSysCode}"/>
<input name="searchSttusCode" type="hidden" value="${searchVO.searchSttusCode}"> 

<div id="content_pop">
<div id="h2_topnav"><h1><strong> 변경작업 등록</strong></h1></div>
	
<div id="datail_table2">
<table  summary="변경작업 항목을 등록합니다.">
<caption>변경작업 등록</caption>
	<tr>
	    <th style="width:15%" scope="row" >변경요청명</th>
	    <td  colspan="3" ><c:out value="${changeWorkPlan.changeRequstNm}"/> </td>
	</tr>
	<tr>
	    <th  scope="row">처리상태</th>
	    <td ><c:out value="${changeWorkPlan.changeProcessSttusCode}"/></td>
	    <th scope="row">변경구분</th>
	    <td ><c:out value="${changeWorkPlan.changeSeCode}"/></td>
	</tr>
	<tr>
	    <th   scope="row">담당자권한위임</th>
	    <td colspan="3">
	      		<input type="checkbox" name="planT"  <c:if test="${changeWorkPlan.planExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 계획검토
	      		<input type="checkbox" name="comptT"  <c:if test="${changeWorkPlan.comptExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 변경완료검토
	    </td>
	</tr>
	<tr>
		<th   scope="row">계획작업기간 </th>
		<td colspan="3"><c:out value="${changeWorkPlan.planBeginDe}"/> ~ <c:out value="${changeWorkPlan.planEndDe}"/></td>
	</tr>
	<tr>
    	<th   scope="row">작업계획    </th>
    	<td  colspan="3">   	${fn:replace(fn:escapeXml(changeWorkPlan.opertPlanCn),crlf,"<br/>")}    </td>
	</tr>
	<tr>
    	<th   scope="row"><label for="realOpertBeginDe"><span class="th_add">실제작업기간</span></label>   </th>
		<td colspan="3" >
			<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
			<input name="realOpertBeginDe"  id="realOpertBeginDe" value="${changeWorkPlan.realOpertBeginDe}" size="10" maxlength="10"  class="inputsmall01" style="width:20%" title="실제작업 시작일" tabindex="1" />
            <img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.changeWorkPlanVO, document.changeWorkPlanVO.realOpertBeginDe, document.changeWorkPlanVO.realOpertBeginDe); return false;" onkeypress="javascript:fn_egov_NormalCalendar(document.changeWorkPlanVO, document.changeWorkPlanVO.realOpertBeginDe, document.changeWorkPlanVO.realOpertBeginDe); return false;"/>
			~		
			<input name="realOpertEndDe"  id="realOpertEndDe" value="${changeWorkPlan.realOpertEndDe}"  size="10" maxlength="10"   class="inputsmall01" style="width:20%" title="실제작업 종료일" tabindex="2"/>
            <img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.changeWorkPlanVO, document.changeWorkPlanVO.realOpertEndDe, document.changeWorkPlanVO.realOpertEndDe); return false; return false;" onkeypress="javascript:fn_egov_NormalCalendar(document.changeWorkPlanVO, document.changeWorkPlanVO.realOpertEndDe, document.changeWorkPlanVO.realOpertEndDe);"/>
		</td>
	</tr>
    <tr>
	    <th   scope="row"><label for="changeOpertCn"><span class="th_add">작업내용</span></label>   </th>
	    <td  colspan="3"><textarea name="changeOpertCn"  id="changeOpertCn" rows="5" cols="10" style="width:600px;"  class="textarea" title="작업내용" tabindex="3"><c:out value="${changeWorkPlan.changeOpertCn}" /></textarea>   </td>
	</tr>
	<tr>
	    <th   scope="row"><label for="unsolvCn">미해결내용</label>   </th>
	    <td  colspan="3">	    <textarea  name="unsolvCn"  id="unsolvCn" rows="5" cols="10" style="width:600px;" class="textarea" title="미해결내용" tabindex="4"><c:out value="${changeWorkPlan.unsolvCn}" /></textarea>	    </td>
	</tr>
	<tr>
	    <th   scope="row"><label for="opertComptAt"><span class="th_add">작업완료</span></label>   </th>
	    <td  colspan="3">
			<select name="opertComptAt" id="opertComptAt" style="width:110px" onchange="checkUnsolv();" title="작업완료" tabindex="5">
			<option value="">--선택하세요--</option>
			<c:forEach items="${opertComptAt_result}" var="codeInfo" varStatus="status">
			<option value="${codeInfo.code}" <c:if test="${changeWorkPlan.opertComptAt eq codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
			</c:forEach>
			</select>
	    </td>
	</tr>  
	<tr>
		<th  id="file_upload_posbl"  scope="row"><label for="egovComFileUploader">산출물첨부</label></th>
		<td colspan="3" ><input type="file"  id="egovComFileUploader" onkeydown="event.returnValue=false;"  title="산출물첨부" tabindex="6"/></td>
	</tr>
	<tr> 
   		<th rowspan="2"  scope="row">산출물파일목록</th>
		<td colspan="3">
			<c:if test="${not empty changeWorkPlan.atchFileId}">
			<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
		  	<c:param name="param_atchFileId" value="${changeWorkPlan.atchFileId}" />
			</c:import>
	  		</c:if>	
   		</td>
	</tr>	
	<tr> 
		<td  colspan="3" id="egovComFileList"></td>
	</tr> 
<!-- 계획검토결과코드가 재계획인 경우 아래 출력  -->
	<c:if test="${changeWorkPlan3.comptExmntResultCode eq 'R' }"> 
    <tr>
	    <th  scope="row">완료검토여부</th>
	    <td >
			<c:forEach items="${comptExmntResultCode_result}" var="codeInfo" varStatus="status">
			<c:if test="${changeWorkPlan3.comptExmntResultCode eq codeInfo.code}">
			<font color="red"><c:out value="${codeInfo.codeNm}"/> </font>
			</c:if>
			</c:forEach>
		</td>
		<th  scope="row">완료검토일</th>
		<td >   <font color="red"> <c:out value="${changeWorkPlan3.comptExmntDt }"/></font>  </td>
	</tr>
	<tr>
	    <th   scope="row">완료검토내용    </th>
	    <td colspan="3" >      <font color="red"><c:out value="${changeWorkPlan3.comptExmntCn}"/></font>    </td>
	</tr>
	</c:if>
<!-- 변경작업계획 끝 -->
</table>
</div>
</div>
	
<!-- 버튼-->
<div class="subbtn_align">  	
<ul >
	<c:if test="${changeWorkPlan.changeProcessSttusCode eq '작업' and changeWorkPlan.comptExmntMndtAt eq 'N'  and fn:length(fn:trim(changeWorkPlan.changeOpertCn)) > 0}"> 
		<li class="btn02_leftbg"></li>
	    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_regist_change_work(2); return false;" class="btn_link" tabindex="7">완료검토요청</a></li>
	    <li class="btn02_rightbg"></li>
	</c:if>
 	<c:if test="${changeWorkPlan.comptExmntMndtAt eq 'Y' and fn:length(fn:trim(changeWorkPlan.changeOpertCn)) > 0}"> 
		<li class="btn02_leftbg"></li>
	    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_regist_change_work(3); return false;" class="btn_link" tabindex="8">변경완료</a></li>
	    <li class="btn02_rightbg"></li>
	</c:if>
	<c:if test="${changeWorkPlan.changeProcessSttusCode eq '작업'}"> 
		<li class="btn02_leftbg"></li>
	    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_regist_change_work(1); return false;" class="btn_link" tabindex="9">저장</a></li>
	    <li class="btn02_rightbg"></li>
	</c:if>		
		<li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"   onclick="fn_egov_back(); return false;" tabindex="10"/></span></li>
		<li class="submit_btn01_right"></li>
</ul>
</div>

<!-- 검색조건유지 데이터 -->
<input name="changeProcessSttusCode" type="hidden" value="${searchVO.changeProcessSttusCode}"/> 
<input name="emrgncyProcessAt" value="${searchVO.emrgncyProcessAt}" type="hidden"/> 
<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
<input name="fromDate" type="hidden" value="<c:out value='${searchVO.fromDate}'/>"/>
<input name="toDate" type="hidden" value="<c:out value='${searchVO.toDate}'/>"/> 
</form:form>
</div>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>