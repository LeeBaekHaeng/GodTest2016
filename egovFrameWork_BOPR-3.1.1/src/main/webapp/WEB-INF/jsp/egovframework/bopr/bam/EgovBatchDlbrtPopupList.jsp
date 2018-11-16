<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBatchDlbrtPopupList.jsp
  * @Description : 배치 배포 완료된 것 팝업 조회
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.08.20 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.08.20
  *  @version 1.0 
  *  @see
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<title>배포배치 조회 팝업</title>

<script type="text/javaScript" language="javascript" defer="defer">

function fncSelectBatchDlbrtList(pageNo){
	var searchCondition = document.listForm.searchCondition.value;
	var searchKeyword = document.listForm.searchKeyword.value;

	var flag = 0;
	document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "");
	if(searchCondition!="" && searchKeyword==""){
		alert("검색어를 입력하세요");
		flag = 1;
	}else if(searchKeywordFrom!="" && searchKeywordTo!=""){
		if(searchKeywordFrom>searchKeywordTo){
			alert("검색시작일이 검색종료일보다 클 수 없습니다.");
			flag = 2;
		}
	}
	if(flag==0){
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtPopupList.do'/>";
	    document.listForm.submit();
	}else{
		return;
	}  
}

function fncSelectBatchDlbrt(batchDlbrt,batchDlbrtNm) {
    opener.document.batchDlbrtManage.batchId.value = batchDlbrt;
    opener.document.batchDlbrtManage.batchNm.value = batchDlbrtNm;
    self.close();
}

function fncSelectBatchDlbrtNm(batchDlbrt) {
    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtPopupList.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtPopupList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectBatchDlbrtList('1');
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
<form name="listForm" action="<c:url value='/bopr/bam/EgovBatchDlbrtPopupList.do'/>" method="post">
<div class="popupBody">
	<h2>배포완료 배치심의 팝업</h2>
	<div class="location">배포완료 배치심의 팝업 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건 선택</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색조건 선택" onchange="javascript:fncChangeCondition();return false;">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${batchDlbrtVO.searchCondition == '1'}"> selected </c:if>>배치ID</option>    
				<option value="2" <c:if test="${batchDlbrtVO.searchCondition == '2'}"> selected </c:if>>배치명</option>       
			</select>
			<label for="searchKeyword" class="disp_none">검색어 입력</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력" type="text" 
				<c:if test="${batchDlbrtVO.searchKeyword ne ''}">value="<c:out value='${batchDlbrtVO.searchKeyword}'/>"</c:if>
				<c:if test="${batchDlbrtVO.searchKeyword eq '' && batchDlbrtVO.searchCondition eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			 onkeyup="press();return false;" />                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectBatchDlbrtList('1');return false;"/>
		</fieldset>
	</div>

	<div class="bbsList">
		<table  summary="배치ID, 업무심의명, 배치명 등의 업무심의 팝업목록입니다.">
		<caption>업무심의목록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:25%" >
			<col style="width:25%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">배치ID</th>
			<th scope="col">업무심의명</th>
			<th scope="col">배치명</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(batchDlbrtList) == 0}">
		<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="batchDlbrt" items="${batchDlbrtList}" varStatus="status">
		<tr>
		    <td><a href="javascript:fncSelectBatchDlbrt('<c:out value="${batchDlbrt.batchId}"/>','<c:out value="${batchDlbrt.batchNm}"/>')"><c:out value="${batchDlbrt.batchId}"/></a></td>
		    <td><c:out value="${batchDlbrt.jobDlbrtNm}"/></td>
		    <td><c:out value="${batchDlbrt.batchNm}"/></td>
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
	<% String jobDlbrtNo = request.getParameter("jobDlbrtNo"); %>
	<input type="hidden" name="jobDlbrtNo" value=<%=jobDlbrtNo %> />
	<input type="hidden" name="batchDlbrtNo"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${batchDlbrtVO.pageIndex}'/>"/>
	<!-- 메시지 -->
 	<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
</div>
</form>
</body>
</html>