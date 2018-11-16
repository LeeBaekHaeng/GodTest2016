<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovUserManagePopUp.jsp
  * @Description : EgovUser List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자                    수정내용
  * @ -------       --------    ---------------------------
  * @ 2009.03.01    Lee.m.j       최초 생성
  *
  *  @author 실행환경 개발팀 홍길동
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<title>사용자목록팝업</title>

<script type="text/javaScript" language="javascript" defer="defer">

function fncSelectUserList(pageNo){

    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/uam/EgovUserManagePopUp.do'/>";
    document.listForm.submit();
}

function fncSelectUser(userId, userNm, moblPhon, email){
	
	// var opener = parent.window.dialogArguments;
	
	if (opener.document.getElementById("userId") != null)
	{
		opener.document.getElementById("userId").value = userId;
	}
	
	if (opener.document.getElementById("userNm") != null)
	{
		opener.document.getElementById("userNm").value = userNm;	
	}
	
	if (opener.document.getElementById("moblPhon") != null)
	{
		opener.document.getElementById("moblPhon").value = moblPhon;	
	}
	
	if (opener.document.getElementById("email") != null)
	{
		opener.document.getElementById("email").value = email;	
	}
	
	opener.fncUserManagePopupCallBack();
	
    self.close();
}

function fncChangeCondition(){
	if(document.listForm.searchCondition.value == ''){
		document.listForm.searchKeyword.value="검색시 전체조회가 수행됩니다.";
		document.listForm.searchKeyword.disabled="disabled";
	}else{
		document.listForm.searchKeyword.value="";
		document.listForm.searchKeyword.disabled="";
	}
}
function fncPress(){
	if (event.keyCode==13) {
		fncSearchList();
	}
}
function fncSearchList(){
	if(!document.listForm.searchKeyword.disabled){
		if(document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "") != ""){
			fncSelectUserList('1');	
		}else{
			window.alert("검색어를 입력하세요");
			document.listForm.searchKeyword.select();
		}
	}else{
		fncSelectUserList('1');
	}
	
}

</script>

</head>

<body>
<form:form name="listForm" method="post" onkeypress="if(event.keyCode==13) return false;">

<div class="popupBody">
	<h2>사용자 목록 팝업</h2>
	<div class="location">사용자 목록 > <strong>팝업</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
	<fieldset class="searchboxA">
		<legend>검색 영역</legend>
		<label for="searchCondition" class="disp_none">검색조건</label>
		<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색어 선택" onchange="javascript:fncChangeCondition();">
			<option value="" >검색조건선택</option>
			<option value="1" <c:if test="${userManageVO.searchCondition == '1'}">selected</c:if> >사용자ID</option>
			<option value="2" <c:if test="${userManageVO.searchCondition == '2'}">selected</c:if> >사용자명</option>              
		</select>
		<label for="searchKeyword" class="disp_none">검색어</label>
		<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();"
			<c:if test="${userManageVO.searchKeyword ne ''}">value="<c:out value='${userManageVO.searchKeyword}'/>"</c:if>
			<c:if test="${userManageVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
		/>                  
		<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
	</fieldset>
	</div>
	<!-- //검색영역 -->
	
	<div class="bbsList">
		<table  summary="사용자ID, 사용자명, 전화번호, 이메일 순으로 보여지는 사용자 검색입니다.">
		<caption>사용자 검색</caption>
		<colgroup>
			<col style="width:25%" >
			<col style="width:25%" >
			<col style="width:25%" >
			<col style="width:25%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">사용자ID</th>
			<th scope="col">사용자명</th>
			<th scope="col">전화번호</th>
			<th scope="col">이메일</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${userList}" varStatus="status">
		<tr>
			<td><a href="javascript:fncSelectUser('<c:out value="${user.userId}"/>', '<c:out value="${user.userNm}"/>', '<c:out value="${user.moblphonNo}"/>', '<c:out value="${user.emails}"/>')"><c:out value="${user.userId}"/></a></td>
			<td><c:out value="${user.userNm}"/></td>
			<td><c:out value="${user.moblphonNo}"/></td>
			<td><c:out value="${user.emails}"/></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty userManageVO.pageIndex }">
	<div class="paging">
		<!-- 적용불가 -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>

	
	<!-- Hidden값 -->
	<input type="hidden" name="userId"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${userManageVO.pageIndex}'/>"/>
</form:form>
</body>
</html>
