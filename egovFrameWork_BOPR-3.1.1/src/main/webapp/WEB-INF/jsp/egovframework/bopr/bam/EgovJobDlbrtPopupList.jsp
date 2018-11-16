<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovJobDlbrtManage.jsp
  * @Description : 업무심의 완료된 것 팝업 조회
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.08.07 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.08.07
  *  @version 1.0 
  *  @see
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<title>업무심의 조회 팝업</title>

<script type="text/javaScript" language="javascript" defer="defer">

function fncSelectJobDlbrtList(pageNo){
	var searchCondition = document.listForm.searchCondition.value;
	var searchKeyword = document.listForm.searchKeyword.value;

	var flag = 0;
	document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "");
	if(searchCondition!="" && searchKeyword==""){
		alert("검색어를 입력하세요");
		flag = 1;
	}
	if(flag==0){
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:url value='/bopr/bam/EgovJobDlbrtPopupList.do'/>";
	    document.listForm.submit();
	}else{
		return;
	} 
}

function fncSelectJobDlbrt(jobDlbrt,jobDlbrtNm) {
    opener.document.batchDlbrtManage.jobDlbrtNo.value = jobDlbrt;
    opener.document.batchDlbrtManage.jobDlbrtNm.value = jobDlbrtNm;
    self.close();
}

function fncSelectJobDlbrtNm(jobDlbrt) {
    document.listForm.action = "<c:url value='/bopr/bam/EgovJobDlbrtPopupList.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/bam/EgovJobDlbrtPopupList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectJobDlbrtList('1');
    }
}

function fncChangeCondition(){
	if(document.listForm.searchCondition.value == ''){
		document.listForm.searchKeyword.value="검색조건을 선택하세요";
		document.listForm.searchKeyword.disabled="disabled";
	}else{
		document.listForm.searchKeyword.value="";
		document.listForm.searchKeyword.disabled="";
	}
}

function fncOnLoad(){
//	var message = document.listForm.message.value;
//	if(message!=null && message!=''){
//		alert(message);
//	}
}
</script>

</head>

<body onLoad="javascript:fncOnLoad();">
<form name="listForm" action="<c:url value='/bopr/bam/EgovJobDlbrtPopupList.do'/>" method="post">
<div class="popupBody">
	<h2>업무심의 팝업</h2>
	<div class="location">업무심의 팝업 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건 선택</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색조건 선택" onchange="javascript:fncChangeCondition();return false;">
				<option value="">검색조건선택</option>
				<option value="1">업무심의번호</option>    
				<option value="2">업무심의제목</option>       
			</select>
			<label for="searchKeyword" class="disp_none">검색어 입력</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력" type="text" 
				<c:if test="${jobDlbrtVO.searchKeyword ne ''}">value="<c:out value='${jobDlbrtVO.searchKeyword}'/>"</c:if>
				<c:if test="${jobDlbrtVO.searchKeyword eq '' && jobDlbrtVO.searchCondition eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if> 
			onkeyup="press();return false;" />                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectJobDlbrtList('1');return false;"/>
		</fieldset>
	</div>

	<div class="bbsList">
		<table  summary="업무심의번호, 업무구분, 업무심의제목 등의 업무심의 팝업목록입니다.">
		<caption>업무심의목록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:25%" >
			<col style="width:25%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">업무심의번호</th>
			<th scope="col">업무구분</th>
			<th scope="col">업무심의제목</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(jobDlbrtList) == 0}">
		<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="jobDlbrt" items="${jobDlbrtList}" varStatus="status">
		<tr>
		    <td><a href="javascript:fncSelectJobDlbrt('<c:out value="${jobDlbrt.jobDlbrtNo}"/>','<c:out value="${jobDlbrt.jobDlbrtNm}"/>')"><c:out value="${jobDlbrt.jobDlbrtNo}"/></a></td>
		    <td class="center"><c:out value="${jobDlbrt.jobSeCodeNm}"/></td>
		    <td><c:out value="${jobDlbrt.jobDlbrtNm}"/></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<div class="paging">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	<!-- //페이징 -->
	<input type="hidden" name="jobDlbrtNo"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${jobDlbrtVO.pageIndex}'/>"/>
	<!-- 메시지 -->
 	<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
</div>
</form>
</body>
</html>