<%
 /**
  * @Class Name  : EgovCcmCmmnDetailCodeRegist.jsp
  * @Description : EgovCcmCmmnDetailCodeRegist 화면
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
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
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
function fn_egov_regist_CmmnDetailCode(form){
	
		if(!validateCmmnDetailCode(form)){
			return;
		}else{
			
			if(fnCheckNotKorean(document.cmmnDetailCode.code.value)){
				window.alert("코드에 한글을 사용할 수 없습니다.");
				document.cmmnDetailCode.code.select();
				return;
			}
			
			if(fnCheck(document.cmmnDetailCode.code.value)){
				window.alert("코드에 특수문자를 사용할 수 없습니다.");
				document.cmmnDetailCode.code.select();
				return;
			}
			
			if(fnCheck(document.cmmnDetailCode.codeNm.value)){
				window.alert("코드명에 특수문자를 사용할 수 없습니다.");
				document.cmmnDetailCode.codeNm.select();
				return;
			}
			
			if(confirm("<spring:message code='common.save.msg'/>")){
				form.cmd.value = "Regist";
				form.submit();
			}
		}
	
}
/* ********************************************************
 * CodeId 가져오기
 ******************************************************** */
function fn_egov_get_CodeId(form){
	form.cmd.value = "";
	form.submit();
}
/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
function fncShowMessg(){
	if("<c:out value='${message}'/>" != ''){
	alert("<c:out value='${message}'/>");
	}
}

/* 한글입력 체크 */
function fnCheckNotKorean(koreanStr){
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) {
        }else{
            //한글이 있을때
            return true;
        }
    }
    return false;
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

$(document).ready(function() {
	fncShowMessg();
});
</script>

<form:form commandName="cmmnDetailCode" name="cmmnDetailCode" method="post">
<div class="contsBody">
	<h2>공통상세코드 관리</h2>
	<div class="location">공통코드 관리 > 공통상세코드 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fn_egov_list_CmmnDetailCode();" title="공통상세코드 목록화면으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fn_egov_regist_CmmnDetailCode(document.cmmnDetailCode);" title="해당 공통상세코드 정보를 저장">등록</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="코드ID, 코드, 코드명, 코드설명, 사용여부의 순서로 입력하는 공통상세코드관리 등록입니다.">
		<caption>공통상세코드관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드ID</th>
			<td colspan="3">
				<label for="clCode" class="disp_none">코드ID</label>
				<select name="clCode" class="select" onChange="javascript:fn_egov_get_CodeId(document.cmmnDetailCode);" title="clCode">
				<c:forEach var="result" items="${cmmnClCodeList}" varStatus="status">
				<option value='<c:out value="${result.clCode}"/>' <c:if test="${result.clCode == cmmnCode.clCode}">selected="selected"</c:if>><c:out value="${result.clCodeNm}"/></option>
				</c:forEach>
				</select>
				
				<label for="codeId" class="disp_none">코드ID</label>
				<select name="codeId" class="select" id="codeId">
				<c:forEach var="result" items="${cmmnCodeList}" varStatus="status">
				<option value='<c:out value="${result.codeId}"/>' ><c:out value="${result.codeIdNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드</th>
			<td>
				<label for="code" class="disp_none">코드</label>
				<form:input  path="code" size="15" maxlength="15" id="code"/>
			</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드명</th>
			<td>
				<label for="codeNm" class="disp_none">코드명</label>
				<form:input  path="codeNm" size="60" maxlength="60" id="codeNm"/>
			</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드설명</th>
			<td>
				<label for="codeDc" class="disp_none">코드설명</label>
				<form:textarea path="codeDc" rows="3" cols="60" id="codeDc"/>
			</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용여부</th>
			<td colspan="3">
				<label for="useAt" class="disp_none">사용여부</label>
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

	<!-- Hidden 값 -->
	<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
	
</form:form>
