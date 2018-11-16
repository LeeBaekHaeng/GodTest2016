<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovAuthorManage.java
  * @Description : EgovAuthorManage List 화면
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
<title>권한목록팝업</title>

<script type="text/javaScript" language="javascript" defer="defer">

function fncSelectRole(returnRole){
	parent.window.returnValue = returnRole;
    window.close();
}

</script>


</head>

<body>

<form:form name="listForm" action="${pageContext.request.contextPath}/sec/ram/EgovAuthorList.do" method="post">

<div class="contsBody">

	<div class="bbsList">
		<table  summary="권한ID, 권한명, 설명의 순서로 보여지는 권한 검색입니다.">
		<caption>권한 검색</caption>
		<colgroup>
			<col style="width:40%" >
			<col style="width:30%" >
			<col style="width:30%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">권한ID</th>
			<th scope="col">권한명</th>
			<th scope="col">설명</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="author" items="${authorList}" varStatus="status">
		<tr>
			<td><a href="javascript:fncSelectRole('<c:out value="${author.authorCode}"/>')"><c:out value="${author.authorCode}"/></a></td>
			<td><c:out value="${author.authorNm}"/></td>
			<td><c:out value="${author.authorDc}"/></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty authorManageVO.pageIndex }">
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>

	
	<!-- Hidden 값 -->
	<input type="hidden" name="authorCode"/>
	<input type="hidden" name="authorCodes"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
	<input type="hidden" name="searchCondition"/>
</form:form>


<%-- <DIV id="main" style="display:">

<table border="0">
  <tr>
    <td width="700">

<form:form name="listForm" action="${pageContext.request.contextPath}/sec/ram/EgovAuthorList.do" method="post">
<table width="100%" cellpadding="8" class="table-line" summary="권한관리에  관한 테이블입니다.권한ID,권한 명,설명, 등록일자, 롤 정보의 내용을 담고 있습니다.">
 <thead>
  <tr>
    <th class="title" width="15%" scope="col" nowrap="nowrap">권한 ID</th>
    <th class="title" width="25%" scope="col" nowrap="nowrap">권한 명</th>
    <th class="title" width="40%" scope="col" nowrap="nowrap">설명</th>
    <th class="title" width="15%" scope="col" nowrap="nowrap">등록일자</th>
    <th class="title" width="5%" scope="col" nowrap="nowrap">롤 정보</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach var="author" items="${authorList}" varStatus="status">
  <tr>
    <td class="lt_text" nowrap="nowrap"><a href="javascript:fncSelectRole('<c:out value="${author.authorCode}"/>')"><c:out value="${author.authorCode}"/></a></td>
    <td class="lt_text" nowrap="nowrap"><c:out value="${author.authorNm}"/></td>
    <td class="lt_text3" nowrap="nowrap"><c:out value="${author.authorDc}"/></td>
    <td class="lt_text3" nowrap="nowrap"><c:out value="${author.authorCreatDe}"/></td>
    <td class="lt_text3" nowrap="nowrap"><a href="#LINK" onclick="javascript:fncSelectRole('<c:out value="${author.authorCode}"/>')"><img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif'/>" width="15" height="15" align="middle" alt="롤 정보"></a></td>

  </tr>
 </c:forEach>
 </tbody>

 <!--tfoot>
  <tr class="">
   <td colspan=6 align="center"></td>
  </tr>
 </tfoot -->
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10"></td>
  </tr>
</table>

<c:if test="${!empty authorManageVO.pageIndex }">
<div align="center">
    <div>
        <ui:pagination paginationInfo = "${paginationInfo}"
            type="default"
            jsFunction="linkPage"
            />
    </div>
    <div align="right">
        <input type="text" name="message" value="<c:out value='${message}'/>" size="30" readonly="readonly" title="메시지"/>
    </div>
</div>
</c:if>
<input type="hidden" name="authorCode"/>
<input type="hidden" name="authorCodes"/>
<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form:form>
</td>
</tr>
</table>
</DIV> --%>
</body>
</html>
