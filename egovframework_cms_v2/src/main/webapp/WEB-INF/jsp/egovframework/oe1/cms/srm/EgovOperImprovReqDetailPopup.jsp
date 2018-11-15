<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
   * @JSP Name : EgovOperImprovReqDetailPopup.jsp
   * @Description : 운영개선요청 상세 화면 팝업
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

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/cms/srm/gnrl/selectOperImprovReqList.do'/>";
   	document.detailForm.submit();		
}

/* 수정 function */
function fn_egov_updt() {	
	document.detailForm.action = "<c:url value='/cms/srm/gnrl/updateOperImprovReqView.do' />";
	document.detailForm.submit();
    }

/* 삭제 function */
function fn_egov_delete(){
    if(confirm("삭제 하시겠습니까?")){
	   	document.detailForm.action = "<c:url value='/cms/srm/gnrl/deleteOperImprovReq.do'/>";
	   	document.detailForm.submit();	
    }
}
-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->
<div id="wrap">


<div id="content"><!-- BODY 내용 START -->

<form:form commandName="vo" name="detailForm" enctype="multipart/form-data" method="post" action="oe1/cms/srm/gnrl/selectOperImprovReqList.do" >
<div style="visibility:hidden;display:none;"><input name="iptSubmit+-" type="submit" value="전송" title="전송" /></div>
<input type="hidden" name="operImprvmRequstId"    value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="selectedId"    value="<c:out value='${vo.operImprvmRequstId }'/>"/>
<input type="hidden" name="posblAtchFileNumber"   value="10" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 운영개선요청 상세</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	<table summary="개선요청명,요청자,요청일,업무구분,완료요청일,요청내용,요청첨부파일,요청구분,긴급처리여부,담당자,접수일,처리완료일,처리내용,처리첨부파일,처리만족도,의견 상세입니다">
	<caption>운영개선요청 상세 테이블</caption>
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
  		
  		<c:if test="${vo.requstSttusCode == '06'}">
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
		<tr>
			<th scope="row">처리완료일</th>
			<td colspan="3">
				<c:out value="${vo.processComptDe}"/>	
			</td>	
		</tr>
		<tr>
			<th scope="row">처리내용</th>
			<td colspan="3" >
				${fn:replace(fn:escapeXml(vo.processCn),crlf,'<br/>')}
			</td>
		</tr>
  		<tr>
			<th scope="row" width="20%">첨부파일목록</th>
			<td colspan="3">
	  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
	    			<c:param name="param_atchFileId" value="${vo.processAtchFileId}" />
	    		</c:import> 
			</td>
  		</tr>	
  		</c:if>	
  		
		<c:if test="${vo.requstSttusCode == '06'}">
			<c:if test="${not empty vo.stsfdg && vo.stsfdg != ''}">	
					<tr>
						<th scope="row" rowspan="2">만족도</th>
						<th scope="row">처리만족도</th>
						<td colspan="3">
							<c:choose>
								<c:when test="${vo.stsfdg == '5'}">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/img_star_05.gif' />" alt="별점5" />
								</c:when>
								<c:when test="${vo.stsfdg == '4'}">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/img_star_04.gif' />" alt="별점4" />
								</c:when>
								<c:when test="${vo.stsfdg == '3'}">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/img_star_03.gif' />" alt="별점3" />
								</c:when>
								<c:when test="${vo.stsfdg == '2'}">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/img_star_02.gif' />" alt="별점2" />
								</c:when>
								<c:when test="${vo.stsfdg == '1'}">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/img_star_01.gif' />" alt="별점1" />
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>			
						</td>
					</tr>
					<tr>
						<th scope="row">의견</th>
						<td colspan="3" >
							${fn:replace(fn:escapeXml(vo.stsfdgOpinion),crlf,'<br/>')}
						</td>
					</tr> 				
			</c:if>	
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
 		<li class="btn02_leftbg"></li>
	    <li class="btn02_middlebg"><a href="#LINK" onclick="window.close();"  class="btn_link">닫기</a></li>
		<li class="btn02_rightbg"></li>
  		</ul>
  </div>   
 


</div>
</form:form>
<!-- BODY 내용 END --></div>


<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

