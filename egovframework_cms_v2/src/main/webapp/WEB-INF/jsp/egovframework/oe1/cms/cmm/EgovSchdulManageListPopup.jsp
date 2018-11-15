<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovSchdulManageListPopup.jsp
  * @Description : 부서일정관리 목록  팝업
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<c:set var="ImgUrl" value="/images/egovframework/cop/smt/sdm/"/>
<c:set var="CssUrl" value="/css/egovframework/cop/smt/sdm/"/>
<html>
<head>
<meta http-equiv="Content-language" content="ko">
<title>부서일정관리 목록  팝업</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="${CssUrl}com.css" />
<base target="_self" />
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cop/smt/sdm/EgovOe1DeptSchdulManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_egov_regist_DeptSchdulManage(){
	location.href = "/cop/smt/sdm/EgovOe1DeptSchdulManageRegist.do";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_DeptSchdulManage(){
	location.href = "/cop/smt/sdm/EgovOe1DeptSchdulManageModify.do";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_DeptSchdulManage(schdulId){
	var vFrom = document.listForm;
	vFrom.schdulId.value = schdulId;
	vFrom.action = "/cop/smt/sdm/EgovOe1DeptSchdulManageDetail.do";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_DeptSchdulManage(){
	var vFrom = document.listForm;
	
	if(vFrom.searchKeyword.value == ""){
		alert('검색어를 입력해주세요!');
		vFrom.searchKeyword.focus();
		return;
	}
	
	if(vFrom.searchCondition.selectedIndex == 0){
		alert('검색 구분을 선택해주세요!');
		vFrom.searchCondition.focus();
		return;
	} 

	vFrom.action = "/cop/smt/sdm/EgovOe1DeptSchdulManageList.do";
	vFrom.submit();
	
}

/* ********************************************************
* 선택 처리 함수
******************************************************** */
function fn_egov_open_Popup(cnt, schdulId){
	
	var opener = window.dialogArguments;

	opener.document.getElementById("schdulId").value = schdulId;
	opener.document.getElementById("schdulCn").value = document.getElementById("iptText_"+ cnt).value;

	window.returnValue=true;
	window.close();
}
</script>

</head>
<body>
<DIV id="content" style="width:712px">
<form name="listForm" id="listForm" action="<c:url value=''/>" method="post">
<table width="100%" cellpadding="1" class="table-search" border="0">
 <tr>
  <td class="title_left">

   <img src="<c:out value="${ImgUrl}"/>icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle">&nbsp;부서일정관리 목록</td>
  <th scope="row">
  </th>
  <td width="110px">
   	<select name="searchCondition" class="select" style="width:100%">
		   <option value=''>--선택하세요--</option>
		   <option value='QESTNR_SJ' <c:if test="${searchCondition == 'QESTNR_SJ'}">selected</c:if>>설문제목</option>
		   <option value='FRST_REGISTER_ID' <c:if test="${searchCondition == 'FRST_REGISTER_ID'}">selected</c:if>>등록자</option>
	   </select>
	</td>
  <td width="180px">
    <input name="searchKeyword" type="text" size="10" value="${searchKeyword}" maxlength="35" style="width:100%"> 
  </td>
  <th width="30px" align="center">
   <table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr> 
      <td><img src="/images/egovframework/uss/olp/mgt/button/bu2_left.gif" width="8" height="20"></td>
      <td background="<c:out value="${ImgUrl}"/>button/bu2_bg.gif" class="text_left" nowrap><a href="#" onClick="JavaScript:fn_egov_search_DeptSchdulManage()"><spring:message code="button.inquire" /></a> 
      </td>
      <td><img src="<c:out value="${ImgUrl}"/>button/bu2_right.gif" width="8" height="20"></td>
    </tr>
   </table>
  </th>  
 </tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>

<table  summary="순번, 일정구분, 일지명   목록입니다"  width="100%" cellpadding="0" class="table-line" border="0">
<thead>
<tr>  
    <th class="title" width="35px" nowrap>순번</th>
    <th class="title" width="100px" nowrap>일정구분</th>
    <th class="title" nowrap>일지명</th> 
    <th class="title" width="30px" nowrap></th>         
</tr>
</thead>
<tbody>
<%-- 데이터를 화면에 출력해준다 --%>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
  <tr>
		<td class="lt_text3"><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>
		<td class="lt_text3">
		 <c:if test="${resultInfo.schdulKindCode == '1'}">부서일정</c:if>
		 <c:if test="${resultInfo.schdulKindCode == '2'}">개인일정</c:if>
		</td>
		<td class="lt_text3">
		${resultInfo.schdulNm}
		</td>
    	<td class="lt_text3L">
    		<a href="#" onClick="JavaScript:fn_egov_open_Popup('${status.count}', '${resultInfo.schdulId}')">선택</a>
    		<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.schdulNm}">
    	</td>
  </tr>   
</c:forEach>
</tbody>  
</table>

<table width="100%" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>

<div align="center">
	<div>
		<ui:pagination paginationInfo = "${paginationInfo}"
				type="image"
				jsFunction="linkPage"
				/>
	</div>
</div>
<input name="schdulId" id="schdulId" type="hidden" value="">
<input name="pageIndex" id="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>

<!-- 
<c:set var="today" value="< %=new java.util.Date()%>"/>
<fmt:formatDate value="${today}" type="DATE" pattern="yyyy년 MM월 dd일(E) a KK시 mm분 ss초" />

<fmt:parseDate value="${todayS}" var="today" pattern="yyyymmdd"/>

${parseDate}
// -->

</DIV>
</body>
</html>