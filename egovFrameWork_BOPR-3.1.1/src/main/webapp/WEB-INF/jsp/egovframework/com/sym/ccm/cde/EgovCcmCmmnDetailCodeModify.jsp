<%
 /**
  * @Class Name  : EgovCcmCmmnDetailCodeModify.jsp
  * @Description : EgovCcmCmmnDetailCodeModify 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnDetailCode" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CmmnDetailCode(){
	location.href = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_CmmnDetailCode(form){
	
		if(!validateCmmnDetailCode(form)){
			return;
		}else{
			
			if(fnCheck(document.cmmnDetailCode.codeNm.value)){
				window.alert("코드명에 특수문자를 사용할 수 없습니다.");
				document.cmmnDetailCode.codeNm.select();
				return;
			}
			
			if(confirm("<spring:message code='common.save.msg'/>")){
				form.cmd.value = "Modify";
				form.submit();
			}
		}
	
}

/* 특수문자체크 */
function fnCheck(str){
    var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    if(special_pattern.test(str)){
    	/* 걸리면 true반환 */
        return true;
    } else {
   		return false;
	}
}

</script>

<form:form commandName="cmmnDetailCode" name="cmmnDetailCode" method="post">

<div class="contsBody">
	<h2>공통상세코드 관리</h2>
	<div class="location">공통코드 관리 > 공통상세코드 관리 > <strong>수정</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fn_egov_list_CmmnDetailCode();" title="공통상세코드 목록화면으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fn_egov_modify_CmmnDetailCode(document.cmmnDetailCode);" title="해당 공통상세코드 수정정보를 저장">수정</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="코드ID, 코드, 코드명, 코드설명, 사용여부의 순서로 보여지는 공통상세코드관리 수정입니다.">
		<caption>공통상세코드관리 수정</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드ID</th>
			<td colspan="3">${cmmnDetailCode.codeIdNm}</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드</th>
			<td><c:out value='${cmmnDetailCode.code}'/></td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드명</th>
			<td>
				<label for="codeNm" class="disp_none">코드명</label>
				<form:input  path="codeNm" size="60" maxlength="60" id="codeNm"/></td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드설명</th>
			<td>
				<label for="codeDc" class="disp_none">코드설명</label>
				<form:textarea path="codeDc" rows="3" cols="60" id="codeDc"/></td>
		</tr>
		
		<tr>
		<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용여부</th>
		<td colspan="3">
			<label for="codeDc" class="disp_none">사용여부</label>
			<form:select path="useAt" id="useAt">
				<form:option value="Y" label="Yes"/>
				<form:option value="N" label="No"/>
			</form:select>
		</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden값 -->
	<input name="cmd" type="hidden" value="Modify">
	<form:hidden path="codeId"/>
	<form:hidden path="code"/>
	
</form:form>
