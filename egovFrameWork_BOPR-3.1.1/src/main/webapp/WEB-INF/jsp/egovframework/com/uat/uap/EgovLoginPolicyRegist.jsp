<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/**
 * @Class Name  : EgovLoginPolicyRegist.java
 * @Description : EgovLoginPolicyRegist jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/cmm/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/cmm/button.css' />" rel="stylesheet" type="text/css">
<title>로그인정책 등록</title>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="loginPolicy" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectLoginPolicyList() {
    var varFrom = document.getElementById("loginPolicy");
    varFrom.action = "<c:url value='/uat/uap/selectLoginPolicyList.do'/>";
    varFrom.submit();
}

function fncLoginPolicyInsert() {

    var varFrom = document.getElementById("loginPolicy");
    varFrom.action = "<c:url value='/uat/uap/addLoginPolicy.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateLoginPolicy(varFrom)){
            return;
        }else{
            if(ipValidate())
                varFrom.submit();
            else
                return;
        }
    }
}

function ipValidate() {

    var varFrom = document.getElementById("loginPolicy");
    var IPvalue = varFrom.ipInfo.value;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + "는 예외 아이피 입니다..");
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + "는 예외 아이피 입니다.");
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("형식이 일치 하지않습니다. ");
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("형식이 일치 하지않습니다. ");
                result = false;
            }

            if ((i == 0) && (thisSegment > 255)) {
                alert("형식이 일치 하지않습니다. ");
                result = false;
            }
        }
    }

    return result;
}

</script>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div id="border" style="width:730px">
<table border="0">
  <tr>
    <td width="700">
<form:form commandName="loginPolicy" method="post" action="${pageContext.request.contextPath}/uat/uap/addLoginPolicy.do' />">
<table width="100%" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="40%" class="title_left">
   <h1><img alt="" src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle">&nbsp;로그인정책 등록</h1>
  </td>
 </tr>
</table>

<table width="100%" cellpadding="8" class="table-line" summary="로그인정책을 등록한다.">
  <caption>로그인정책 등록</caption>
  <tr>
    <th class="required_text" width="20%" scope="row" nowrap>사용자ID<img alt="" src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td class="lt_text" nowrap><input name="emplyrId" id="emplyrId" title="사용자ID" type="text" value="<c:out value='${loginPolicy.emplyrId}'/>" size="30" class="readOnlyClass" readonly ></td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row" nowrap>사용자명<img alt="" src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td class="lt_text" nowrap><input name="emplyrNm" id="emplyrNm" title="사용자명" type="text" value="<c:out value='${loginPolicy.emplyrNm}'/>" maxLength="50" size="30" class="readOnlyClass" readonly ></td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row" nowrap>IP정보<img alt="" src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td class="lt_text" nowrap><input name="ipInfo" id="ipInfo" title="IP정보" type="text" maxLength="23" size="30"  >&nbsp;<form:errors path="ipInfo" /></td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row" nowrap>IP제한여부<img alt="" src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td class="lt_text" nowrap>
      <select name="lmttAt" id="lmttAt" title="IP제한여부">
          <option value="Y">Y</option>
          <option value="N">N</option>
      </select>&nbsp;<form:errors path="lmttAt" />
    </td>
  </tr>
</table>

<table width="100%" cellpadding="8">
  <tr>
    <td align="center">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><span class="button"><input type="submit" value="<spring:message code="button.save" />" onclick="fncLoginPolicyInsert(); return false;"></span></td>
      <td>&nbsp;&nbsp;</td>
      <td><span class="button"><a href="<c:url value='/uat/uap/selectLoginPolicyList.do'/>?pageIndex=<c:out value='${loginPolicyVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${loginPolicyVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectLoginPolicyList(); return false;"><spring:message code="button.list" /></a></span></td>
    </tr>
   </table>
   </td>
  </tr>
</table>
<input type="hidden" name="dplctPermAt" value="Y" >
<input type="hidden" name="searchCondition" value="<c:out value='${loginPolicyVO.searchCondition}'/>" >
<input type="hidden" name="searchKeyword" value="<c:out value='${loginPolicyVO.searchKeyword}'/>" >
<input type="hidden" name="pageIndex" value="<c:out value='${loginPolicyVO.pageIndex}'/>" >
</form:form>

</td>
</tr>
</table>
</DIV>
</body>
</html>

