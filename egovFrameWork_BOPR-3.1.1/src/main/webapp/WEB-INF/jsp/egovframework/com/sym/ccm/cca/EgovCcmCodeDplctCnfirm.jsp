<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovCcmCodeDplctCnfirm.jsp
  * @Description : 공통코드중복확인
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.03    조재영          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.23
  *  @version 1.0
  *  @see
  *
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<title>공통코드 중복확인</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<base target="_self" >
<script type="text/javaScript" language="javascript">
function fncheckCode(){
	if(fnCheckNotKorean(document.checkForm.checkCode.value) && fnCheck(document.checkForm.checkCode.value)){
		document.checkForm.action ="<c:url value='/sym/ccm/cca/EgovCcmCodeDplctCnfirm.do'/>";
		document.checkForm.submit();
    }else{
        return;
    }
}
function fnReturnCode(){
    if (document.checkForm.usedCnt.value == 0){
	    opener.document.cmmnCode.codeId.value = document.checkForm.resultCode.value;
	    opener.document.cmmnCode.codeCheckYn.value=true;
        self.close();
    }else if (document.checkForm.usedCnt.value == 1){
        alert("이미 사용중인 코드입니다.");
        return;
    }else{
    	alert("먼저 중복확인을 실행하십시오");
        return;
    }
}
function fnClose(){
    var retVal="";
    window.returnValue = retVal;
    window.close();
}
function fnCheckNotKorean(koreanStr){
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) {
        }else{
            //hangul finding....
           	alert("한글은 사용할 수 없습니다.");
           	document.checkForm.checkCode.value ="";
           	document.checkForm.action ="<c:url value='/sym/ccm/cca/EgovCcmCodeDplctCnfirm.do'/>";
    		document.checkForm.submit();
            return false;
        }
    }
    return true;
}

// 2011.10.25 보안점검 후속조치
function fnCheck(str){
    var blank_pattern = /[\s]/g;
    var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    if( special_pattern.test(str) == true || blank_pattern.test(str) == true ){
        alert("공백 및 특수문자는 사용할 수 없습니다.");
        document.checkForm.checkCode.value ="";
       	document.checkForm.action ="<c:url value='/sym/ccm/cca/EgovCcmCodeDplctCnfirm.do'/>";
		document.checkForm.submit();
        return false;
    } else {
   		return true;
	}
}
</script>
</head>
<body>
<form name="checkForm" id="checkForm" action="">
<div class="contsBody">
	<h2>공통코드중복확인</h2>
	<div class="location">등록 > <strong>공통코드중복확인</strong></div>
	
	<div class="search">
	<fieldset class="searchboxA">
		<legend>공통코드중복확인</legend>
		<label for="searchKeyword">코드 : </label>
		<input type="hidden" name="resultCode" value="<c:out value="${checkCode}"/>" />
	    <input type="hidden" name="usedCnt" value="<c:out value="${usedCnt}"/>" />
	    <label for="checkCode" class="disp_none">중복확인할 코드</label>
		<input type="text" name="checkCode" value="<c:out value="${checkCode}"/>" maxlength="20" tabindex="1" title="코드입력"/>                       
		<input type="image" class="searchbtn" title="조회" src="/images/egovframework/bopr/btn_search.gif" alt="<spring:message code='button.inquire' />" onclick="javascript:fncheckCode();"/>
	</fieldset>
	</div>
	
	<div class="search" >
	<fieldset class="searchboxA">
		<legend>공통코드중복확인</legend>
	    <c:choose>
		    <c:when test="${usedCnt eq -1}">
		        &nbsp; 중복확인을 실행하십시오
		    </c:when>
		    <c:when test="${usedCnt eq 0}">
		        ${checkCode} 는 사용가능한 코드입니다.
		    </c:when>
		    <c:otherwise>
		        ${checkCode} 는 사용할수 없는 코드입니다.
		    </c:otherwise>
	    </c:choose>
	</fieldset>
    </div>
    
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fnReturnCode();" title="검색한 코드를 사용"><spring:message code='button.use' /></a></span>
		<span class="bbsBtn"><a href="javascript:fnClose();" title="현재창 닫기"><spring:message code='button.close' /></a></span>
	</div>
</div>
</form>
</body>
</html>
