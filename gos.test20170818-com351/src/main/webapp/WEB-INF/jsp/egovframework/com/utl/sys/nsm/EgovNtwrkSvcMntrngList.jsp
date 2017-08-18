<% 
/**
 * @Class Name : EgovNtwrkSvcMntrngList.jsp
 * @Description : 네트워크서비스모니터링 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.17   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.17
 *  @version 1.0 
 *  @see
 *  
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript">

	function fn_egov_init_ntwrksvcmntrng(){
		
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_ntwrksvcmntrng('1');
		}
	}
	
	function fn_egov_select_ntwrksvcmntrng(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>";
		document.frm.submit();	
	}

	function fn_egov_inqire_ntwrksvcmntrng(sysIp, sysPort) {
		document.frm.sysIp.value = sysIp;
		document.frm.sysPort.value = sysPort;
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrng.do'/>";
		document.frm.submit();	
	}

	function fn_egov_insert_ntwrksvcmntrng(){	
		document.frm.action = "<c:url value='/utl/sys/nsm/addNtwrkSvcMntrng.do'/>";
		document.frm.submit();
	}

	function fn_egov_log_ntwrksvcmntrng(){	
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>";
		document.frm.submit();
	}
</script>
<title>네트워크서비스모니터링 목록조회</title>
</head>
<body onLoad="fn_egov_init_ntwrksvcmntrng()">

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="border" style="width:730px">

	<form name="frm" method="post" action="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="sysIp">
	<input type="hidden" name="sysPort">
	
	<table width="100%" cellpadding="8" class="table-search" border="0">
	<tbody>
	 <tr>
	  <td width="40%" class="title_left">
	   <h1><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;네트워크서비스모니터링 목록</h1></td>
		<td width="25%" >
			<select name="searchCnd" class="select" title="조회조건 선택">
				<option value=''>--선택하세요--</option>
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >시스템명</option>
				<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >시스템IP</option>
				<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >관리자명</option>
				<option value="3" <c:if test="${searchVO.searchCnd == '3'}">selected="selected"</c:if> >상태</option>
		   </select>
		</td>
	  <td width="25%">
	    <input name="searchWrd" type="text" size="27" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력"> 
	  </td>
	  <th width="10%">
	   <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td><span class="button"><input type="submit" value="조회" onclick="fn_egov_select_ntwrksvcmntrng('1'); return false;"></span></td>
	      <td><span class="button"><a href="<c:url value='/utl/sys/nsm/addNtwrkSvcMntrng.do'/>" onclick="fn_egov_insert_ntwrksvcmntrng('1'); return false;"><spring:message code="button.create" /></a></span></td>
	      <td>&nbsp;</td>
	      <td><span class="button"><a href="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>" onclick="fn_egov_log_ntwrksvcmntrng(); return false;">로그</a></span></td>
	    </tr>
	   </table>
	  </th>  
	 </tr>
	</tbody>
	</table>
	
	</form>
	<table width="100%" cellpadding="8" class="table-list" summary="이 표는 네트워크서비스모니터링 대상 정보를 제공하며, 시스템IP, 시스템포트, 시스템명, 모니터링상태, 관리자명 정보로 구성되어 있습니다 .">
	<caption>네트워크서비스모니터링대상 목록</caption>
	 <thead>
	  <tr>
	    <th scope="col" class="title" width="10%" nowrap>번호</th>
	    <th scope="col" class="title" width="20%" nowrap>시스템IP</th>
	    <th scope="col" class="title" width="10%" nowrap>시스템포트</th>
	    <th scope="col" class="title" width="30%" nowrap>시스템명</th>
	    <th scope="col" class="title" width="15%" nowrap>모니터링상태</th>
	    <th scope="col" class="title" width="15%" nowrap>관리자명</th>
	  </tr>
	 </thead>    
	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	
		    <td class="lt_text3" nowrap><c:out value="${result.sysIp}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.sysPort}"/></td>
		    <td class="lt_text3" nowrap>
		     <form name="ntwrkSvcMntrngVO" method="post" action="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrng.do'/>">
		    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
		    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
				<input type="hidden" name="sysIp" value="<c:out value="${result.sysIp}"/>">
				<input type="hidden" name="sysPort" value="<c:out value="${result.sysPort}"/>">
				<span class="link"><input type="submit" value="<c:out value="${result.sysNm}"/>" onclick="javascript:fn_egov_inqire_ntwrksvcmntrng('<c:out value="${result.sysIp}"/>', '<c:out value="${result.sysPort}"/>'); return false;" style="text-align : left;"></span>
			 </form>
			</td>
			<td class="lt_text3" nowrap><c:out value="${result.mntrngSttus}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.mngrNm}"/></td>
		  </tr>
		 </c:forEach>	  
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td class="lt_text3" nowrap colspan="6"><spring:message code="common.nodata.msg" /></td>  
		  </tr>		 
		 </c:if>
	 </tbody>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_ntwrksvcmntrng" />
	</div>
</div>

</body>
</html>