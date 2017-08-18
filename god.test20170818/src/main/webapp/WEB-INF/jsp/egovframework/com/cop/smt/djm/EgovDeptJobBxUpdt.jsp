<% 
/**
 * @Class Name : EgovDeptJobBxUpdt.jsp
 * @Description : 부서업무함 수정
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.07   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.07
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/"/>
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/egovframework/com/cop/smt/djm/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="deptJobBxVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_modify_deptjobbx() {
		if (!validateDeptJobBxVO(document.deptJobBxVO)){
			return;
		}

		var intIndictOrdr = eval(document.deptJobBxVO.indictOrdr.value);
		var deptIndictOrdr = eval(document.deptJobBxVO.deptIndictOrdr.value);
		 
		if(intIndictOrdr < 1){
			alert("표시순서의 값을 1 이상으로 입력해야 합니다.");
			return;
		}

		if(deptIndictOrdr < intIndictOrdr){
			alert("해당 부서의 부서업무함 표시순서의 최대 값은 " +  (deptIndictOrdr -1) + "이므로 " + deptIndictOrdr + "이하로 입력해야 합니다.");
			return;
		}
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/updateDeptJobBx.do'/>";
			document.deptJobBxVO.submit();					
		}
	}

	function fn_egov_delete_deptjobbx() {
		if (confirm('<spring:message code="common.delete.msg" />')) {
			document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/deleteDeptJobBx.do'/>";
			document.deptJobBxVO.submit();					
		}
	}
	
	function fn_egov_list_deptjobbx() {
		if(document.deptJobBxVO.indictOrdr.value == ""){
			document.deptJobBxVO.indictOrdr.value = "0";
		}
		document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>";
		document.deptJobBxVO.submit();					
	}	

	/* ********************************************************
	* 부서  팝업창열기
	******************************************************** */
	function fn_egov_dept_DeptJobBx(strType){
		var arrParam = new Array(1);
		arrParam[0] = window;
		arrParam[1] = strType;

		window.showModalDialog("<c:url value='/cop/smt/djm/selectDeptListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");

		fn_egov_indictOrdr_DeptJobBx();
	}

	/* ********************************************************
	* 표시순서가져오기
	******************************************************** */
	function fn_egov_indictOrdr_DeptJobBx(){
		if(document.deptJobBxVO.indictOrdr.value == ""){
			document.deptJobBxVO.indictOrdr.value = "0";
		}
		document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/getDeptJobBxOrdr.do'/>";
		document.deptJobBxVO.submit();
	}

</script>
<title>부서업무함수정</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="border" style="width:730px">

<form:form commandName="deptJobBxVO" name="deptJobBxVO" method="post" action="${pageContext.request.contextPath}/cop/smt/djm/updateDeptJobBx.do' />">

	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="100%"class="title_left">
	   <h1><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">
	   &nbsp;부서업무함 수정</h1></td>
	 </tr>
	</table>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register"  summary="이 표는 부서업무함 정보를  수정하기 위한 표이며, 부서, 부서업무함명, 표시순서 정보로 구성되어 있습니다 .">
	<caption>부서업무함 수정</caption>
	<tbody>
	  <tr> 
		<th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="deptNm">부서</label><img src="${ImgUrl}icon/required.gif" width="15" height="15" alt="필수항목"></th>
		<td scope="row" width="80%" >
			<table cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="120px" style="padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;">
				<form:input path="deptNm" size="20" readonly="true" maxlength="20" title="부서"/>
				</td>
				<td style="padding:0px 0px 0px 2px;margin:0px 0px 0px 0px;">
				<a href="<c:url value='/cop/smt/djm/selectDeptListPopup.do' />" target="_blank" title="새 창으로 이동" onClick="fn_egov_dept_DeptJobBx('typeDept');return false;">
				<img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />" style="border:0px" alt="부서" title="부서">
				</a>
				</td>
			</tr>
			</table>
			<div><form:errors path="deptNm" cssClass="error"/></div>
		   <form:hidden path="deptId" />
				  
		</td>
	  </tr>
	  <tr> 
		<th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="deptJobBxNm">부서업무함명</label><img src="${ImgUrl}icon/required.gif" width="15" height="15" alt="필수항목"></th>
		<td width="80%" >
		  <form:input path="deptJobBxNm" size="50" maxlength="255" title="부서업무함명"/>
		  <div><form:errors path="deptJobBxNm" cssClass="error"/></div>
		</td>
	  </tr>
	  <tr> 
	    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="indictOrdr">표시순서</label><img src="${ImgUrl}icon/required.gif" width="15" height="15" alt="필수항목"></th>
	    <td width="80%" >
	      <input type="text" name="indictOrdr" id="indictOrdr" size="3" maxlength="6" title="표시순서" onchange="fn_egov_indictOrdr_DeptJobBx()"  value="<c:out value='${deptJobBxVO.indictOrdr}'/>">
	      <div><form:errors path="indictOrdr" cssClass="error"/></div>
	    </td>
	  </tr>
	</tbody>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td height="10"></td>
	  </tr>
	</table>
  	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	  <td><span class="button"><input type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_deptjobbx(); return false;"></span></td>
      <td>&nbsp;&nbsp;</td>
      <td><span class="button"><a href="<c:url value='/cop/smt/djm/deleteDeptJobBx.do'/>?deptJobBxId=<c:out value='${deptJobBxVO.deptJobBxId}'/>" onclick="fn_egov_delete_deptjobbx(); return false;"><spring:message code="button.delete" /></a></span></td>
      <td>&nbsp;&nbsp;</td>
      <td><span class="button"><a href="<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>?searchWrd=<c:out value='${deptJobBxVO.searchWrd}'/>&amp;searchCnd=<c:out value='${deptJobBxVO.searchCnd}'/>&amp;pageIndex=<c:out value='${deptJobBxVO.pageIndex}'/>" onclick="fn_egov_list_deptjobbx(); return false;"><spring:message code="button.list" /></a></span></td>
	</tr>
	</table>
	</div>
	<input type="hidden" name="deptIndictOrdr" value="<c:out value='${indictOrdrValue}'/>" />
	<form:hidden path="deptJobBxId" />
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${deptJobBxVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${deptJobBxVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${deptJobBxVO.pageIndex}'/>" />
    <!-- //검색조건 유지 -->
</form:form>

</div>

</body>
</html>
