<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovReportrList.jsp
 * @Description : 보고자 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.09.03   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.09.03
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">
	function fnInit(){
		getDialogArguments();
		var opener = parent.window.dialogArguments;
		
		//document.frm.title.value = opener[1]; 
		parent.document.title = opener[1] + " 목록조회"; 
	}
	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_reportr('1');
		}
	}
	
	function fn_egov_select_reportr(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/mrm/selectReportrList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_reportr(uniqId, emplNo, emplyrNm, orgnztNm) {
		getDialogArguments();
		var opener = parent.window.dialogArguments;

		if(opener[2] != '' && opener[0].document.getElementById(opener[2]) != null){
			opener[0].document.getElementById(opener[2]).value = uniqId;
		}

		if(opener[3] != '' && opener[0].document.getElementById(opener[3]) != null){
			opener[0].document.getElementById(opener[3]).value = emplNo;
		}

		if(opener[4] != '' && opener[0].document.getElementById(opener[4]) != null){
			opener[0].document.getElementById(opener[4]).value = emplyrNm;
		}

		if(opener[5] != '' && opener[0].document.getElementById(opener[5]) != null){
			opener[0].document.getElementById(opener[5]).value = orgnztNm;
		}

		parent.window.returnValue = true;
		parent.window.close();
	}
</script>
<title>담당자 목록조회</title>
</head>
<body onLoad="fnInit()">

<div id="border" style="width:730px">

	<form name="frm" method="post" action="<c:url value='/cop/smt/mrm/selectReportrList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	
	<table width="100%" cellpadding="8" class="table-search" border="0">
	<tbody>
	 <tr>
	  <td width="25%" class="title_left">
	   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;보고대상자 선택</td>
		<td width="30%" >
			<label for="searchCnd">조회조건 : </label>
	   		<select name="searchCnd" class="select" title="조회조건 선택">
				<option value=''>--선택하세요--</option>
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >부서명</option>
				<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >사원명</option>
		   </select>
		</td>
	  <td width="35%">
	    <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력"> 
	  </td>
	  <th width="10%">
	   <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td><span class="button"><input type="submit" value="조회" onclick="fn_egov_select_reportr('1'); return false;"></span></td>
	    </tr>
	   </table>
	  </th>  
	 </tr>
	</tbody>
	</table>
	
	</form>
		
	<table width="100%" cellpadding="8" class="table-list" summary="이 표는 보고자정보를 제공하며, 부서, 직위, 사번, 사원명  정보로 구성되어 있습니다.">
	<caption>보고자목록</caption>
	 <thead>
	  <tr>
	    <th scope="col" class="title" width="10%" nowrap>번호</th>
	    <th scope="col" class="title" width="25%" nowrap>부서</th>
	    <th scope="col" class="title" width="15%" nowrap>직위</th>
	    <th scope="col" class="title" width="15%" nowrap>사번</th>
	    <th scope="col" class="title" width="20%" nowrap>사원명</th> 
	    <th scope="col" class="title" width="15%" nowrap>선택</th>      
	  </tr>
	 </thead>    
	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>		    
		    <td class="lt_text3" nowrap><c:out value="${result.orgnztNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.ofcpsNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.emplNo}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.emplyrNm}"/></td>
		    <td class="lt_text3" nowrap>
				<span class="link"><input type="submit" value="선택" onclick="javascript:fn_egov_inqire_reportr('<c:out value="${result.uniqId}"/>', '<c:out value="${result.emplNo}"/>', '<c:out value="${result.emplyrNm}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;"></span>
			</td>
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
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_reportr" />
	</div>
</div>

</body>
</html>
